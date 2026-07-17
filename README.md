# Java Legacy Order Processor

## Overview

**Java Legacy Order Processor** is a lightweight Spring Boot microservice intentionally built using legacy Java coding practices commonly found in enterprise applications.

The project is designed to serve as an input for an AI-powered Java modernization agent. It contains intentionally introduced legacy patterns that can be detected, analyzed, and modernized based on a user-selected target Java version.

Unlike a toy example, this is a fully runnable REST microservice while remaining small enough to easily understand the modernization process.

---

# Project Objectives

- Demonstrate AI-assisted Java modernization.
- Detect legacy coding patterns using AST analysis.
- Modernize source code based on the selected target Java version.
- Generate modernization reports.
- Produce automated Pull Requests with transformed code.
- Showcase incremental modernization of enterprise applications.

---

# Technology Stack

| Component | Version |
|-----------|---------|
| Java | Legacy Java (Java 7/8 compatible) |
| Spring Boot | 2.7.x |
| Maven | 3.x |
| REST API | Spring MVC |
| Build Tool | Maven |

---

# Project Structure

```
java-legacy-order-processor

src
└── main
    ├── java
    │   └── com.demo.order
    │       ├── OrderApplication.java
    │       ├── Order.java
    │       ├── OrderController.java
    │       ├── OrderService.java
    │       ├── LegacyOrderProcessor.java
    │       └── LegacyUtils.java
    │
    └── resources
        └── application.properties
```

---

# Application Flow

```
REST API

      │

      ▼

OrderController

      │

      ▼

OrderService

      │

      ▼

LegacyOrderProcessor

      │

      ▼

LegacyUtils
```

---

# Legacy Coding Patterns Included

The following legacy coding practices are intentionally included for AI-driven modernization.

| Category | Legacy Pattern | Example Modernization |
|----------|----------------|-----------------------|
| Collections | Raw Collections | Generic Collections |
| Collections | Vector | List / ArrayList |
| Collections | Hashtable | ConcurrentHashMap / Map |
| Collections | Enumeration | Iterator / Stream API |
| Collections | Manual Iteration | Stream API |
| Object Model | Mutable POJO | Records (where applicable) |
| Object Creation | Manual Setters | Builder Pattern / Immutable Objects |
| Concurrency | Thread | Virtual Threads |
| Concurrency | Runnable | Lambda Expressions |
| Concurrency | synchronized | Modern Concurrency Utilities |
| Blocking Operations | Thread.sleep() | CompletableFuture / Async APIs |
| Callback | Anonymous Callback Classes | CompletableFuture |
| Null Handling | Explicit Null Checks | Optional |
| String Handling | StringBuffer | StringBuilder |
| String Handling | String Concatenation | String Formatting |
| Type Safety | Raw Types | Generic Types |
| Type Safety | Unchecked Casts | Safe Generic Collections |
| Date API | java.util.Date | java.time API |
| Date API | Calendar | LocalDate / LocalDateTime |
| Dependency Injection | Field Injection | Constructor Injection |

# Legacy Patterns and Modernization Targets

The application intentionally includes several legacy Java coding patterns that are commonly found in enterprise applications. These patterns provide meaningful modernization opportunities for the AI Agent.

| Legacy Pattern | Introduced | Java 8 Modernization | Java 11 Modernization | Java 14 Modernization | Java 17 Modernization | Java 21 Modernization |
|----------------|------------|----------------------|-----------------------|-----------------------|-----------------------|-----------------------|
| Anonymous Inner Classes | Java 1.1 | Lambda Expressions | Lambda Expressions | Lambda Expressions | Lambda Expressions | Lambda Expressions |
| Raw Collections | Before Java 5 | Generic Collections | Generic Collections | Generic Collections | Generic Collections | Generic Collections |
| Hashtable | JDK 1.0 | HashMap | HashMap | HashMap | ConcurrentHashMap | ConcurrentHashMap |
| Vector | JDK 1.0 | ArrayList | ArrayList | ArrayList | List Interface Improvements | Immutable Collections (`List.of()`) |
| Enumeration | JDK 1.0 | Iterator | Iterator | Enhanced For Loop | Streams | Streams |
| StringBuffer | JDK 1.0 | StringBuilder | StringBuilder | StringBuilder | String Formatting | String Templates (Preview) |
| Calendar | JDK 1.1 | java.time API | java.time API | java.time API | java.time API | java.time API |
| Date | JDK 1.0 | java.time API | java.time API | java.time API | java.time API | java.time API |
| synchronized | JDK 1.0 | ExecutorService | ExecutorService | ExecutorService | CompletableFuture | Virtual Threads |
| Thread | JDK 1.0 | ExecutorService | CompletableFuture | CompletableFuture | CompletableFuture | Virtual Threads |
| Runnable | JDK 1.0 | Lambda Expressions | Lambda Expressions | Lambda Expressions | CompletableFuture | Virtual Threads |
| Explicit Null Checks | Always | Optional | Optional | Optional | Optional | Pattern Matching (where applicable) |
| Manual Loop-based Iteration | Always | Stream API | Stream API | Stream API | Stream API | Stream API |
| switch Statement | Always | Traditional Switch | Traditional Switch | Switch Expressions | Enhanced Switch Expressions | Pattern Matching for Switch |
| Mutable POJO | Always | Java Bean | Java Bean | Cleaner POJO | Immutable Classes | Records |
| Callback Interfaces | Always | CompletableFuture | CompletableFuture | CompletableFuture | CompletableFuture | Structured Concurrency (Preview) |
| Field Injection | Spring | Constructor Injection | Constructor Injection | Constructor Injection | Constructor Injection | Constructor Injection |
| String Concatenation (`+`) | Always | StringBuilder | StringBuilder | StringBuilder | `String::formatted()` | String Templates (Preview) |
| Raw Type Casting | Before Java 5 | Generic Collections | Generic Collections | Generic Collections | Generic Collections | Generic Collections |
| Unchecked Casts | Before Java 5 | Safe Generics | Safe Generics | Safe Generics | Safe Generics | Safe Generics |
| Blocking Calls (`Thread.sleep`) | JDK 1.0 | ExecutorService | CompletableFuture | CompletableFuture | CompletableFuture | Virtual Threads |

