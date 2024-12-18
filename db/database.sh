#!/bin/bash

docker run \
  --name mysql-container \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=ticketdatabase \
  -p 3306:3306 \
  -d mysql:latest