#!/bin/sh
mvn clean package && docker build -t jcosta/jpa-stream-result .
docker rm -f jpa-stream-result || true && docker run -d -p 8080:8080 -p 4848:4848 --name jpa-stream-result jcosta/jpa-stream-result 
