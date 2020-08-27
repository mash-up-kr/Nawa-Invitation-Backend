#!/bin/bash

DOCKER_APP_NAME=nawa-invitation-server

EXIST_BLUE=$(sudo /usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-blue -f docker-compose.yml ps | grep Up)

if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    sudo /usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-blue -f docker-compose.yml up -d --build nawa-invitation-server-blue

    sleep 10

    sudo /usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-green -f docker-compose.yml down
else
    echo "green up"
    sudo /usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-green -f docker-compose.yml up -d --build nawa-invitation-server-green

    sleep 10

    sudo /usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-blue -f docker-compose.yml down
fi