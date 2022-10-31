# Time-Tracker-backend

***

## DESCRIPTION:
Backend part for timetracker app.
***

## HOW TO RUN: 
mvmv clean spring-boot:run  
***

## COMMANDS

***

### **EMPLOYEE COMMANDS:**

**GET LIST OF EMPLOYEES**  

- curl http://localhost:8080/timetracker/employee/all  

**GET EMPLOYEE BY id**  

- curl http://localhost:8080/timetracker/employee/id  

**GET EMPLOYEE WITH employee_name**   

- curl http://localhost:8080/timetracker/employee/all?name=employee_name  

**POST NEW EMPLOYEE WITH employee_name**  

- curl -X POST http://localhost:8080/timetracker/employee -H "Content-type:application/json" -d "{\"name\": \"employee_name\"}"  

**EDIT EXISTING EMPLOYEE BY id**  

- curl -X PUT http://localhost:8080/timetracker/employee/id -H "Content-type:application/json" -d "{\"name\":\"employee_name\"}"  

**DELETE EXISTING EMPLOYEE BY id**  

- curl -X DELETE http://localhost:8080/timetracker/employee/id  

***

### **TASK COMMANDS:**

**GET LIST OF TASKS**  

- curl http://localhost:8080/timetracker/task/all  
 
**GET TASK BY id**  

- curl http://localhost:8080/timetracker/task/id
  
**GET TASK WITH task_name**   

- curl http://localhost:8080/timetracker/task/all?name=task_name  
 
**GET TASK ENDED BETWEEN start_date AND end_date**  

- curl http://localhost:8080/timetracker/task/all?start=2022-09-01T00:00:00&end=2022-12-01T00:00:00  
  
**POST NEW TASK WITH task_name AND task_description TO EMPLOYEE BY id**  

- curl -X POST http://localhost:8080/timetracker/employee/id/task -H "Content-type:application/json" -d "{\"name\": \"task_name\", \"description\":\"task_description\"}"  
  
**EDIT EXISTING TASK BY id** 

- curl -X PUT http://localhost:8080/timetracker/task/id -H "Content-type:application/json" -d "{\"name\": \"task_name\", \"description\":\"task_description\"}"  
  
**DELETE EXISTING TASK BY id**  

- curl -X DELETE http://localhost:8080/timetracker/task/id  

**START AND END TASK**  

- curl http://localhost:8080/timetracker/task/id/start  
- curl http://localhost:8080/timetracker/task/id/end  

