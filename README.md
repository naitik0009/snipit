🚀 SnipIt Backend (GenSeven Ecosystem)
SnipIt is a distributed Code Snippet Manager built with a professional Scala Multi-Module Architecture.

It demonstrates an Enterprise-grade setup using Shared Libraries, Local Artifact Publishing, Layered Architecture, and Dockerized Deployment.

🏗️ Architecture
The project is split into three decoupled projects to simulate a real-world microservice environment:

Plaintext
genSeven-workspace/
├── 📦 scala-utils (Project A)
│   ├── Level: Low-level Shared Library
│   └── Role: Common helpers (Time, String manipulation)
│
├── 📦 scala-models (Project B)
│   ├── Level: Shared Data Schema
│   └── Role: Case Classes, Circe JSON Codecs, DTOs
│   └── Dependency: Depends on `scala-utils`
│
└── 🚀 snipit (Project C)
    ├── Level: The Main Application
    ├── Module: Core (Pure Business Logic)
    ├── Module: Backend (Cask Server + Slick/Postgres)
    └── Dependency: Depends on `scala-models`
🛠️ Tech Stack
Language: Scala 2.13.12

Build Tool: SBT 1.9.7

Server: Cask (Synchronous, Flask-like)

Database: PostgreSQL 13

ORM: Slick 3.4.1 (Raw SQL Mapping)

JSON: Circe 0.14.6

Containerization: Docker & Docker Compose

Base Image: Amazon Corretto 17 (Alpine) - Apple Silicon Compatible

⚡ Getting Started (The Fast Way)
We have fully dockerized the ecosystem. You can run the entire backend (App + Database) with one command.

1. Prerequisites

Docker installed and running.

SBT installed (for building the JAR).

2. Build the Application Image

Before running Docker Compose, we must compile the code into a Fat JAR and build the container image.

Bash
# 1. Publish libraries locally (Required for compilation)
cd scala-utils  && sbt clean publishLocal && cd ..
cd scala-models && sbt clean publishLocal && cd ..

# 2. Build the Fat JAR
cd snipit
sbt "project backend" assembly

# 3. Build the Docker Image
docker build -t snipit-backend:v1 -f backend/Dockerfile .
3. Launch the Ecosystem

Go back to the root workspace folder and start the cluster.

Bash
cd ..
docker-compose up
Database: Starts on port 5432.

Backend: Starts on port 8080.

Networking: The backend automatically discovers the database via the internal Docker network snipit-net.

🔌 API Documentation
Once the containers are running, you can interact with the API via localhost:8080.

1. Register a User

Bash
curl -X POST http://localhost:8080/api/register \
   -H "Content-Type: application/json" \
   -d '{"username": "dockerUser", "password": "password123"}'
2. Create a Snippet

Copy the id from the registration response.

Bash
curl -X POST http://localhost:8080/api/snippets \
   -H "Content-Type: application/json" \
   -d '{
         "userId": "REPLACE_WITH_UUID",
         "title": "Dockerized Scala",
         "code": "println(\"Hello from inside the container!\")",
         "language": "scala"
       }'
3. List Snippets

Bash
curl http://localhost:8080/api/snippets/REPLACE_WITH_UUID
📁 Project Structure (Details)
snipit/core: Contains the Repository trait. Pure Scala logic.

snipit/backend: Contains PostgresDB (Slick implementation) and Server (Cask Routes).

application.conf: Configured to accept environment variables (DB_USER, DB_PASSWORD) which are injected automatically by docker-compose.yml.
