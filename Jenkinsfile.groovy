pipeline{
 agent none
    stages{
	  stage('Checkout') {
        checkout([$class: 'GitSCM',
                  branches: [[name: 'master']],
                  userRemoteConfigs: [[url: 'https://github.com/Madhav0987/boxfuse-sample-java-war-hello.git']]
                 ])
    }

    stage('Test') {
        try {
            sh 'mvn clean test'
            echo 'Running unit test'
        } catch (Exception e) {
            error 'Tests failed!'
        }
    }

    stage('Build') {
        try {
            sh 'mvn clean package'
            echo 'Build created successful'
        } catch (Exception e) {
            error 'Build failed!'
        }
    }

    stage('Deploy') {
        try {
            sh 'sudo cp /home/ec2-user/jenkins/workspace/Assignment_03/target/hello-1.0.war /opt/apache-tomcat-9.0.95/webapps'
            echo 'Successfully deployed'
        } catch (Exception e) {
            error 'Deployment failed!'
        }
    }
}
	}
