# Employee Payroll Management System

**Student:** Rukundo Levis
**Student ID:** 27507

A Spring Boot REST API for managing employee payroll. Payroll officers create payslips for employees. A payslip moves from DRAFTED to REVIEWED, then ends as APPROVED or REJECTED. Disbursements can only be recorded for APPROVED payslips.

## Technologies

- Java 21
- Spring Boot (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven

## Entities

PayrollArea, Branch, PayrollOfficer, Employee, Payslip, Disbursement

## Steps to run

1. Create the database in PostgreSQL:
   ```sql
   CREATE DATABASE employee_payroll_db;
   ```
2. Set your PostgreSQL username and password in `src/main/resources/application.properties`.
3. Run the application:
   ```
   mvn spring-boot:run
   ```
4. The API runs on `http://localhost:8080`. Import `employee-payroll-management-system-midterm.postman_collection.json` into Postman to test it. It has one folder for each part, A to F.
