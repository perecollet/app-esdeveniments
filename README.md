# app-esdeveniments

**Bachelor's Final Project** — Computer Engineering in Information Systems Management  
**Author:** Pere Collet

A full-stack mobile application for managing sporting and leisure events. Users can create events, browse available events, join or leave them, and download a pre-filled PDF registration form for each event they participate in.

---

## Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [API Endpoints](#api-endpoints)

---

## Overview

`app-esdeveniments` is a cross-platform mobile application targeting the sports and leisure sector. It allows registered users to manage their participation in events, view event details on a map, and automatically generate PDF registration forms via Apache PDFBox.

---

## Architecture

The backend follows **Hexagonal Architecture** (Ports & Adapters), structured as a multi-module Gradle project:

```
server/
├── common/               # Shared annotations, base classes (UseCase, WebAdapter, PersistenceAdapter)
├── events-application/   # Domain layer: entities, use cases, ports (in/out), services
├── adapters/
│   ├── events-persistence/  # JPA persistence adapter (PostgreSQL / H2)
│   └── events-web/          # REST controllers (web adapter)
└── events-configuration/    # Spring Boot entry point, wires all modules together
```

This structure enforces a strict dependency rule: the domain (`events-application`) has zero knowledge of persistence or web concerns.

---

## Tech Stack

### Backend
| Technology | Version | Purpose |
|---|---|---|
| Java | 11 | Primary language |
| Spring Boot | 2.2.6 | Application framework |
| Spring Security | 5.3.3 | Authentication & session management |
| Spring Session | 1.3.5 | HTTP session handling |
| Spring Data JPA | 2.2.6 | ORM layer |
| PostgreSQL | latest | Production database |
| H2 | runtime | In-memory database for development |
| Apache PDFBox | 2.0.20 | PDF form generation |
| Lombok | latest | Boilerplate reduction |
| Jackson | 2.11.0 | JSON serialization |
| Joda-Time | 2.10.6 | Date/time handling |
| Gradle | 6.x | Build system |

### Frontend
| Technology | Version | Purpose |
|---|---|---|
| Ionic Framework | 5.x | UI component library |
| Angular | 8.2 | SPA framework |
| TypeScript | 3.4 | Primary language |
| Capacitor | 2.0.1 | Native Android bridge |
| Angular Google Maps (`@agm/core`) | 1.1.0 | Map integration |
| RxJS | 6.5 | Reactive streams |

---

## Project Structure

```
app-esdeveniments/
├── ionic-esdeveniments/        # Frontend (Ionic + Angular)
│   └── src/app/
│       ├── model/              # Event and User TypeScript models
│       ├── services/           # HTTP services (AuthService, EventService)
│       └── pages/
│           ├── login/
│           ├── register/
│           ├── list-events/
│           ├── event-detail/
│           ├── create-event/
│           └── profile/
├── server/                     # Backend (Spring Boot, Hexagonal Architecture)
│   ├── common/
│   ├── events-application/
│   ├── adapters/
│   │   ├── events-persistence/
│   │   └── events-web/
│   └── events-configuration/
└── Formularis esdeveniments/   # Sample PDF registration forms
```

---

## Features

- **User registration & authentication** — session-based auth via Spring Security
- **Event listing** — browse all available sporting and leisure events
- **Event detail** — view location on Google Maps, description, and participant list
- **Create event** — authenticated users can publish new events
- **Join / Leave event** — manage participation in existing events
- **PDF form generation** — automatically fills a registration PDF with participant data (Apache PDFBox)
- **User profile** — view and update personal information
- **Android support** — packaged as a native Android app via Capacitor

---

## Getting Started

### Backend

**Prerequisites:** Java 11, Gradle, PostgreSQL

```bash
cd server

# Run with H2 in-memory database (development)
./gradlew :events-configuration:bootRun

# Build the JAR
./gradlew build
```

Configure your database connection in `events-configuration/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/esdeveniments
spring.datasource.username=your_user
spring.datasource.password=your_password
```

The server is deployed on **Heroku** (`Procfile` included).

### Frontend

**Prerequisites:** Node.js, npm, Ionic CLI

```bash
cd ionic-esdeveniments

npm install

# Run in browser
ionic serve

# Build for Android
ionic build
npx cap sync android
npx cap open android
```

---

## API Endpoints

Base URL: `http://localhost:8080`

| Method | Path | Description |
|---|---|---|
| `POST` | `/users` | Register a new user |
| `GET` | `/users` | Get all users |
| `PUT` | `/users/{id}` | Update user information |
| `DELETE` | `/users/{id}` | Delete a user |
| `GET` | `/events` | List all events |
| `POST` | `/events` | Create a new event |
| `POST` | `/events/{id}/join` | Join an event |
| `POST` | `/events/{id}/leave` | Leave an event |
| `GET` | `/events/{id}/pdf` | Download pre-filled PDF registration form |
