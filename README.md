
# Backend Take Home Assignment

## Instructions to Run the Code

### Building and Running from Scratch ( if you have docker )

1. Clone this repository, open a terminal and navigate to the 'api' directory:
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

### Building and Running from Scratch ( if you don't have docker )

1. Clone this repository, open a terminal and navigate to the 'api' directory:
```bash
cd api
```
2. Build the project using Maven:
```bash
mvn clean package
```
3. After building the project with Maven (step 2 in the previous section), navigate to the 'target' directory:
```bash
cd target
```
4. Run the JAR file to start the API:
```bash
java -jar backend-take-home-0.0.1-SNAPSHOT.jar

```
The API should now be up and running at http://localhost:8080.


## Importing Postman Collection

You can easily import the provided Postman collection into Postman to test the API endpoints. Follow these steps to do so:

1. Open Postman, and make sure you have Postman installed. If you don't have Postman, you can download it [here](https://www.postman.com/downloads/).

2. Click on the "Import" button located in the upper-left corner of the Postman application.

3. In the Import dialog, select the "Upload Files" tab.

4. Click the "Choose Files" button and navigate to the 'src/main/resources' folder in this repository.

5. Select the Postman collection file (Backend-Take-Home.postman_collection.json) and click "Open" to upload it.

6. Once the file is uploaded, click the "Import" button.

7. You should now see the imported the collection in your Postman workspace.


## Main Technical Decisions

In the development of this API, I made several key technical decisions to ensure its efficiency, maintainability, and scalability. Here's a deeper dive into these decisions:

### Framework Selection: Spring Boot

The project is built using Spring Boot, and this choice was made for several compelling reasons. Spring Boot is known for its robustness and versatility, making it an excellent choice for building a wide range of applications. Its extensive community support means that I have access to a wealth of knowledge and resources to help me develop and maintain this API effectively. Additionally, my familiarity with Java and the Spring framework played a significant role in this selection, allowing me to expedite the development process and deliver a high-quality API.

### Scalability Considerations

One of the primary objectives in the design of this API is scalability. I've built the architecture with future growth in mind. This means that as the project evolves, it will be straightforward to add additional features, integrate databases, and incorporate new components. The API's modular design and use of best practices in software architecture ensure that it can be extended and adapted as the project's requirements change.

### Containerization with Docker

To enhance deployment and maintain consistency across different environments, I've chosen to use Docker for containerization. Docker allows me to package the application and its dependencies into a standardized container, making it easy to deploy the API in various environments, from local development to production servers. This approach simplifies the deployment process and ensures that the API behaves consistently, regardless of the deployment environment.

By making these technical decisions, I aim to create an API that is not only functional but also flexible, easy to maintain, and scalable to meet the project's evolving needs.


## Project Comments and Notes

### Error Handling

- Error handling is implemented throughout the codebase. All potential error scenarios are covered with try-catch blocks, providing meaningful error messages and handling specific cases.

### Testing

- A suite of unit tests is included in the project to ensure the correctness of the API's functionality. These tests are located in the 'tests' directory.
