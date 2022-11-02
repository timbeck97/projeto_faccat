pipeline {
    agent any
    tools{
        maven "maven"
    }
    stages {
        
        stage('maven build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }
        stage('maven run') {
            steps {
                sh 'nohup java -jar /var/lib/jenkins/workspace/FACCAT/target/demo-0.0.1-SNAPSHOT.jar &'
               
            }
        }
       
    }
}
