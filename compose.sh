#!/bin/bash
if [[ $1 == "infra" ]]; then
    if [[ $2 == "up" ]]; then
        echo "starting up infrastructure..."
        docker-compose -f docker/infra.docker-compose.yml up -d
    elif [[ $2 == "down" ]]; then
        echo "destroying infrastructure..."
        docker-compose -f docker/infra.docker-compose.yml down
    fi
else
    echo "Invalid argument!"
fi