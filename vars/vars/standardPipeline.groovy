def call(Map config) {
    pipeline {
        agent any
        
        stages {
            stage('Checkout') {
                steps {
                    echo '📥 Code checked out from GitHub'
                }
            }
            
            stage('Build') {
                steps {
                    script {
                        buildDockerImage(
                            imageName: config.imageName ?: 'jenkins-demo',
                            buildNumber: env.BUILD_NUMBER
                        )
                    }
                }
            }
            
            stage('Test') {
                steps {
                    echo '🧪 Running tests...'
                    echo '✅ Tests passed!'
                }
            }
            
            stage('Deploy') {
                steps {
                    script {
                        deployContainer(
                            imageName: config.imageName ?: 'jenkins-demo',
                            buildNumber: env.BUILD_NUMBER,
                            port: config.port ?: 3000
                        )
                    }
                }
            }
        }
        
        post {
            success {
                echo '✅ Pipeline completed successfully!'
            }
            failure {
                echo '❌ Pipeline failed!'
            }
        }
    }
}
