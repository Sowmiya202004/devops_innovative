pipeline {
    agent any

    tools {
        jdk 'JDK'
        maven 'MAVEN'
    }

    stages {

        stage('Compile') {
            steps {
                bat 'javac CourseManagementServer.java'
            }
        }

        stage('Run Server') {
            steps {
                bat 'java CourseManagementServer'
            }
        }

    }
}
