# Build
mvn clean package && docker build -t org.costajlmpp/security-custom-form-jsf .

# RUN

docker rm -f security-custom-form-jsf || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-custom-form-jsf org.costajlmpp/security-custom-form-jsf 