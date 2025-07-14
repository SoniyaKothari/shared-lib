def call(String ProjectName, String ImageTag, String DockerHubUser)
{
  withCredentials([usernamePassword(credentialsId:'dockerhub', passwordVariable:'dockerHubPass', usernameVariable:'dockerHubUser')])
  {
    sh "docker login -u ${dockerHubUser} -p ${dockerHubPass}"
  }
  sh "docker push ${DockerHubUser}/${ProjectName}:${ImageTag}"
}
