#!/bin/bash

# Test script for Docker setup
echo "Testing Docker setup for Bank Management System"

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven first."
    exit 1
fi

echo "Building the application..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "Build successful!"
else
    echo "Build failed!"
    exit 1
fi

echo "Building Docker image..."
docker build -t bank-management-test .

if [ $? -eq 0 ]; then
    echo "Docker image built successfully!"
    echo "You can now run the container with:"
    echo "docker run -p 8080:8080 bank-management-test"
else
    echo "Docker build failed!"
    exit 1
fi

echo "Test completed successfully!"
