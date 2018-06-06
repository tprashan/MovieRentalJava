FROM openjdk:8

ENV APP /var/www/rent_movies_backend

WORKDIR $APP

COPY . $APP

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/movie-rental-1.0-SNAPSHOT.jar"]