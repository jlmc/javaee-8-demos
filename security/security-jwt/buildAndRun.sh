#!/bin/sh
mvn clean package && docker build -t org.jcosta/security-Jwt .
docker rm -f security-Jwt || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-Jwt org.jcosta/security-Jwt 
