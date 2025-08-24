def call() 
{
    withEnv(["KUBECONFIG=/var/lib/jenkins/.kube/config"]) 
    {
        sh """
          kubectl apply -f k8s/Deployment.yml
          kubectl apply -f k8s/Service.yml
          kubectl rollout status deployment/weather -n devops
          nohup /var/lib/jenkins/start-port-forward.sh > /var/lib/jenkins/port-forward.log 2>&1 &
        """
    }
}
