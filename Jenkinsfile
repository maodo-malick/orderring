pipeline {
    agent any
    tools {
        maven 'Maven-3.9.1'  // Must match the name you configured
    }
    stages {
        stage('Clone'){
            steps{
                git branch:'main',
                   url: 'https://github.com/maodo-malick/orderring.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps{
                sh 'mvn test'
            }
        }
        stage('Package'){
            steps{
                sh 'mvn package'
            }
        }
  }
}
