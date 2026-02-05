def call(Map config) {
    def imageName = config.imageName ?: 'jenkins-demo'
    def buildNumber = config.buildNumber ?: env.BUILD_NUMBER
    
    echo "🔵🟢 Starting Blue-Green Deployment..."
    
    sh """
        chmod +x blue-green-deploy.sh
        ./blue-green-deploy.sh ${buildNumber}
    """
    
    echo "✅ Blue-Green deployment completed!"
}
