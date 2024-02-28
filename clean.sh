#!/bin/zsh

docker-compose down

echo "Removing gateway-image"
docker image rm gateway-image

echo "Removing user-service-image"
docker image rm user-service-image

echo "Removing posts-service-image"
docker image rm posts-service-image