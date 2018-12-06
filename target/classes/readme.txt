Spring Boot与mybatis整合以及开启声明式事务
内部包含
1.基础的增删查改
2.缓存
3.文件上传下载
4.邮件发送
5.能够根据生产环境动态加载配置文件
6.actuator，监控整个应用程序
7.天气预报，添加了Restempalate,HttpClient远程访问接口 http://localhost:8882/weather/cityName/%E5%8D%9A%E7%88%B1
  因为这个接口是免费，担心把它访问死了，加入Redis缓存，但是本机没有配账户密码也能用
8.集成了springboot 的jpa，实现过程  1.类标注entity 2.创建接口实现jpa，根据名字实现方法  3.根据业务调用
9.成功添加的pageHelper分頁
10.加入swagger
11.自动生成代码
-------------------