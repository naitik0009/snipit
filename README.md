🚀 SnipIt Backend (GenSeven Ecosystem)
SnipIt is a distributed Code Snippet Manager built with a professional Scala Multi-Module Architecture.

It demonstrates an Enterprise-grade setup using Shared Libraries, Local Artifact Publishing, Layered Architecture, and Dockerized Persistence.

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

Build Tool: SBT (Simple Build Tool) 1.9.7

Server: Cask (Synchronous, Flask-like)

Database: PostgreSQL 13 (via Docker)

ORM/Driver: Slick 3.4.1 (Raw SQL Mapping)

JSON: Circe 0.14.6

Security: JBcrypt (Password Hashing)

⚡ Getting Started
1. Prerequisites

Java 17+ installed.

SBT installed.

Docker installed and running.

2. Start the Infrastructure

Spin up the PostgreSQL database container.

Bash
docker-compose up -d
3. Build the Dependency Chain

Since this utilizes a private local ecosystem, you must publish the shared libraries to your local Ivy cache before running the app.

Step A: Publish Utils

Bash
cd scala-utils
sbt clean publishLocal
Step B: Publish Data Models

Bash
cd ../scala-models
sbt clean publishLocal
4. Run the Application

Now that dependencies are resolved, run the main server.

Bash
cd ../snipit
sbt "project backend" run
You should see: 🚀 Server online at http://localhost:8080/

🔌 API Documentation
You can interact with the API using curl.

1. Register a User

Bash
curl -X POST http://localhost:8080/api/register \
   -H "Content-Type: application/json" \
   -d '{"username": "naitik", "password": "password123"}'
2. Create a Snippet

Copy the id from the registration response.

Bash
curl -X POST http://localhost:8080/api/snippets \
   -H "Content-Type: application/json" \
   -d '{
         "userId": "REPLACE_WITH_UUID",
         "title": "Scala Hello World",
         "code": "println(\"Hello GenSeven\")",
         "language": "scala"
       }'
3. List Snippets

Bash
curl http://localhost:8080/api/snippets/REPLACE_WITH_UUID
📁 Project Structure (Details)
snipit/core: Contains the Repository trait. It knows what needs to be done but not how. Pure Scala.

snipit/backend: Contains PostgresDB (Slick implementation) and Server (Cask Routes). Handles the "Dirty Details" of HTTP and SQL.

application.conf: Handles Database credentials with support for Environment Variable overrides (${?DB_USER}).
