pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    docker.build("YOUR_ECR_REPOSITORY_URL/deploying-app-to-eks:${env.BUILD_ID}").push()
                }
            }
        }
        // Add additional stages as needed
    }
}
