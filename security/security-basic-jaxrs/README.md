# Build
mvn clean package && docker build -t org.costajlmpp/security-basic-jaxrs .

# RUN

docker rm -f security-basic-jaxrs || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-basic-jaxrs org.costajlmpp/security-basic-jaxrs 