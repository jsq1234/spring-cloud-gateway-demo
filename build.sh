#!/bin/zsh

echo "$(tput bold)$(tput setaf 6)Removing existing images...$(tput sgr0)"
./clean.sh

echo "$(tput bold)$(tput setaf 6)Rebuilding packages using mvnw$(tput sgr0)" 
./mvnw clean package --threads 8

echo "$(tput bold)$(tput setaf 6)Creating docker image for gateway$(tput sgr0)"
docker build -t gateway-image ./gateway

echo "$(tput bold)$(tput setaf 6)Creating docker image for user-service$(tput sgr0)"
docker build -t user-service-image ./user-service

echo "$(tput bold)$(tput setaf 6)Creating docker image for posts-service$(tput sgr0)"
docker build -t posts-service-image ./posts-service
