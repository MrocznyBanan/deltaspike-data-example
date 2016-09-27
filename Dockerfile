FROM airhacks/wildfly
MAINTAINER Mroczny Banan, mrocznybanan.eu
COPY ./target/deltaspike.war ${DEPLOYMENT_DIR}