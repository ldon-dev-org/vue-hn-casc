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
