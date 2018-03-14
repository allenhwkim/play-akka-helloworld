
## Install Java/IDE/sbt
```
$ brew cask install java8
$ java -version
$ brew cask install intellij-idea
$ vi ~/.bash_profile
$ # then add    alias idea='open -a "`ls -dt /Applications/IntelliJ\ IDEA*|head -1`"'
$ brew install sbt
$ sbt about
$ sbt tasks
```

## Create directories and files
$ mkdir helloworld && cd helloworld
$ mkdir -p app/controllers app/views conf project
build.sbt

```
name := "helloworld"
version := "1.0-SNAPSHOT"
lazy val root = (project in file(".")).enablePlugins(PlayJava)
scalaVersion := "2.12.4"
libraryDependencies += guice
```
conf/application.conf

```
# This is the main configuration file for the application.
# https://www.playframework.com/documentation/latest/ConfigFile
```

project/build.properties
```project/build.properties```

project/plugins.sbt
```ddSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.12")```

## First Run without route and contorller
$ cd helloworld
$ sbt
[helloworld] $ run
$ open -a "Google Chrome" http://localhost:9000


## First Run with contorller and route
conf/routes
```
GET     /       controllers.HomeController.index()
```

app/controllers/HomeController.java
```
package controllers;
import play.mvc.*;
 
public class HomeController extends Controller {
    public Result index() {
        return ok("Hello World");
    }
}
```

Run
```
$ sbt
[helloworld] $ run
$ open -a "Google Chrome" http://localhost:9000
```
