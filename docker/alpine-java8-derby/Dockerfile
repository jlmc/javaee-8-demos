FROM costajlmpp/alpine-java8

MAINTAINER Joao Costa, costajlmpp@gmail.com

ENV INSTALL_DIR /opt
ENV VERSION db-derby-10.13.1.1

RUN apk update \
 && apk add ca-certificates wget \
 && update-ca-certificates

# download the derby package
# http://mirror.dkd.de/apache/db/derby/db-derby-10.13.1.1/db-derby-10.13.1.1-bin.zip

RUN wget -O ${INSTALL_DIR}/${VERSION}-bin.zip http://mirror.dkd.de/apache/db/derby/${VERSION}/${VERSION}-bin.zip
RUN unzip ${INSTALL_DIR}/${VERSION}-bin.zip -d ${INSTALL_DIR}
RUN rm -rf ${INSTALL_DIR}/${VERSION}-bin.zip

ENTRYPOINT ${INSTALL_DIR}/${VERSION}-bin/bin/startNetworkServer -h 0.0.0.0
EXPOSE 1527


