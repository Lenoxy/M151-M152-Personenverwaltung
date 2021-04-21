FROM adoptopenjdk/openjdk11:alpine-jre
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:7777", "-jar","/app.jar"]
