# Create a docker derby database

cd alpine-java8

docker build -t jcosta/alpine-java8 .

cd ..

cd alpine-java8-derby

docker build -t jcosta/alpine-java8-derby .

docker run -d -it --name jcosta-alpine-java8-derby-demos -p 1527:1527 jcosta/alpine-java8-derby