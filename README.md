# Servlet based Hello World app for App Engine Flexible environment

## Requirements
* [Apache Maven](http://maven.apache.org) 3.3.9 or greater
* [Google Cloud SDK](https://cloud.google.com/sdk/)
* `gcloud components install app-engine-java`
* `gcloud components update`

## Cambiar de instancia de app-engine
* Con lo siguiente cambiamos a otra instancia de app-engine
* `gcloud init`
*
* Si la instancia app-engine es nueva, porque la acabamos de crear con la consola de app-engine, tenemos que añadir una aplicación.
* Con el siguiente comando creamos una nueva aplicación en una instancia nueva de app-engine
* `gcloud app create`
*

## Setup

Use either:

* `gcloud init`
* `gcloud auth application-default login`

We support building with [Maven](http://maven.apache.org/), [Gradle](https://gradle.org), [IntelliJ IDEA](https://cloud.google.com/tools/intellij/docs/), and [Eclipse](https://cloud.google.com/eclipse/docs/).  
The samples have files to support both Maven and Gradle.  To use the IDE plugins, see the documentation pages above.

## Maven
[Using Maven and the App Engine Plugin](https://cloud.google.com/appengine/docs/flexible/java/using-maven)
& [Maven Plugin Goals and Parameters](https://cloud.google.com/appengine/docs/flexible/java/maven-reference)

### Running locally

    $ mvn jetty:run-exploded
  
### Deploying

    $ mvn appengine:deploy

## Gradle
[Using Gradle and the App Engine Plugin](https://cloud.google.com/appengine/docs/flexible/java/using-gradle) 
& [Gradle Tasks and Parameters](https://cloud.google.com/appengine/docs/flexible/java/gradle-reference)

### Running locally

    $ gradle jettyRun

### Deploying

    $ gradle appengineDeploy


Microsoft Windows [Versión 10.0.17134.228]
(c) 2018 Microsoft Corporation. Todos los derechos reservados.

C:\Users\nachocv>cd C:\Users\nachocv\eclipse-workspace\adrutas1

C:\Users\nachocv\eclipse-workspace\adrutas1>gcloud init
Welcome! This command will take you through the configuration of gcloud.

Settings from your current configuration [adrutas] are:
compute:
  region: europe-west1
  zone: europe-west1-d
core:
  account: adrutas2@gmail.com
  disable_usage_reporting: 'False'
  project: adrutasnew

Pick configuration to use:
 [1] Re-initialize this configuration [adrutas] with new settings
 [2] Create a new configuration
 [3] Switch to and re-initialize existing configuration: [default]
Please enter your numeric choice:  2

Enter configuration name. Names start with a lower case letter and
contain only lower case letters a-z, digits 0-9, and hyphens '-':  adrutas2
Your current configuration has been set to: [adrutas2]

You can skip diagnostics next time by using the following flag:
  gcloud init --skip-diagnostics

Network diagnostic detects and fixes local network connection issues.
Checking network connection...done.
Reachability Check passed.
Network diagnostic (1/1 checks) passed.

Choose the account you would like to use to perform operations for
this configuration:
 [1] adrutas2@gmail.com
 [2] Log in with a new account
Please enter your numeric choice:  2

Your browser has been opened to visit:

    https://accounts.google.com/o/oauth2/auth?redirect_uri=http%3A%2F%2Flocalhost%3A8085%2F&prompt=select_account&response_type=code&client_id=32555940559.apps.googleusercontent.com&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcloud-platform+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fappengine.admin+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcompute+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Faccounts.reauth&access_type=offline




Updates are available for some Cloud SDK components.  To install them,
please run:
  $ gcloud components update

You are logged in as: [nachocv@gmail.com].

Pick cloud project to use:
 [1] adrutas1
 [2] adrutas2-218517
 [3] api-project-309023812142
 [4] bbdd-1377
 [5] nachocv
 [6] nachocv1
 [7] Create a new project
Please enter numeric choice or text value (must exactly match list
item):  2

Your current project has been set to: [adrutas2-218517].

Not setting default zone/region (this feature makes it easier to use
[gcloud compute] by setting an appropriate default value for the
--zone and --region flag).
See https://cloud.google.com/compute/docs/gcloud-compute section on how to set
default compute region and zone manually. If you would like [gcloud init] to be
able to do this for you the next time you run it, make sure the
Compute Engine API is enabled for your project on the
https://console.developers.google.com/apis page.

Your Google Cloud SDK is configured and ready to use!

* Commands that require authentication will use nachocv@gmail.com by default
* Commands will reference project `adrutas2-218517` by default
Run `gcloud help config` to learn how to change individual settings

This gcloud configuration is called [adrutas2]. You can create additional configurations if you work with multiple accounts and/or projects.
Run `gcloud topic configurations` to learn more.

Some things to try next:

* Run `gcloud --help` to see the Cloud Platform services you can interact with. And run `gcloud help COMMAND` to get help on any gcloud command.
* Run `gcloud topic -h` to learn about advanced features of the SDK like arg files and output formatting

##Copia de BBDD sin password
C:\Program Files\MySQL\MySQL Server 5.5\bin>mysqldump.exe --host=173.194.109.193 --order-by-primary=TRUE --protocol=tcp --user=root --compress=TRUE --port=3306 --default-character-set=utf8 --skip-triggers "rutas" > C:\Users\nachocv\Documents\dumps\Dump20181018.sql

##Copia de BBDD con password
C:\Program Files\MySQL\MySQL Server 5.5\bin>mysqldump.exe --host=35.187.86.126 --order-by-primary=TRUE --protocol=tcp --user=root --password=1234 --compress=TRUE --port=3306 --default-character-set=utf8 --skip-triggers "rutas" > C:\Users\nachocv\Documents\dumps\Dump20190610.sql

##Subir cambios a GitHub
Se comprueba el estado con "git status"
Se añade lo que falte con "git add ."
Se guardan cambios en local con "git commit -m etiqeta"
Se suben cambios a GitHub con "git push origin master"

