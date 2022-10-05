FROM openjdk:11.0-jdk
ENV JAVA_HOME /usr/local/openjdk-11

ADD entrypoint.sh entrypoint.sh

RUN chmod 777 entrypoint.sh

VOLUME /var/log/spring-boot

ADD ./target/*.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]