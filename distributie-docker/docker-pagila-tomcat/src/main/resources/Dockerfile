FROM tomcat:9.0

MAINTAINER 'Rob "rnobel@quobell.nl"'

COPY *.war /usr/local/tomcat/webapps/

ENV PAGILADB_HOSTNAME pagila_db
ENV PAGILADB_PORT 5432
ENV PAGILADB_DBNAME dvdrental
ENV PAGILADB_DBUSER postgres
ENV PAGILADB_DBPASS postgres

CMD ["catalina.sh", "run"]