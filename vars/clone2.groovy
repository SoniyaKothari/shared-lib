def call(String repoUrl, String branchName) {
    git(
        url: repoUrl,
        branch: branchName,
        credentialsId: 'github-creds'
    )
}
