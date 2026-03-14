pipeline {
    agent any

    tools {
        jdk 'JDK'
        maven 'MAVEN'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Server') {
            steps {
                bat 'java -cp target/classes CourseManagementServer'
            }
        }

    }
}
