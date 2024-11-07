pipeline {
    agent any
    environment {
        // Define your ECR repository URL and AWS region
        ECR_REPOSITORY = '345594595830.dkr.ecr.us-east-1.amazonaws.com/java-microservice' // e.g., <account-id>.dkr.ecr.<region>.amazonaws.com/your-repo-name
        AWS_REGION = 'us-east-1' // e.g., us-east-1
        AWS_CREDENTIALS_ID = 'aws-creds' // Jenkins credentials ID for AWS
        SONARQUBE_URL = 'http://3.95.7.57:9000/' // SonarQube server URL
        SONARQUBE_CREDENTIALS_ID = 'sonar-token' // Jenkins credentials ID for SonarQube
    }

    tools {
        jdk 'JDK11' // Replace with the name you configured in Global Tool Configuration
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', 
            branches: [[name: '*/main']], 
            userRemoteConfigs: [[url: 'https://github.com/IAM-VarunKR/Deploying-app-to-EKS.git', credentialsId: 'ab440c02-9630-4615-b6b5-b5feced4ebce']]
        ])
            }
        }
        stage('Build') {
            steps {
                script {
                    // Using the configured Maven tool
                    dir('DeployingAppToEKS'){
                        withMaven(maven: 'Maven 3.9') {
                        sh 'mvn clean install -DskipTests'
                        }
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run tests using Maven
                    sh 'mvn test'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis
                    withCredentials([usernamePassword(credentialsId: SONARQUBE_CREDENTIALS_ID, usernameVariable: 'SONAR_USER', passwordVariable: 'SONAR_PASSWORD')]) {
                        sh "mvn sonar:sonar -Dsonar.projectKey=com.example:DeployingAppToEKS -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=\$SONAR_USER -Dsonar.password=\$SONAR_PASSWORD"
                    }
                }
            }
        }
        stage('Login to ECR') {
            steps {
                script {
                    // Login to AWS ECR
                    def ecrLogin = sh(script: "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY}", returnStdout: true)
                    echo "${ecrLogin}"
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh "docker build -t ${ECR_REPOSITORY}:latest ."
                }
            }
        }
        stage('Push to ECR') {
            steps {
                script {
                    // Push the Docker image to ECR
                    sh "docker push ${ECR_REPOSITORY}:latest"
                }
            }
        }
    }
}
