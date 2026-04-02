# BookMyStayApp
# 🏨 Book My Stay App

### (Core Java + Data Structures Project)

---

## 📌 Overview

The **Book My Stay App** is a console-based Hotel Booking Management System designed to demonstrate the practical use of **Core Java** and **Data Structures** in solving real-world problems.

The project is built incrementally using multiple use cases, where each use case introduces a new concept such as **collections, OOP, data handling, and system design principles**.

The focus is on **logic, data handling, and system behavior**, rather than UI.

---

## 🎯 Objectives

* Apply **Java Collections Framework** in real scenarios
* Understand **data structure selection based on problems**
* Implement **object-oriented design principles**
* Simulate **real-world booking system behavior**
* Build a scalable and maintainable system

---

## 🧠 Key Concepts Covered

* OOP (Encapsulation, Abstraction, Polymorphism)
* ArrayList, LinkedList, HashSet, LinkedHashSet
* HashMap (Key–Value Mapping)
* Queue (FIFO), Stack (LIFO)
* Exception Handling
* File Handling (Persistence)
* Multithreading (Concurrency)
* Design Patterns (Strategy Pattern)

---

## 📂 Project Structure

All use cases are implemented inside a single main class:

```
BookMyStay.java
```

---

## 🚀 Use Cases Implemented

### ✅ UC1: Application Entry

* Displays welcome message
* Introduces program flow

---

### ✅ UC2: Room Types & Availability

* Uses OOP (Room classes)
* Stores room availability

---

### ✅ UC3: Centralized Inventory

* Uses **HashMap**
* Single source of truth for room availability

---

### ✅ UC4: Room Search

* Read-only operations
* Filters available rooms

---

### ✅ UC5: Booking Request Queue

* Uses **Queue (FIFO)**
* Ensures fair request handling

---

### ✅ UC6: Reservation Confirmation

* Uses **Set + HashMap**
* Prevents double booking

---

### ✅ UC7: Add-On Services

* Uses **Map + List**
* Supports multiple services per booking

---

### ✅ UC8: Booking History

* Uses **List**
* Stores past bookings

---

### ✅ UC9: Error Handling

* Custom exceptions
* Input validation

---

### ✅ UC10: Cancellation & Rollback

* Uses **Stack (LIFO)**
* Restores system state

---

### ✅ UC11: OOP Service Layer

* Encapsulates logic in classes
* Follows Single Responsibility Principle

---

### ✅ UC12: Strategy Pattern

* Dynamic algorithm selection
* Uses interfaces and polymorphism

---

### ✅ UC13: Performance Comparison

* Uses `System.nanoTime()`
* Compares different approaches

---

## ⚙️ Technologies Used

* Java (JDK 8+)
* IntelliJ IDEA / VS Code
* Git & GitHub

---

## ▶️ How to Run

1. Clone the repository:

   ```
   git clone <your-repo-link>
   ```

2. Open in IntelliJ / VS Code

3. Run:

   ```
   BookMyStay.java
   ```

---

## 💡 Key Learnings

* Choosing the right data structure is critical
* Separation of concerns improves scalability
* Real-world systems require validation and consistency
* Data structures solve practical engineering problems

---

## 📈 Future Enhancements

* Database integration (MySQL)
* GUI (JavaFX / Web UI)
* REST API integration
* Payment system simulation

---

## 👨‍💻 Author

**Akaash**

---

## 📄 License

This project is for educational purposes.
