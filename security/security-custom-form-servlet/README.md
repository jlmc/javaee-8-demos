# Build
mvn clean package && docker build -t org.costajlmpp/security-custom-form-servlet .

# RUN

docker rm -f security-custom-form-servlet || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-custom-form-servlet org.costajlmpp/security-custom-form-servlet 