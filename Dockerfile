FROM maven:3-amazoncorretto-11 AS build

WORKDIR /app

COPY . .

RUN mvn package

FROM amazoncorretto:11

ARG cluster_url=mongodb+srv://root:ulaval@floppa-staging.sasi1f4.mongodb.net/?retryWrites=true&w=majority
ARG database=floppa-staging

ENV FLOPPA_MONGO_CLUSTER_URL=$cluster_url
ENV FLOPPA_MONGO_DATABASE=$database

COPY --from=build /app/target/floppa-0.0.1-jar-with-dependencies.jar floppa-0.0.1.jar

CMD [ "java", "-jar", "floppa-0.0.1.jar" ]

EXPOSE 8080