#!/bin/bash
docker network create pagila-network
docker run -d --net=pagila-network --name=pagila_db postgres/dvdrental:latest
docker run -d -p 8888:8080 --net=pagila-network --name=pagila_restservice tomcat/dvdrental:latest