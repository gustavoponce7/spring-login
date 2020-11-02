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
				bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
				bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}