# RoomEase
RoomEase is an Android application designed to streamline service requests for college hostel students. It provides a user-friendly, ticket-based system for tracking and resolving maintenance issues, simplifying communication between students, administrators, and workers.

## Features
- Ticket-Based System: Students can easily create service requests for various issues, including:
  - Room Cleaning
  - AC/Cooler Maintenance
  - Electrical Repairs
  - Furniture Repairs

- Secure Authentication: Users can sign in securely using their Google accounts, powered by Firebase Authentication.
- Two-Way Communication: A streamlined system allows administrators to receive requests, assign available workers, and update the status of tickets.
- Issue Tracking: Students can track the progress of their requests from submission to resolution, ensuring transparency.
- Admin and Worker Interface: Enables administrators and workers to manage and close service tickets once the issue is resolved.


## Tech Stack & Architecture
### Mobile Application (Android)
- Language: Kotlin
- UI: Jetpack Compose
- Architecture: Model-View-ViewModel (MVVM)
- Dependency Injection: Koin•Networking: Ktor Client
- Authentication: Firebase Authentication (Google Sign-In)

### Backend
- Framework: Spring Boot (Java)
- Architecture: Model-View-Controller (MVC)
- Database: MySQL

## App Screenshots


| Light | Dark |
|---|---|
| ![Login_Light](https://github.com/user-attachments/assets/ad1d240c-5317-4613-bc60-6833a1e5c4ba) | ![Login_Dark](https://github.com/user-attachments/assets/6cbcaba4-b184-4ffc-af44-5c30ef7fc118) |
| ![Details_Light](https://github.com/user-attachments/assets/c4fe7e72-e9b9-419c-b86f-1d4177d348d2) | ![Details_Dark](https://github.com/user-attachments/assets/0851059e-c0ac-4cd9-b097-6ed2431a156b) |
| ![Home_Light](https://github.com/user-attachments/assets/27a348eb-a96b-4d4d-ab3d-909cc6f78f8f) | ![Home_Dark](https://github.com/user-attachments/assets/4cd820c2-7645-453e-8735-680991fba5f6) |
| ![Create_Light](https://github.com/user-attachments/assets/aa0a4af0-be0c-4cce-9b12-92c3240e678a) | ![Create_Dark](https://github.com/user-attachments/assets/afa54c04-c995-41c3-90cc-6ef69b79861d) |
| ![Tickets_Light](https://github.com/user-attachments/assets/d1672fb5-fad1-4325-9961-0403b3d4111b) | ![Tickets_Dark](https://github.com/user-attachments/assets/f6490c5b-e31c-49e8-a246-490c0f455e1f) |
| ![Services_Light](https://github.com/user-attachments/assets/e0b9f603-809d-4e2c-8fb6-3420e3193954) | ![Services_Dark](https://github.com/user-attachments/assets/8d57fb9e-105c-41c0-8b81-65de4a0c36a7) |

---

