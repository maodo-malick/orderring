pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
           maven "M3"
    }

    stages {
        stage('compile') {
            steps {
                // To run Maven on a Windows agent, use
                 bat  'mvn clean compile'
            }
        }
           stage('Test'){
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
