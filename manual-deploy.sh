#!/bin/bash

git pull origin develop

echo "clean project"
./gradlew clean

echo "build project"
./gradlew build

DOCKER_APP_NAME=nawa-invitation-server

EXIST_BLUE=$(docker ps | grep ${DOCKER_APP_NAME}-blue)

if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    docker-compose -f docker-compose.yml up -d --build ${DOCKER_APP_NAME}-blue

    sleep 25

    docker stop ${DOCKER_APP_NAME}-green
    docker rm ${DOCKER_APP_NAME}-green
else
    echo "green up"
    docker-compose -f docker-compose.yml up -d --build ${DOCKER_APP_NAME}-green

    sleep 25

    docker stop ${DOCKER_APP_NAME}-blue
    docker rm ${DOCKER_APP_NAME}-blue
fi