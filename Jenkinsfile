pipeline {
    agent any
    stages {
        stage('Clone'){
            step{
                git branch:'main',
                    'https://github.com/maodo-malick/orderring.git'
            }
        }
        stage('Build, Test, and Package') {
            steps {
                // The 'clean package' goal runs the entire lifecycle up to 'package',
                // which includes 'compile' and 'test' (running unit tests)
                
                bat 'mvn clean package'
            }
        }
  }
}
