FROM openjdk:11
VOLUME /tmp
VOLUME /fs
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar' \
	&& echo $(date) > /image_built_at \
	&& /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
