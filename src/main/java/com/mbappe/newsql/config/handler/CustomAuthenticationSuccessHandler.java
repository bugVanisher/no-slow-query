package com.mbappe.newsql.config.handler;

import com.alibaba.fastjson.JSON;
import com.mbappe.newsql.base.AjaxResponseBody;
import com.mbappe.newsql.constants.StatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 认证成功处理器
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody<Token> response = new AjaxResponseBody<>();
        response.setSuccess(true);
        response.setData(new Token("admin-token"));
        response.setCode(StatusCode.SUCCESS.getCode());
        response.setMessage("login success");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
    }

    static class Token {
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        Token(String token) {
            this.token = token;
        }

        private String token;

    }

}
