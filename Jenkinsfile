pipeline {
  agent { label 'linux' }
  environment {
    TEST_URL="http://192.168.1.25:4723/"
  }
  stages {
    stage('Run the tests') {
      steps {
        sh './mvnw clean test'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/**/*.xml'
    }
  }
}
