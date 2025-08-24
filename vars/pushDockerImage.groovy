def call(String ProjectName, String ImageTag, String DockerHubUser) 
{
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'dockerHubPass', usernameVariable: 'dockerHubUser')]) 
    {
        sh "docker login -u ${dockerHubUser} -p ${dockerHubPass}"
    }

    // Check if image exists on Docker Hub
    def imageExists = sh(
        script: "docker manifest inspect ${DockerHubUser}/${ProjectName}:${ImageTag} > /dev/null 2>&1 || echo 'notfound'",
        returnStdout: true
    ).trim()

    if (imageExists == 'notfound') 
    {
        echo "🔹 Image not found on Docker Hub. Pushing new image..."
        sh "docker push ${DockerHubUser}/${ProjectName}:${ImageTag}"
    } 
  else 
    {
        echo "Image ${DockerHubUser}/${ProjectName}:${ImageTag} already exists. Skipping push."
    }
}
