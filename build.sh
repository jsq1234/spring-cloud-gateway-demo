#!/bin/zsh

echo "Creating docker image for gateway"
docker build -t gateway-image ./gateway

echo "Creating docker image for user-service"
docker build -t user-service-image ./user-service

echo "Creating docker image for posts-service"
docker build -t posts-service-image ./posts-service