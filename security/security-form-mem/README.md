# Build
mvn clean package && docker build -t org.costajlmpp/security-form-mem .

# RUN

docker rm -f security-form-mem || true && docker run -d -p 8080:8080 -p 4848:4848 --name security-form-mem org.costajlmpp/security-form-mem 