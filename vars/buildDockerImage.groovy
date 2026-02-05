def call(Map config) {
    def imageName = config.imageName ?: 'jenkins-demo'
    def buildNumber = config.buildNumber ?: env.BUILD_NUMBER
    
    echo "Building Docker image: ${imageName}:${buildNumber}"
    
    script {
        docker.build("${imageName}:${buildNumber}")
    }
    
    echo "✅ Docker image built successfully"
}
