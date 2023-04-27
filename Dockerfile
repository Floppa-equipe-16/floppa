FROM maven:3-amazoncorretto-11 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn compile; exit 0;

COPY . .

# We can skip tests since they are ran before building the dockerfile (in Github actions)
RUN mvn package -Dmaven.test.skip

FROM amazoncorretto:11

ARG CLUSTER_URL
ENV FLOPPA_MONGO_CLUSTER_URL=$CLUSTER_URL

ARG DATABASE
ENV FLOPPA_MONGO_DATABASE=$DATABASE

ARG HOSTEMAIL
ENV FLOPPA_HOST_EMAIL=$HOSTEMAIL

ARG HOSTPASSWORD
ENV FLOPPA_HOST_PASSWORD=$HOSTPASSWORD

COPY --from=build /app/target/floppa-0.0.1-jar-with-dependencies.jar floppa-0.0.1.jar

CMD [ "java", "-jar", "floppa-0.0.1.jar" ]

EXPOSE 8080