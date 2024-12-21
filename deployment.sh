#!/bin/bash

# Function to center text
center() {
  local COLUMNS=$(tput cols)
  printf "%*s\n" $(((${#1} + COLUMNS) / 2)) "$(echo -e "$1")"
}

# Colors and formatting
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
RESET='\033[0m'
BOLD='\033[1m'

# Animation spinner function
spinner() {
  local pid=$1
  local delay=0.1
  local spinstr='|/-\\'
  while kill -0 "$pid" 2>/dev/null; do
    for c in $spinstr; do
      printf "\r\033[0;36m[%c]\033[0m" "$c"
      sleep $delay
    done
  done
  printf "\r    \r"
}

clear

# Step 1: Clean and Build the App
center "${CYAN}${BOLD}############## MVN Cleaning and Building the App ##############${RESET}"
(mvn clean package -DskipTests > /dev/null 2>&1) & spinner $!
center "${GREEN}${BOLD}########### Done Building the app Successfully ###########${RESET}"

# Step 2: Deploy Infrastructure
center "${YELLOW}${BOLD}##### Next step is deployment #####${RESET}"
center "${CYAN}${BOLD}##### Deploying the Infrastructure Using Docker Compose #####${RESET}"
(docker-compose up --build -d > /dev/null 2>&1) & spinner $!
center "${GREEN}${BOLD}##### Done #####${RESET}"

# Step 3: Listing Containers
center "${CYAN}${BOLD}################# Listing the containers ####################${RESET}"
docker ps --format "table {{.ID}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}" | while IFS= read -r line; do
  center "$line"
done

# Final Message
center "${GREEN}${BOLD}Deployment Completed Successfully!${RESET}"
