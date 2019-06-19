FROM openjdk:11
VOLUME /tmp
VOLUME /tmp/fs
ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar' \
	&& echo $(date) > /image_built_at \
	&& /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENV JAVA_OPTS="\
            -server \
            -Xmx128m \
            -Xms256m"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
