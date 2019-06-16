
<div align="center">
    <p align="center">
    
   [![him-vue](https://github.com/lmxdawn/him-vue/raw/master/pic/him.jpg)](http://him-netty.await.fun/h5)
   

   </p>
   <p align="center">
    
   [![him-vue](https://img.shields.io/badge/him-him--vue-1.svg)](https://github.com/lmxdawn/him-vue)
   [![him-netty](https://img.shields.io/badge/him-him--netty-1.svg)](https://github.com/lmxdawn/him-netty)
   [![QQ群](https://img.shields.io/badge/QQ%E7%BE%A4-210277856-orange.svg)](https://shang.qq.com/wpa/qunwpa?idkey=d4965fc7101936dcdea5eb1d05e2eaeb3128f20796028ee937ab516652083c6c)
   [![](https://badge.juejin.im/entry/5cd6be3ae51d456e5b66ae3d/likes.svg?style=flat-square)](https://juejin.im/post/5cd6be3ae51d456e5b66ae3d)
    
   </p>
    
   <p align="center">
    
   [![vue](https://img.shields.io/badge/vue-2.x-1.svg)](https://github.com/vuejs/vue)
   [![netty](https://img.shields.io/badge/netty-4.1.25.Final-1.svg)](https://github.com/netty/netty)
   [![spring-boot](https://img.shields.io/badge/spring--boot-2.1.2.RELEASE-1.svg)](https://github.com/spring-projects/spring-boot)
    
   </p>
</div>

# him-netty

> 使用前先阅读**开源协议**: [中文版](https://github.com/lmxdawn/him-netty/LICENSE_CN) ,  [English version](https://github.com/lmxdawn/him-netty/LICENSE) , 协议出处 [Anti-996-License-1.0](https://github.com/kattgu7/Anti-996-License)

> 前端：vue  [前往](https://github.com/lmxdawn/him-vue) ，服务端 netty [前往](https://github.com/lmxdawn/him-netty)

# 踩坑指南

> * 1. iOS版本手机QQ中清空不了 Cookie 的bug (Android 版本的QQ没试), 其它浏览器均正常

> * 2. 手机微信中打开后点击输入文字后, 不管点不点击发送按钮都会出现短暂的不能点击的现象(任何按钮都不能点击), 后来发现是因为在微信里面, 输入法把 输入框顶上去了, 然后输入法隐藏后输入框还在上面!!!! [点击查看详情](https://developers.weixin.qq.com/community/develop/doc/00040a43cd4290dedbc7e7f1851400)
  。找到一个解决输入框的方法:  @blur="chatTextBlur" 监听失去焦点的事件(vue 写法), 然后在事件里面执行 `window.scroll(0, 0);`

> * 3. 因为设置了定位，`overflow: scroll` 原生滚动，iOS下会不流畅，解决办法：换成 `-webkit-overflow-scrolling: touch;`

# 功能列表
* [x] 单聊
* [x] 群聊
* [x] protobuf 编解码
* [x] 客户端心跳
* [x] 客户端断开重连
* [x] 异地登录, 通知下线
* [x] 移动端/PC端适配
* [x] 离线消息 (消息通过 ack 机制, 实现可达性)
* [x] 第三方QQ登录
* [x] 自带 emoji 表情
* [x] 文本消息
* [ ] 声音提示
* [ ] 图片消息
* [ ] 音频消息
* [ ] 视屏消息
* [ ] 分布式部署
* [ ] PHP 版本的 (Workerman 版本)


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

## SQL的导入

> 创建数据库，名称： him， 把 根目录下 scripts 里面的 him.sql 导入进去 

## 打包

> mvn -Dmaven.test.skip=true clean package

> java -jar him-api/target/him-api-0.0.1-SNAPSHOT.jar

> 如果要加环境配置 --spring.profiles.active=pro 即可， 默认是 dev 环境。
  **特别要注意：配置文件里面有跨域配置，这个一定要注意**

him-vue [前往](https://github.com/lmxdawn/him-vue) 和 him-netty [前往](https://github.com/lmxdawn/him-netty) 都启动后访问 http://localhost:8080

注意默认使用 QQ登录, 这个需要去申请QQ互联, 如果不想去申请, 则可以直接设置 Cookie, 两个值 UID 和 SID, 这两个值可以通过接口 /api/user/login/byPwd 获取, 具体请看java 代码

# 加好友演示

[![him-vue](https://github.com/lmxdawn/him-vue/raw/master/pic/user-show-how.gif)](http://him-netty.await.fun/h5)

# 加群演示

[![him-vue](https://github.com/lmxdawn/him-vue/raw/master/pic/group-show-how.gif)](http://him-netty.await.fun/h5)


# QQ 互联相关配置

### java 代码
> him-api/src/main/resources/ 这里的配置文件里面, `qq.auth.appid` 和 `qq.auth.appkey` 配置上即可

### vue 代码
> 详细配置 根目录下的 
`.env.development`
`.env.production`
`.env.stage` 这三个文件是配置, 分别代表 本地测试,生产环境,线上测试环境

|名称|描述|
| --- | --- |
| VUE_APP_API_BASE | API接口地址 |
| VUE_APP_WEBSOCKET_URL | websocket地址 |
| VUE_APP_USER_QR_CODE_URL | 生成用户的二维码地址(用来加好友的) |
| VUE_APP_GROUP_QR_CODE_URL | 生成群二维码的地址(用来加群的) |
| VUE_APP_ROUTER_BASE | 如果用了 NGINX 做代理, 并且有二级路径, 则需要配置此项 |

# 跨域问题

> NGINX 做了端口的代理后, header 头 设置了跨域, 但是还是获取不了, 不知道为啥, 欢迎大神来指导

> 最后我的解决办法, 全部用一个域名, 然后 NGINX 做路径的转换,下面贴一下我的配置

```
# 前端路径, 注意这里配置了二级目录后, 需要 vue 的路由里面也需要配置
# 我是写在配置文件里面的 VUE_APP_ROUTER_BASE 这个配置项来控制的
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

> 然后 windows 版本执行 `proto.bat` 即可，Linux/Max 运行 `sh proto.sh`

