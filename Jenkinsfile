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
    phase = "Beginning"
  }
  stages('Automatic Canary Deployment')
  {
    stage('Deployment Phase Check'){
       agent {
        kubernetes {
          label 'nodejs'
          yaml testPodYaml
        }
      }
      steps {
        checkout scm
        script{
          changedFile = sh (script:"git diff-tree --no-commit-id --name-only -r ${env.GIT_COMMIT}", returnStdout: true)
          echo(changedFile)
          experimentFile = changedFile.contains("experiments/")
          if(experimentFile){
            experimentText = sh (script:"cat ${changedFile}", returnStdout: true)
           sh "cat ${changedFile}"
            experimentYaml = readYaml (text: "$experimentText")
            canaryBool = experimentYaml.labels.contains("Canary-deploy")
            if(canaryBool){
             echo "Canary deploy!" 
            }
            else{
             echo "No canary!" 
            }
          }
          else{
           echo "Updated a not experiment!" 
          }
        }
      }
    }
    stage('Automated Testing Kickoff'){
      steps {
        echo("Hello 2")
      }
    }
    stage('Canary scale up'){
      steps {
        echo("Hello 3")
      }
    }
    stage('Canary scale down'){
      steps {
        echo("Hello 4")
      }
    }
  }
}
