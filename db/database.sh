#!/bin/bash

docker run \
  --name mysql-local \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=ticketdatabase \
  -p 3306:3306 \
  -d \
  mysql:latest


#  docker run \
#    --name prodcontainer \
#    -e MYSQL_ROOT_PASSWORD=root \
#    -e MYSQL_DATABASE=ticketdatabase \
#    -p 3307:3306 \
#    -d \
#    mysql:latest