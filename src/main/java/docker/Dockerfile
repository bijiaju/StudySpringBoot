#基础镜像   自己写的dockerFile
FROM hub.c.163.com/r00txx/java8

#作者
MAINTAINER bee byq_work@163.com

# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp

# 将jar包添加到容器中并更名为app.jar
ADD springbootMybatis-0.0.1-SNAPSHOT.jar app.jar

#这是为了wait-for容器编排使用的，但是如果加上这个会编译错误
#RUN apt-get update
#RUN apt-get install -y netcat

# 运行jar包
RUN touch /app.jar

#声明的端口，并不会作用到端口映射
EXPOSE 8080

#可让容器像一个可执行程序一样运行，这样运行时可以像软件一样接受参数执行
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]