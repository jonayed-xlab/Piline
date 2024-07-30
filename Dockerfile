FROM ghcr.io/graalvm/jdk-community:21
VOLUME /tmp
COPY target/piline-v1.0.0.jar /piline-server.jar
ENTRYPOINT ["java", "-jar", "/piline-server.jar"]