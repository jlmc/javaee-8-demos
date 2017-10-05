#!/bin/sh
mvn clean package && docker build -t org.jcosta/security-custom-form-servlet .
docker rm -f security-custom-form-servlet || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-custom-form-servlet org.jcosta/security-custom-form-servlet 
