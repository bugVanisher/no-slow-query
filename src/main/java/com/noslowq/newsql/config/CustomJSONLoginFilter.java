package com.noslowq.newsql.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.noslowq.newsql.user.dto.UserDetail;
import com.noslowq.newsql.user.persistence.ddl.UserDO;
import com.noslowq.newsql.user.services.UserService;
import com.noslowq.newsql.utils.AlgorithmUtil;
import com.noslowq.newsql.utils.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 JSON 登录
 */
public class CustomJSONLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = Logger.getLogger(CustomJSONLoginFilter.class);

    private final UserService userService;

    @Autowired
    private AlgorithmUtil algorithmUtil;

    CustomJSONLoginFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        JSONObject requestBody = getRequestBody(httpServletRequest);
        String username = requestBody.getString("username");
        String password = requestBody.getString("password");
        UserDO userDO = validateUsernameAndPassword(username, password);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(userDO, password, simpleGrantedAuthorities);
    }

    /**
     * 获取请求体
     */
    private JSONObject getRequestBody(HttpServletRequest request) throws AuthenticationException{
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                stringBuilder.append(new String(bs, 0, len));
            }
            return JSON.parseObject(stringBuilder.toString());
        } catch (IOException e) {
            log.error("get request body error.");
        }
        throw new AuthenticationServiceException("invalid request body");
    }

    /**
     * 校验用户名和密码
     */
    private UserDetail validateUsernameAndPassword(String username, String password) throws AuthenticationException {
        // TODO: 2019/10/7 密码加密
        UserDO userDO = null;
        try {
            userDO = userService.getByUsername(username);
        } catch (Exception e) {
            log.error(e, "get user error");
        }
        if (userDO == null){
            throw new UsernameNotFoundException("user not exist");
        }
        if(!userDO.getPassword().equals(algorithmUtil.AESdecrypt(userDO.getPassword()))){
            throw new AuthenticationServiceException("error username or password");
        }
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userDO, userDetail);
        return userDetail;
    }

}
