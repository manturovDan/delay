import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.jiraCloudIntegration
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.bitbucketCloudConnection
import jetbrains.buildServer.configs.kotlin.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.triggers.vcs
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    buildType(Sandbox1_Delay_Build)

    features {
        feature {
            id = "PROJECT_EXT_10"
            type = "IssueTracker"
            param("name", "manturovDan/gl-pub")
            param("authType", "anonymous")
            param("repository", "https://gitlab.com/manturovDan/gl-pub")
            param("type", "GitLabIssues")
            param("secure:accessToken", "credentialsJSON:211e5695-82b5-4c23-a59f-15fde476d00d")
        }
        feature {
            id = "PROJECT_EXT_5"
            type = "IssueTracker"
            param("name", "gl-anon")
            param("authType", "anonymous")
            param("repository", "")
            param("type", "GitLabIssues")
            param("secure:accessToken", "")
        }
        githubConnection {
            id = "PROJECT_EXT_6"
            displayName = "GitHub.com"
            clientId = "2915ac6df533bc631f94"
            clientSecret = "credentialsJSON:6132fda8-43d2-4171-a468-ce1c6f68a88f"
        }
        bitbucketCloudConnection {
            id = "PROJECT_EXT_9"
            displayName = "Bitbucket Cloud"
            key = "ZhWH5uesz6W7sMhUTB"
            clientSecret = "credentialsJSON:ce1c2982-e852-406c-9bc9-6e02a4dadd4e"
        }
    }

    subProject(Sandbox1_Delay_Delay)
}

object Sandbox1_Delay_Build : BuildType({
    id("Build")
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "delay"
            scriptContent = """
                pwd
                ls
                java LongBuild/src/LongBuild.java 1
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        jiraCloudIntegration {
            issueTrackerConnectionId = "PROJECT_EXT_12"
        }
    }
})


object Sandbox1_Delay_Delay : Project({
    id("Delay")
    name = "Delay"

    vcsRoot(Sandbox1_Delay_Delay_HttpsGithubComManturovDanDelayRefsHeadsMain)

    buildType(Sandbox1_Delay_Delay_Build)
})

object Sandbox1_Delay_Delay_Build : BuildType({
    id("Delay_Build")
    name = "Build"

    vcs {
        root(Sandbox1_Delay_Delay_HttpsGithubComManturovDanDelayRefsHeadsMain)
    }

    steps {
        script {
            scriptContent = """
                pwd
                ls
                java LongBuild/src/LongBuild.java 2
            """.trimIndent()
        }
    }

    triggers {
        vcs {
        }
    }
})

object Sandbox1_Delay_Delay_HttpsGithubComManturovDanDelayRefsHeadsMain : GitVcsRoot({
    id("Delay_HttpsGithubComManturovDanDelayRefsHeadsMain")
    name = "https://github.com/manturovDan/delay#refs/heads/main"
    url = "https://github.com/manturovDan/delay"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "manturovDan"
        password = "credentialsJSON:83896ea8-0aba-4dcb-8e31-6ef6b2224b0e"
    }
})
