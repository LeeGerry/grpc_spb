# gRPC集成SpringBoot的例子
## 四个module 
* proto: 包含所有proto文件
* user-service: 依赖proto模块，并提供user的相关gRPC服务
* movie-service: 依赖proto模块，并提供movie的相关gRPC服务
* aggregator-service: 聚合模块，依赖user-service和movie-service，提供rest服务

## Client 可以使用聚合服务中提供的rest api, 也可以使用user-service和movie-service的gRPC api.

## 可以用Postman模拟使用rest api和gRPC api

## Android客户端使用rest api已经很普遍，下面的demo是使用gRPC的例子
1. Demo: https://github.com/LeeGerry/AndroidGrpc.git
2. Demo中使用了该例子，请在本地先启动user-service和movie-service，在Android项目的MainViewModel中把静态变量address设置为本机的IP address.
