# Prescription Management - Microservice

I have created a microservice project for prescription management processes, allowing doctors to perform tasks such as creating prescriptions, adding medications, and listing prescriptions.
<img src="screenshots/main_diagram.drawio.svg" alt="Main Information" width="800" height="500">


## Using Tools & Technologies
* Spring boot
* Rest Api
* HashiCorp Vault
* Eureka Server
* Resilience4j
* Zipkin
* Feign Client
* Docker
* Actuator
* Open Api
* Lombok
* Mapstruct


### Explore Rest APIs
<table style="width:100%">
  <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Description</th>
      <th>Valid Request Body</th>
      <th>Valid Request Path Variables</th>
  </tr>
  <tr>
      <td>POST</td>
      <td>http://localhost:8889/api/v1/prescription</td>
      <td>Create a new prescription</td>
      <td><a href="README.md#create_prescription">Info</a></td>
      <td></td>
  </tr>
  <tr>
      <td>PUT</td>
      <td>http://localhost:8889/api/v1/prescription</td>
      <td>Add Medication to Prescription</td>
      <td><a href="README.md#add_medication">Info</a></td>
      <td></td>
  </tr>
  <tr>
      <td>GET</td>
      <td>http://localhost:8889/api/v1/prescription/{prescription_uuid}</td>
      <td>Get a Prescription by ID</td>
      <td></td>
      <td><a href="README.md#get_prescription">Info</a></td>
  </tr>
</table>

---
### HashiCorp Vault

```
    http://localhost:8200/ui/vault/auth?with=token
```
NOT: dev mode token: root-token

---
### Open Api (Swagger)

```
  http://localhost:8081/prescription-service/swagger-ui/index.html
```
---
### Actuator

```
  http://localhost:8081/actuator/
```

---
### Zipkin (Log tracing)

```
  http://localhost:9411/zipkin/
```

---
### Eureka - server

```
  http://localhost:8761/
```
---
#### RUN THE APPLICATION

##### Run Docker:
```
$ cd parkinglot
$ docker-compose up
```
##### Run Maven:
```
$ mvn clean install
$ mvn spring-boot:run
```
---
## Valid Request Body

##### <a id="create_prescription"> Create a new prescription
```
    http://localhost:8889/api/v1/prescription    
    {
      "prescriptionNo": "C-21",
      "doctorName": "Dr. Mehmet Öz"
    }
```

##### <a id="add_medication"> Add Medication to Prescription
```
    http://localhost:8889/api/v1/prescription
    {
      "prescriptionId": <prescription_uuid>,
      "medicationSerialNumber": "Aspirin-101"
    } 
```
NOT: prescription_uuid : The prescription UUID value received in response to the HTTP POST request made to create the prescription.

## Valid Request Path Variables

##### <a id="get_prescription"> Get a Prescription by ID
```
    http://localhost:8889/api/v1/prescription/{prescription_uuid}
```
NOT: prescription_uuid : The prescription UUID value received in response to the HTTP POST request made to create the prescription.