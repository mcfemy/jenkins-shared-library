def call(Map config) {
    def imageName = config.imageName ?: 'jenkins-demo'
    def buildNumber = config.buildNumber ?: env.BUILD_NUMBER
    def port = config.port ?: 3000
    
    echo "Deploying ${imageName}:${buildNumber} on port ${port}"
    
    // Cleanup old containers
    sh """
        docker ps -aq --filter "name=${imageName}" | xargs -r docker rm -f || true
    """
    
    // Run new container
    sh """
        docker run -d -p ${port}:${port} \
        --name ${imageName}-${buildNumber} \
        ${imageName}:${buildNumber}
    """
    
    echo "✅ Application deployed at http://localhost:${port}"
}
