#!groovy

pipeline {
    agent any
    stages {  
		stage('Checkout') {
            steps {
                echo 'checkout..'
				checkout scm
            }
        }
		stage('Build') {
            steps {
                echo 'Building..'
				mvn clean install
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
				mvn test
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}