> **Note:** The AI Agent applies only the transformations that are supported by the selected target Java version while preserving application behavior.
---

# Legacy Pattern Location

| File | Legacy Patterns |
|------|-----------------|
| Order.java | Mutable POJO, Getters, Setters, equals(), hashCode(), toString() |
| OrderController.java | REST Controller, Field Injection |
| OrderService.java | Loop-based Iteration, Raw Types, Unchecked Casts, Null Checks, StringBuffer, String Concatenation, Callback Pattern |
| LegacyOrderProcessor.java | Thread, Runnable, synchronized, Blocking Calls |
| LegacyUtils.java | Vector, Hashtable, Enumeration, Date, Calendar |
| OrderApplication.java | Spring Boot Application Bootstrap |

---

# Supported Java Modernization Targets

The AI Agent modernizes the application based on the selected target Java version.

| Target Version | Example Modernization Opportunities |
|----------------|-------------------------------------|
| **Java 8** | Lambda Expressions, Stream API, Optional, Method References, java.time API, Generics Improvements, Default Interface Methods |
| **Java 11** | Local Variable Type Inference (`var`), New String APIs (`isBlank()`, `lines()`, `repeat()`), HTTP Client API, Files API Improvements |
| **Java 14** | Switch Expressions, Helpful NullPointerException Messages, Records (Preview), Pattern Matching for `instanceof` (Preview) |
| **Java 17 (LTS)** | Records, Pattern Matching for `instanceof`, Sealed Classes, Enhanced Switch Expressions, Strong Encapsulation |
| **Java 21 (LTS)** | Virtual Threads, Pattern Matching for Switch, Record Patterns, Sequenced Collections, Structured Concurrency (Preview), Scoped Values (Preview), String Templates (Preview) |

> **Note:** The modernization performed depends on the selected target Java version and the compatibility requirements of the application.

---

# AI Modernization Workflow

```
Git Repository

        │

        ▼

Repository Scanner

        │

        ▼

AST Analyzer

        │

        ▼

Legacy Pattern Detection

        │

        ▼

RAG Knowledge Retrieval

        │

        ▼

LLM Code Modernization

        │

        ▼

Modernized Source Code

        │

        ▼

Modernization Report

        │

        ▼

Git Branch + Pull Request
```

---

# REST Endpoints

## Process Orders

```
POST /orders/process
```

Processes one or more customer orders.

---

## Get All Orders

```
GET /orders
```

Returns all processed orders.

---

## Get Order By ID

```
GET /orders/{id}
```

Returns a specific order.

---

## Delete Order

```
DELETE /orders/{id}
```

Deletes an order by ID.

---

# Running the Application

## Build

```bash
mvn clean install
```

## Start

```bash
mvn spring-boot:run
```

Application URL

```
http://localhost:8080
```

---

# AI Modernization Goals

The AI Agent should automatically detect and modernize:

- Loop-based iteration
- Mutable POJO classes
- Anonymous inner classes
- Thread / Runnable
- synchronized methods
- Blocking calls
- Callback implementations
- Explicit null checks
- Raw collections
- Unchecked casts
- StringBuffer
- String concatenation
- Vector
- Hashtable
- Enumeration
- java.util.Date
- Calendar
- Manual object creation
- Field injection
- Legacy REST coding style

---

# Repository Purpose

This repository intentionally preserves legacy Java coding practices and is **not intended to represent modern Java development**.

Its purpose is to serve as a realistic input for AI-powered modernization tools capable of:

- Scanning source code
- Detecting legacy patterns
- Retrieving modernization knowledge
- Refactoring source code
- Generating modernization reports
- Creating automated Pull Requests

---

# License

This project is intended for educational, demonstration, and research purposes.
