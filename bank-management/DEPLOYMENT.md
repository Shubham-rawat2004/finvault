# Docker & CI/CD Setup for Bank Management System

This document provides instructions for setting up and using Docker and GitHub CI/CD for the Bank Management System.

## Prerequisites

- Docker installed on your machine
- Docker Hub account (for CI/CD)
- GitHub repository with the code

## Local Development with Docker

### 1. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up --build

# Run in detached mode
docker-compose up -d --build

# Stop services
docker-compose down
```

### 2. Build Docker Image Manually

```bash
# Build the application first
mvn clean package

# Build Docker image
docker build -t bank-management .

# Run the container
docker run -p 8080:8080 bank-management
```

## CI/CD Setup with GitHub Actions

### 1. Configure Secrets in GitHub Repository

Go to your GitHub repository → Settings → Secrets and variables → Actions → New repository secret

Add the following secrets:
- `DOCKERHUB_USERNAME`: Your Docker Hub username
- `DOCKERHUB_TOKEN`: Your Docker Hub access token

### 2. How to Get Docker Hub Token

1. Log in to Docker Hub
2. Go to Account Settings → Security → Access Tokens
3. Create a new access token with read/write permissions

### 3. CI/CD Workflow

The GitHub Actions workflow will:
- Trigger on push to main/master branches and pull requests
- Build the application with Maven
- Run tests
- Build Docker image
- Push to Docker Hub (only on main/master branch pushes)
- Deploy to production (deployment steps need to be configured)

## Production Deployment

### Option 1: Manual Deployment with Docker

```bash
# Pull the latest image from Docker Hub
docker pull your-dockerhub-username/bank-management:latest

# Run the container
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your-mysql-host:3306/bankdb \
  -e SPRING_DATASOURCE_USERNAME=your-db-user \
  -e SPRING_DATASOURCE_PASSWORD=your-db-password \
  your-dockerhub-username/bank-management:latest
```

### Option 2: Docker Compose for Production

Create a `docker-compose.prod.yml` file with production settings:

```yaml
version: '3.8'

services:
  app:
    image: your-dockerhub-username/bank-management:latest
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_URL=jdbc:mysql://production-mysql:3306/bankdb
      - SPRING_DATASOURCE_USERNAME=prod-user
      - SPRING_DATASOURCE_PASSWORD=prod-password
    restart: always

  mysql:
    image: mysql:8.0
    environment:
      - MYSQL_ROOT_PASSWORD=secure-password
      - MYSQL_DATABASE=bankdb
    volumes:
      - mysql_prod_data:/var/lib/mysql
    restart: always

volumes:
  mysql_prod_data:
```

## Environment Variables

The application uses the following environment variables:

- `SPRING_DATASOURCE_URL`: JDBC URL for MySQL database
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password

## Troubleshooting

### Common Issues

1. **Database Connection Issues**: Ensure MySQL is running and accessible
2. **Port Conflicts**: Check if port 8080 is available
3. **Docker Build Fails**: Make sure to run `mvn clean package` first to create the JAR file

### Useful Docker Commands

```bash
# View running containers
docker ps

# View logs
docker logs <container_id>

# Stop container
docker stop <container_id>

# Remove all stopped containers
docker container prune

# Remove unused images
docker image prune
```

## Next Steps

1. Configure your production database credentials
2. Set up SSL/TLS for production
3. Configure monitoring and logging
4. Set up database backups
5. Configure auto-scaling for production workload
