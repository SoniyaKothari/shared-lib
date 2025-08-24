def call() 
{
    withEnv(["KUBECONFIG=/var/lib/jenkins/.kube/config"]) 
    {
        sh """
          kubectl apply -f k8s/namespace.yml  
          kubectl apply -f k8s/Deployment.yml
          kubectl apply -f k8s/Service.yml
          kubectl rollout status deployment/weather -n devops
        """
    }
}
