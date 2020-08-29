#!/bin/bash

DOCKER_APP_NAME=nawa-invitation-server

EXIST_BLUE=$(docker ps | grep ${DOCKER_APP_NAME}-blue)

if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    /usr/local/bin/docker-compose -f docker-compose.yml up -d --build ${DOCKER_APP_NAME}-blue

    sleep 10

    /usr/local/bin/docker-compose stop ${DOCKER_APP_NAME}-green
    /usr/local/bin/docker-compose rm ${DOCKER_APP_NAME}-green
else
    echo "green up"
    /usr/local/bin/docker-compose -f docker-compose.yml up -d --build ${DOCKER_APP_NAME}-green

    sleep 10

    /usr/local/bin/docker-compose stop ${DOCKER_APP_NAME}-blue
    /usr/local/bin/docker-compose rm ${DOCKER_APP_NAME}-blue
fi