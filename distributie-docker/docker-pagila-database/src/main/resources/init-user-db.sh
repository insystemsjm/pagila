#!/bin/sh

set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
CREATE DATABASE dvdrental;
GRANT ALL PRIVILEGES ON DATABASE dvdrental TO postgres;
EOSQL
