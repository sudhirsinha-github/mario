# This docker file will build MARIO  application.
FROM 208904473210.dkr.ecr.eu-west-1.amazonaws.com/oracle-java:1.8.0_11

MAINTAINER sudhir.kumar@gmail.com

COPY mario-fat.jar home/ubuntu/mario/

COPY conf.json /home/ubuntu/mario/

COPY logback.xml /home/ubuntu/mario/

COPY command.sh /home/ubuntu/mario/

EXPOSE 8080

WORKDIR /home/ubuntu/mario/

CMD sh ./command.sh

