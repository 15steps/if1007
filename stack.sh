#!/bin/bash

COMPOSE_FILE=""

# select stack
if [[ $1 == "infra" || $1 == "i" ]]; then
    COMPOSE_FILE=docker/infra.docker-compose.yml
elif [[ $1 == "services" || $1 == "s" ]]; then
    COMPOSE_FILE=docker/services.docker-compose.yml
else
    echo "Invalid argument!"
fi

if [[ $2 == "up" ]]; then
    echo "starting up ${COMPOSE_FILE}..."
    ./gradlew build -x test
    docker-compose -f ${COMPOSE_FILE} up -d --build
elif [[ $2 == "down" ]]; then
    echo "destroying ${COMPOSE_FILE}..."
    docker-compose -f ${COMPOSE_FILE} down
fi