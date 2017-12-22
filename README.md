## Apache RocketMQ [![Build Status](https://travis-ci.org/apache/rocketmq.svg?branch=master)](https://travis-ci.org/apache/rocketmq) [![Coverage Status](https://coveralls.io/repos/github/apache/rocketmq/badge.svg?branch=master)](https://coveralls.io/github/apache/rocketmq?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.apache.rocketmq/rocketmq-all/badge.svg)](http://search.maven.org/#search%7Cga%7C1%7Corg.apache.rocketmq)
[![GitHub release](https://img.shields.io/badge/release-download-orange.svg)](https://rocketmq.apache.org/dowloading/releases)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

**[Apache RocketMQ](https://rocketmq.apache.org) is a distributed messaging and streaming platform with low latency, high performance and reliability, trillion-level capacity and flexible scalability.**

It offers a variety of features:

* Pub/Sub messaging model
* Scheduled message delivery
* Message retroactivity by time or offset
* Log hub for streaming
* Big data integration
* Reliable FIFO and strict ordered messaging in the same queue
* Efficient pull&push consumption model
* Million-level message accumulation capacity in a single queue
* Multiple messaging protocols like JMS and OpenMessaging
* Flexible distributed scale-out deployment architecture
* Lightning-fast batch message exchange system
* Various message filter mechanics such as SQL and Tag
* Docker images for isolated testing and cloud isolated clusters
* Feature-rich administrative dashboard for configuration, metrics and monitoring


----------

## Learn it & Contact us
* Mailing Lists: <https://rocketmq.apache.org/about/contact/>
* Home: <https://rocketmq.apache.org>
* Docs: <https://rocketmq.apache.org/docs/quick-start/>
* Issues: <https://issues.apache.org/jira/browse/RocketMQ>
* Ask: <https://stackoverflow.com/questions/tagged/rocketmq>
 

----------

## Apache RocketMQ Community
* [RocketMQ Community Projects](https://github.com/apache/rocketmq-externals)

----------

## Contributing
We always welcome new contributions, whether for trivial cleanups, big new features or other material rewards, more details see [here](http://rocketmq.apache.org/docs/how-to-contribute/) 
 
----------
## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation


RocketMQ共包含9个模块

rocketmq-common：通用的枚举、基类方法、或者数据结构，包名有admin、consumer、filter、hook、message
rocketmq-remoting：使用netty的客户端、服务端，使用fastjson序列化，自定义二进制协议
rocketmq-srvutil：只有一个ServerUtil类，只提供Server程序依赖，尽可能减少客户端依赖
rocketmq-store：消息存储，索引，consumerLog，commitLog等
rocketmq-client：消息发送和接收，包含consumer和producer
rocketmq-filtersrv：消息过滤器
rocketmq-broker：服务端，接受消息，存储消息，consumer拉取消息
rocketmq-tools：命令行工具
rocketmq-namesrv：NameServer，类似服务注册中心，broker在这里注册，consumer和producer在这里找到broker地址

通信关系：
Producer和Name Server：每一个Producer会与Name Server集群中的一台机器建立TCP连接，会从这台Name Server上拉取路由信息。
Producer和broker：Producer会和它要发送的topic相关的master类型的broker建立TCP连接，用于发送消息以及定时的心跳信息。broker中会记录该Producer的信息，供查询使用
broker与Name Server：broker（不管是master还是slave）会和每一台Name Server机器来建立TCP连接。broker在启动的时候会注册自己配置的topic信息到Name Server集群的每一台机器中。即每一台Name Server都有该broker的topic的配置信息。master与master之间无连接，master与slave之间有连接
Consumer和Name Server：每一个Consumer会和Name Server集群中的一台机器建立TCP连接，会从这台Name Server上拉取路由信息，进行负载均衡
Consumer和broker：Consumer可以与master或者slave的broker建立TCP连接来进行消费消息，Consumer也会向它所消费的broker发送心跳信息，供broker记录。
