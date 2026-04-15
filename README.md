#  Smart Queue Management System

A full-stack web application that digitizes queue management for places like hospitals, banks, and service centers.  
It allows users to join queues remotely, track their position, and view queue status in real-time.

---

##  Problem Statement

Traditional queue systems require users to wait physically without knowing their turn, leading to time wastage and inefficiency.  

This project solves that problem by providing a digital queue system where users can:
- Join queues online
- Get token numbers
- Track their position dynamically

---

##  Tech Stack

### Frontend
- React.js

### Backend
- Spring Boot (Java)

### Database
- MySQL

---

##  System Architecture

Frontend (React) → Backend (Spring Boot APIs) → Database (MySQL)

- React sends API requests
- Spring Boot processes logic
- MySQL stores queue data
- Response is sent back to UI

---

##  Features

###  User
- Join queue
- Get token number
- Check position
- View dashboard

###  Admin
- Move queue forward (Next Token)

###  System
- Real-time token generation
- Position tracking
- Queue status display

---

##  Database Design

The system uses 4 main tables:

- **User**
- **ServiceCenter**
- **Queue**
- **Token**

### Relationships:
- One ServiceCenter → Many Queues  
- One Queue → Many Tokens  
- One User → Many Tokens  

---

##  API Endpoints

| Method | Endpoint | Description |
|--------|--------|------------|
| POST | `/queue/join` | Join queue & get token |
| GET | `/queue/position` | Get user position |
| GET | `/queue/dashboard` | View queue status |
| POST | `/queue/next` | Move queue forward |

---

##  Core Logic

- **Token Generation:**  
  Next token = last token + 1  

- **Position Calculation:**  
  Position = Token Number - Current Token  

- **Queue Movement:**  
  Current token increases when admin clicks "Next"

---

##  How to Run the Project

### 1️ Backend (Spring Boot)

- Open project in IDE
- Configure MySQL in `application.properties`
- Run `SmartqueueApplication.java`

---

### 2 Frontend (React)

```bash
cd smartqueue-frontend
npm install
npm start
```
## URLs

- Frontend → http://localhost:3000  
- Backend → http://localhost:8080  

---

##  Future Enhancements

- Facility selection (Hospital/Bank)
- Real-time updates using WebSockets
- Notification system
- Authentication (JWT)

---

##  Author

Ridhi
