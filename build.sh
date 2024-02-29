#!/bin/zsh

echo "$(tput bold)$(tput setaf 6)Creating docker image for gateway$(tput sgr0)"
docker build -t gateway-image ./gateway

echo "$(tput bold)$(tput setaf 6)Creating docker image for user-service$(tput sgr0)"
docker build -t user-service-image ./user-service

echo "$(tput bold)$(tput setaf 6)Creating docker image for posts-service$(tput sgr0)"
docker build -t posts-service-image ./posts-service

