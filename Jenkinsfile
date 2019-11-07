pipeline{
  agent { 
    node {
      label 'maven'
    } 
  }
  stages{
    stage ('Checkout codigo fuente'){
      steps{
        checkout scm
      }
    }

    stage ('Instalar dependencias'){
      steps{
        sh '''
          mvn install
        '''
      }
    }

    stage ('Construcción Aplicación') {
      steps{
        sh 'mvn package'
      }
    }
    stage ('Registrar Docker') {
      steps{
        script {
          openshift.withCluster() {
            openshift.withProject('poc') {
              openshift.selector("bc", "java-springboot-example").startBuild("--from-dir=./target", "--wait=true", "--follow", "--loglevel=8")
            }
          }
        }
      }
    }

    stage('Desplegar') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('poc') {
              openshift.selector("dc", "java-springboot-example").rollout().latest();
            }
          }
        }
      }
    } 

  }
}