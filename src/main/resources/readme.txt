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
10.加入swagger， 文档地址 http://localhost:8882/swagger-ui.html
11.自动生成代码
12.bean添加注解，表单校验这个功能是属于spring自己的
13.加密-解密
14. 注解
15.excel批量导入导出
16.guava混存Cache，定时缓存
17.Redis缓存
加入的步奏： 1.导入pom 2.配置property 3.添加config 4.写uitl 5.Controller引用  目前引用还是有点问题  json序列化的问题
18.添加了过滤器，不管用，回来再研究
19.请求参数的校验！！
a.首先是对bean进行配置：通过javax.validation.constraints下的注解实现字段验证，便于后续校验  bean是TestValidateBan
b.com.bee.springboot.util.validation;查看 校验 方法
c. Controller     http://localhost:8882/user/getUserInfo调用实现
20. JsonFormat 对 日期格式的修改，响应成自己的效果 json
-------------------