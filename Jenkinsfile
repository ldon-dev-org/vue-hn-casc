@Library('cb-days@master') _
pipeline {
  agent none
  options { 
    buildDiscarder(logRotator(numToKeepStr: '10'))
    //skipDefaultCheckout true
    preserveStashes(buildCount: 10)
  }
  environment {
  }
  stages('Automatic Canary Deployment')
  {
    stage('Deployment Phase Check'){
      steps {
        echo("Hello!")
      }
    }
    stage('Automated Testing Kickoff'){
      steps {
        echo("Hello!")
      }
    }
    stage('Canary scale up'){
      steps {
        echo("Hello!")
      }
    }
    stage('Canary scale down'){
      steps {
        echo("Hello!")
      }
    }
  }
}
