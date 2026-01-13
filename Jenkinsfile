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
