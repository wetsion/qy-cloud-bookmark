
# qy云书签

## 项目描述

qy云书签当前主要应用于浏览器，用户安装浏览器扩展插件，通过浏览器右键菜单或者插件弹出菜单，将当前网页加入云收藏，收藏内容包含网页URL以及网页快照，收藏时可选择书签分类。

用户通过插件弹出菜单点击进入个人中心，对收藏的书签进行管理，管理包括书签分类、书签删除、书签的私有或公共分享。被设置为公共分享的书签，会在云书签的大厅中被其他用户看到，用户还会看到推荐的可能感兴趣的书签。大厅中，可对书签点赞、加入自己的收藏、评论。

## 技术栈

### 客户端

客户端主要包含**浏览器插件**和**web端**。

- 浏览器插件：

    收藏书签的快捷操作、登录、查看已收藏的书签。
    
    技术选型： Chrome扩展开发

- web端： 

    书签大厅、个人中心、插件下载页
    
    技术选型： Vue、ElementUI



### 服务端

服务端采用基于Spring cloud alibaba的分布式微服务架构。

功能 | 选型
---|---
存储 | MySQL
持久层ORM | Mybatis-Plus
缓存 | Redis
认证授权/SSO | Spring Cloud Oauth2
注册中心 | Nacos
配置中心 | Nacos
网关 | Springcloud Gateway
链路追踪/监控 | SkyWalking
日志存储 | Elasticsearch
日志传输 | Logstash
日志展示 | Kibana
接口文档 | Swagger
文件系统/CDN | （待定，拟采用云）
推荐系统 | （待定，初步mahout）


## 项目运行前准备

> 最好按下述顺序

### 1. 下载并启动nacos-server

1.1 下载nacos-server-1.1.4，并解压；  
1.2 进入nacos/bin目录，`sh startup.sh -m standalone`以单机模式启动nacos-server；  
1.3 访问 localhost:8848

### 2. 下载并启动elasticsearch

2.1 下载elasticsearch-6.6.2，并解压；  
2.2 进入elasticsearch根目录，执行`./bin/elasticsearch -d`后台启动运行；  
2.3 访问 localhost:9200

### 3. 下载并启动kibana

3.1 下载kibana-6.6.2，并解压；  
3.2 进入kibana/config目录，修改配置文件kibana.yml，将`elasticsearch.hosts: ["http://localhost:9200"]` 这一行被注释的配置打开；  
3.3 进入kibana根目录，执行`./bin/kibana`启动；  
3.4 访问localhost:5601

### 4. 下载并启动skywalking

4.1 下载skywalking-6.6.0，并解压；  
4.2 进入skywalking/config目录，修改application.yml文件，将elasticsearch配置项的注释都去掉，然后将h2配置项都注释掉；  
4.3 在skywalking根目录，执行`./bin/startup.sh`启动；  
4.4 访问 localhost:8080

#### 项目如何接入skywalking

1. 将skywalking 根目录/agent/config 目录下的agent.config文件拷贝到项目resources目录下，修改`agent.service_name=${SW_AGENT_NAME:qy-cloud-bookmark-xxx}`该配置项，改为当前服务名；

2. 启动项目时，启动命令加上
```sh
-javaagent:/Users/wetsion/dev/apache-skywalking-apm-bin/agent/skywalking-agent.jar
-Dskywalking_config=/Users/wetsion/IdeaProjects/qy-cloud-bookmark/qy-cloud-bookmark-gateway/src/main/resources/agent.config
-Dskywalking.collector.backend_service=localhost:11800
```
> -javaagent:后跟的是skywalking根目录下提供的agent包，`-Dskywalking_config`指向第一步agent.config的路径（或者通过`-Dskywalking.agent.service_name`配置服务名），`-Dskywalking.collector.backend_service=localhost:11800`配置skywalking后台收集信息的服务地址，默认11800端口


### 5. 下载并启动Logstash

5.1 下载logstash-6.6.2，并解压；  
5.2 进入logstash根目录，执行`./bin/logstash-plugin install logstash-codec-json_lines`安装插件；  
5.3 在config目录下创建一个配置文件`logstash-springboot.conf`:  
```sh
input {
  tcp {
    mode => "server"
    host => "0.0.0.0"
    port => 4560
    codec => json_lines
  }
}
output {
  elasticsearch {
    hosts => "localhost:9200"
    index => "springboot-%{+YYYY.MM.dd}"
  }
}
```

5.4 启动logstash，`./bin/lostash -f ./conf/logstash-springboot.conf`；  
5.5 项目中添加logback-spring.xml文件；



## 项目模块划分

按照DDD模型对业务进行拆分，暂定分为：

 - 用户服务模块

> 提供用户信息相关接口


 - 书签服务模块

> 较大的模块，包含书签，以及和书签相关的分类、点赞，提供这些实体信息的接口。鉴于点赞、分类和用户行为相关，所以调用用户服务模块。

 - 广告商业服务模块

> 提供页面展示的广告的接口

 - 系统服务模块

> 提供通知公告、客户端下载地址等接口

 - 后台管理模块

> 后台管理系统所需的接口


