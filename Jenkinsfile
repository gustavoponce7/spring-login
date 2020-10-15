#!groovy

pipeline {
    node ("windows") {
    stages {
        stage('build') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}
}