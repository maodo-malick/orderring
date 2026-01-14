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
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar \
                        -Dsonar.projectKey=orderring \
                        -Dsonar.projectName=Orderring \
                        -Dsonar.java.source=17
                    '''
                }
            }
        }
         stage('Quality Gate') {
                     steps {
                         timeout(time: 5, unit: 'MINUTES') {
                             waitForQualityGate abortPipeline: true
                         }
                     }
         }

        stage('Package'){
            steps{
                sh 'mvn package'
            }
        }
  }
}
