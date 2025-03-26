# 💰 Expense Tracker API

An efficient **Expense Tracker API** built with **Spring Boot & MySQL** that allows users to manage expenses effortlessly. 🚀

## 📌 Features

✅ **User Registration & Management**  
✅ **Expense CRUD Operations**  
✅ **Filter Expenses by Category, Date, or Name**  
✅ **Pagination & Sorting Support**  
✅ **User-specific Expense Tracking**  
✅ **Swagger API Documentation for Visualization**  

---

## 🛠️ Tech Stack

| Technology   | Description |
|-------------|------------|
| ☕ **Backend**  | Spring Boot (Java) |
| 🛢️ **Database** | MySQL |
| 📄 **API Documentation** | Swagger UI |
| 🔨 **Build Tool** | Maven |
| 🖥️ **IDE Used** | IntelliJ IDEA |

---

## 📝 Setup Instructions

### 1️⃣ Clone the Repository

```sh
git clone https://github.com/vishnubansode/expense-tracker-api.git
cd expense-tracker-api


📝 Setup Instructions

Clone the Repository

git clone https://github.com/your-github-username/expense-tracker-api.git
cd expense-tracker-api

Configure Database (MySQL)

Create Database:
CREATE DATABASE expensetracker

Update application.properties with your MySQL credentials:

spring.application.name=ExpenseTracker
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/expensetracker
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect


springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html


Run the Application

mvn spring-boot:run

Access Swagger UI

Open: http://localhost:8080/swagger-ui/index.html

👤 About the Developer

Name: Vishnu Bansode

Email: bansodevishnu24@gmail.com

🌟 Feel free to contribute or raise issues! Happy coding! 🚀

