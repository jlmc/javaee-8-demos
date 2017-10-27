# Build
mvn clean package && docker build -t jcosta/jpa-stream-result .

# RUN

docker rm -f jpa-stream-result || true && docker run -d -p 8080:8080 -p 4848:4848 --name jpa-stream-result jcosta/jpa-stream-result 



docker rum -rm -d -p 8080:8080 -p 4848:4848 -p 8181:8181  oracle/glassfish:5.0

docker exec nervous_curran /bin/bash