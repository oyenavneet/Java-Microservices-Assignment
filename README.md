# Order Management Microservice

A simple Order Management microservice built using **Spring Boot** to demonstrate Java fundamentals, REST API design, and microservices best practices.

## Features
- Create a new order
- Fetch order details by ID
- Update order status with defined state transitions
- List all orders
- In-memory data storage (no database)

## Tech Stack
- Java 17
- Spring Boot
- Lombok
- JUnit 5 & Mockito
- Maven

## Order Fields
- `orderId` (String)
- `customerName` (String)
- `amount` (Double)
- `status` (NEW, PROCESSING, COMPLETED)

## API Endpoints

| Method | Endpoint | Description |
|------|---------|-------------|
| POST | `/orders` | Create a new order |
| GET | `/orders/{orderId}` | Get order by ID |
| PUT | `/orders/{orderId}/status` | Update order status |
| GET | `/orders` | List all orders |

## Status Transition Rules
- `NEW → PROCESSING`
- `PROCESSING → COMPLETED`

## Error Handling
- **404 Not Found** – Order not found
- **400 Bad Request** – Missing or invalid request fields
- Validation for mandatory fields and invalid amounts (≤ 0)

## Testing
- Unit test added for the service layer using **JUnit** and **Mockito**
- Ensures core business logic is tested in isolation

## Design Notes
- Layered architecture (Controller, Service, Model)
- DTOs used to decouple API contracts from internal models
- Lombok reduces boilerplate code and improves readability
- Centralized exception handling using `@RestControllerAdvice`

## Running the Application

```bash
mvn clean install
mvn spring-boot:run
