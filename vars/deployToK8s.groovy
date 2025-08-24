def call() 
{
    withEnv(["KUBECONFIG=/home/soniya/.kube/config"]) 
    {
        sh """
          kubectl apply -f k8s/Deployment.yml
          kubectl apply -f k8s/Service.yml
          kubectl rollout status deployment/weather -n devops
          kubectl port-forward service/weather-svc 8000:8000 -n devops &
        """
    }
}
