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

him-vue [前往](https://github.com/lmxdawn/him-vue) 和 him-netty [前往](https://github.com/lmxdawn/him-netty) 都启动后访问 http://localhost:8080


# protobuf 杂谈
 
> 说明： 目前所有文件都生成好了，不需要在生成，下面简单说明下
## java 中使用

下载好 him-netty 后在 protocol 目录下

> 生成 java 类需要安装 安装  protoc 下载地址：https://github.com/protocolbuffers/protobuf/releases，

> 目前下载的 v3.7.1，解压到任意目录  ，然后把这个目录添加到环境变量 Path 中

> 然后 windows 版本执行 proto.bat 即可，Linux 运行 proto.sh

