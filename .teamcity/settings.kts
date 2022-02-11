import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.youtrack
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

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

    buildType(Build)

    features {
        githubIssues {
            id = "PROJECT_EXT_3"
            displayName = "manturovDan/delay"
            repositoryURL = "https://github.com/manturovDan/delay"
            authType = accessToken {
                accessToken = "credentialsJSON:cc33674f-2a62-4c83-b25c-b0ea2a8f857b"
            }
        }
        youtrack {
            id = "PROJECT_EXT_4"
            displayName = "yt_integration"
            host = "https://danila-tc.youtrack.cloud/"
            userName = ""
            password = ""
            projectExtIds = ""
            accessToken = "credentialsJSON:4ceadd04-0cd1-46ab-a79c-339ade130448"
            useAutomaticIds = true
        }
    }
}

object Build : BuildType({
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
})
