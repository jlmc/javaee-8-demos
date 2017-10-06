# Docker Images

## Derby database

To build a images that contains a derby database server we should run the following commands:

```bash
cd alpine-java8
docker build -t costajlmpp/alpine-java8 .
cd ..
cd alpine-java8-derby
docker build -t costajlmpp/alpine-java8-derby .
```

At this point we have a docker images with java 8 with derby database with it is the database type used by the Default datasource of the Glassfish server.

To start a docker container running the derby database we should run the next command:

```bash
docker run -d -it --name costajlmpp-alpine-java8-derby-demos -p 1527:1527 costajlmpp/alpine-java8-derby
```
