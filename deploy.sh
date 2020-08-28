#!/bin/bash

DOCKER_APP_NAME=nawa-invitation-server

EXIST_BLUE=$(/usr/local/bin/docker-compose -p ${DOCKER_APP_NAME}-blue -f docker-compose.yml ps | grep Up)

if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    /usr/local/bin/docker-compose -f docker-compose.yml up -d --build nawa-invitation-server-blue

    sleep 10

    /usr/local/bin/docker-compose stop nawa-invitation-server-green
    /usr/local/bin/docker-compose rm nawa-invitation-server-green
else
    echo "green up"
    /usr/local/bin/docker-compose -f docker-compose.yml up -d --build nawa-invitation-server-green

    sleep 10

    /usr/local/bin/docker-compose stop nawa-invitation-server-blue
    /usr/local/bin/docker-compose rm nawa-invitation-server-blue
fi