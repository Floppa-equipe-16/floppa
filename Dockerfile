FROM maven:3-amazoncorretto-11 AS build

WORKDIR /app

COPY . .

# We can skip tests since they are ran before building the dockerfile (in Github actions)
RUN mvn clean package -Dmaven.test.skip

FROM amazoncorretto:11

ARG CLUSTER_URL
ENV FLOPPA_MONGO_CLUSTER_URL=$CLUSTER_URL

ARG DATABASE
ENV FLOPPA_MONGO_DATABASE=$DATABASE

COPY --from=build /app/target/floppa-0.0.1-jar-with-dependencies.jar floppa-0.0.1.jar

CMD [ "java", "-jar", "floppa-0.0.1.jar" ]

EXPOSE 8080