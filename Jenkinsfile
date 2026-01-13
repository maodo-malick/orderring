pipeline {
    agent any
    stages {
        stage('Clone'){
            steps{
                git branch:'main',
                   url: 'https://github.com/maodo-malick/orderring.git'
            }
        }
        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }
        stage('Test') {
            steps{
                bat 'mvn test'
            }
        }
        stage('Package'){
            steps{
                bat 'mvn package'
            }
        }
  }
}
