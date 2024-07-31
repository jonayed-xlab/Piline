# Piline

## Project Description
Piline is a Spring Boot application created as part of a DevOps learning project. 
The primary goal of this project is to explore and practice DevOps tools and practices, including Docker, Jenkins, and Kubernetes. It serves as a hands-on example to understand CI/CD and containerization.

## Installation

### Jenkins

To set up Jenkins, follow these steps:

1. **Download Jenkins:**
    - Visit the [Jenkins Download Page](https://www.jenkins.io/download/#downloading-jenkins) and download the Windows installer.

2. **Install Jenkins:**
    - Open the installer and follow the setup wizard:
        - Select the destination folder.
        - Choose service logon credentials (select "Run service as local or domain user").
        - Configure the port selection.
        - Set the Java Home Directory (JDK 11 or JDK 17 only).
        - Proceed with custom setup and click "Next".
        - Complete the installation by clicking "Install".

3. **Run Jenkins:**
    - To start Jenkins, run the following command:

      ```sh
      java -jar jenkins.war --httpPort=8080
      ```
4. **Setup Pipeline Steps:**
   - Select "New Item" (Pipeline)
   - Select "GitHub project" (Provide GitHub URL)
   - Define "Pipeline Script"

#### Important Note: 
1. Use `bat` for Windows commands and `sh` for Linux commands in your Jenkins pipeline script.
2. `withCredentials` is used to securely handle passwords by storing them in a variable. 

### Here is the script I used: 
``` 
   pipeline {
       agent any
       tools{
           maven 'maven_3_9_8'
       }
       stages {
           stage('Git Checkout') {
               steps {
                   checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jonayed-xlab/Piline']])
                
               }
           }
           stage('Install Dependencies') {
               steps {
                   bat 'mvn clean install' 
               }
           }
           stage('Application Build') {
               steps {
                   bat 'mvn package'
               }
           }
           stage('Build Docker Image') {
               steps {
                   script {
                       bat 'docker build -t jonayed23/piline-server .'
                   }
               }
           }
           stage('Upload Image to Docker Hub') {
               steps {
                   script {
                       withCredentials([string(credentialsId: 'Password', variable: 'PASSWORD')]) {
                           bat 'docker login -u jonayed23 -p %PASSWORD%'
                       }
                    
                       bat 'docker push jonayed23/piline-server'

                   }
               }
           }
       }
   }
   ```
   
### Project Setup

```sh
# Clone the Repository:
git clone git@github.com:jonayed-xlab/Piline.git

# Navigate to the Project Directory:
cd Piline

# Install Dependencies:
./mvnw clean install

# Run the Application:
./mvnw spring-boot:run
```

### Pull Docker Image Command
`docker pull jonayed23/piline-server:latest`

