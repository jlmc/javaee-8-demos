# Build
mvn clean package && docker build -t org.costajlmpp/security-Jwt .

# RUN

docker rm -f security-Jwt || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-Jwt org.costajlmpp/security-Jwt 