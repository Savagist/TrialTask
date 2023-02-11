FROM openjdk:19
MAINTAINER Grebenkin Alexander
ADD target/Kameleoon-0.0.1-SNAPSHOT.jar kameleoon.jar
ENTRYPOINT ["java","-jar","kameleoon.jar"]