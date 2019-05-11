# him-netty

> 前端：vue  [前往](https://github.com/lmxdawn/him-vue) ，服务端 netty [前往](https://github.com/lmxdawn/him-netty)

# 环境要求 

## git
> 这个版本管理肯定需要安装的

## jdk 
> JDK 8

## maven 
> 3.6.1

## spring boot 
> 2.1.2

## 下载

> git clone https://github.com/lmxdawn/him-netty.git

> cd him-netty

## 打包

> mvn -Dmaven.test.skip=true clean package

> java -jar him-api/target/him-api-0.0.1-SNAPSHOT.jar

> 如果要加环境配置 --spring.profiles.active=pro 即可， 默认是 dev 环境。
  **特别要注意：配置文件里面有跨域配置，这个一定要注意**

him-vue [前往](https://github.com/lmxdawn/him-vue) 和 him-netty [前往](https://github.com/lmxdawn/him-netty) 都启动后访问 http://localhost:8080

注意默认使用 QQ登录, 这个需要去申请QQ互联, 如果不想去申请, 则可以直接设置 Cookie, 两个值 UID 和 SID, 这两个值可以通过接口 /api/user/login/byPwd 获取, 具体请看java 代码

# 跨域问题

> NGINX 做了端口的代理后, header 头 设置了跨域, 但是还是获取不了, 不知道为啥, 欢迎大神来指导

> 最后我的解决办法, 全部用一个域名, 然后 NGINX 做路径的转换,下面贴一下我的配置

```
# 前端路径
location /h5 {
   try_files $uri $uri/ /h5/index.html;
}
# API 路径
location /api
{
  proxy_pass http://127.0.0.1:9000/api;
  proxy_http_version 1.1;
  proxy_set_header Upgrade $http_upgrade;
  proxy_set_header Connection "Upgrade";
  proxy_set_header X-Real-IP $remote_addr;
}
# ws 路径
location /ws
{
  proxy_pass http://127.0.0.1:9001;
  proxy_http_version 1.1;
  proxy_set_header Upgrade $http_upgrade;
  proxy_set_header Connection "Upgrade";
  proxy_set_header X-Real-IP $remote_addr;
}
```

# protobuf 杂谈
 
> 说明： 目前所有文件都生成好了，不需要在生成，下面简单说明下
## java 中使用

下载好 him-netty 后在 protocol 目录下

> 生成 java 类需要安装 安装  protoc 下载地址：https://github.com/protocolbuffers/protobuf/releases，

> 目前下载的 v3.7.1，解压到任意目录  ，然后把这个目录添加到环境变量 Path 中

> 然后 windows 版本执行 proto.bat 即可，Linux 运行 proto.sh

