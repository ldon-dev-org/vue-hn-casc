@Library('cb-days@master') _
def testPodYaml = libraryResource 'podtemplates/vuejs/vuejs-test-pod.yml'
pipeline {
  agent none
  options { 
    buildDiscarder(logRotator(numToKeepStr: '10'))
    //skipDefaultCheckout true
    preserveStashes(buildCount: 10)
  }
  environment {
    //Possible canary deploy phases:
    //1. Flag completely off
    //2. Flag turned on for internal testing
    //3. Flag turned on for internal testing + % subset of end users
    canaryPhases = ["killSwitch", "fullyFalse", "internalTesting", "percentageDeploy"]
    currentPhase = "fullyFalse"
    testsPass = false
  }
  stages('FF Scale Down')
  {
    stage('Pre-scale'){
       agent {
        kubernetes {
          label 'nodejs'
          yaml testPodYaml
        }
      }
      steps {
        checkout scm
        script{

        }
      }
    }
    stage('Scale Down'){
      steps {
        testsPass = automatedTestCall()
      }
    }
  }
}
