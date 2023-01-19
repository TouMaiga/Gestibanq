pipeline {

agent any

     tools {
          maven 'maven'
     }

     stages{

          stage('Creation du jar ') {

               steps {
                         sh 'mvn clean install -DskipTests'
               }
			   
          }

          stage('build docker image sur serveur') {

               steps {
                         sh 'docker build -t gestibank_app .'
                    }
					

          }
          stage('run docker-compose : par groupe 3') {
		   steps {
				sh 'docker-compose up -d'
			}

		}

		  
     }
}