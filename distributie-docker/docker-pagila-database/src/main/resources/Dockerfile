FROM postgres:9.6

MAINTAINER 'Rob "rnobel@quobell.nl"'

RUN mkdir -p /docker-entrypoint-initdb.d

COPY init-user-db.sh  /docker-entrypoint-initdb.d/
COPY pagila.sql /docker-entrypoint-initdb.d