#!/bin/sh
mvn clean package && docker build -t org.jcosta/security-form-mem .
docker rm -f security-form-mem || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-form-mem org.jcosta/security-form-mem 
