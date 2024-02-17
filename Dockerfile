FROM amazoncorretto:21-alpine

ARG PROJECT_VERSION

WORKDIR /apps
COPY ./build/libs/chat-${PROJECT_VERSION}.jar chat.jar

ENTRYPOINT [ "java", "-jar", "chat.jar" ]
