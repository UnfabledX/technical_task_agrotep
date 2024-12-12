FROM eclipse-temurin:17-jdk-alpine AS builder
ARG JAR_FILE=target/fishmarket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} fishstore.jar
RUN java -Djarmode=layertools -jar fishstore.jar extract

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
COPY public/images/ /public/images/
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
