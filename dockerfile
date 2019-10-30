FROM openjdk:8
COPY ./target/noslowq-server-1.0.jar noslowq-server-1.0.jar
CMD java -jar noslowq-server-1.0.jar