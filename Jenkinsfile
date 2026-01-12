pipeline {
    agent any
    stages {
        stage('Clone'){
            steps{
                git branch:'main',
                    'https://github.com/maodo-malick/orderring.git'
            }
        }
        stage('Build, Test, and Package') {
            steps {
                bat 'mvn clean package'
            }
        }
  }
}
