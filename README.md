# 系统介绍
NoSlowQ(no-slow-query)——一个能够轻松发现新增SQL语句，并能自动做分析和通知的系统，由 [客户端](https://github.com/bugVanisher/newsql-agent) 和 [服务端](https://github.com/bugVanisher/no-slow-query)组成。

（系统开发的动因可以查看 [这里](https://bugvanisher.github.io/2019/02/09/why-we-need-sql-analyse/)）

系统架构图如下：
![](http://assets.processon.com/chart_image/5b4a1a69e4b07df3b43b1501.png)


# 整体效果

[have a look](https://bugvanisher.github.io/2019/02/17/the-demonstration-of-mbappe/)

# 技术栈
## 后端技术栈

1.SpringBoot  
2.SpringSecurity  
3.MyBatis  
4.MySQL  

## 前端技术栈

1.Vue  
2.ElementUI  
3.axios  
4.vue-router  

# 本地开发环境
## Step1-准备数据库

1.clone项目到本地```https://github.com/bugVanisher/no-slow-query```  

2.数据库脚本放在schema目录下，在MySQL中创建完 *noslowq* 数据库并在其中执行目录下的所有数据库脚本

3.数据库配置在项目的resources目录下的application.properties文件中 


## Step2-启动后端springboot
springboot常见的启动方式有三种：

1、项目导入IDE后，Run执行Application.java中的main方法（推荐）

2、pom.xml文件引入spring-boot-maven-plugin插件(本项目已引入)，在IDEA中的maven工具或命令行执行mvn命令

```
mvn spring-boot:run
```

3、mvn install 编译生成出项目的jar，然后运行jar (建议生产发布时使用)

```
java -jar project-version.jar
```

启动完成后，系统开始监听7001端口。

## Step3-启动前端node
进入 src/main/resources/public 目录，执行以下操作

```shell
# 安装依赖
npm install

# 在 localhost:9528 启动项目
npm run dev
```  

前端项目中已经配置了端口转发，将数据转发到SpringBoot（7001端口）上，因此项目启动之后，在浏览器中输入

```
http://localhost:9528 
```
就可以访问我们的前端项目了，所有的请求通过端口转发将数据传到SpringBoot中（注意此时不要关闭SpringBoot项目）。

