#!/bin/sh
mvn clean package && docker build -t com.radPenguins/penguinCompany .
docker rm -f penguinCompany || true && docker run -d -p 8080:8080 -p 4848:4848 --name penguinCompany com.radPenguins/penguinCompany 
