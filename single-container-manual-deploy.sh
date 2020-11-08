#!/bin/bash

DOCKER_APP_NAME=nawa-invitation-server

git pull origin develop

echo "kill and start application"

docker stop ${DOCKER_APP_NAME}-blue
docker rm ${DOCKER_APP_NAME}-blue

echo "clean project"
./gradlew clean

echo "build project"
./gradlew build

docker-compose -f docker-compose.yml up -d --build ${DOCKER_APP_NAME}-blue
