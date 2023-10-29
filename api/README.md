
# Backend Take Home Assignment

## Instructions to Run the Code

### Using Docker Hub (Recommended)

If you have Docker installed and prefer to use the pre-built Docker image from Docker Hub, simply run the following command:

```bash
sudo docker run -p 8080:8080 gabrielbgarcia/backend-take-home:latest
```

### Building and Running from Scratch

1. Clone this repository and navigate to the 'api' directory:
```bash
cd api
```
2. Build the project using Maven:
```bash
mvn clean package
```
3. Build the Docker image:
```bash
sudo docker build -t gabrielbgarcia/backend-take-home:latest .
```
4. Run the Docker container, exposing the API on port 8080:
```bash
sudo docker run -p 8080:8080 gabrielbgarcia/backend-take-home:latest
```
The API should now be up and running at http://localhost:8080.

## Main Technical Decisions
* Framework: The project is built with Spring Boot. I chose Spring Boot due to its robustness, extensive community support, and our familiarity with Java.

* Scalability: The architecture is designed with scalability in mind, making it easy to add a database or other components in the future.

* Containerization: We use Docker for containerization, which ensures consistency and easy deployment.