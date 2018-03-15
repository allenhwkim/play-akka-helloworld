Referennce:
https://www.playframework.com/documentation/2.6.x/JavaAkka

## Prerequisites
* Complete [Play HelloWorld Tutorial](https://github.com/allenhwkim/play-helloworld)

## To start
```
$ git clone https://github.com/allenhwkim/play-akka-helloworld.git
$ cd play-akka-helloworld
$ sbt
[helloworld] $ run
```

conf/routes
```
GET     /            controllers.HomeController.index()
GET     /hello       controllers.HomeController.sayHello()
```

app/actors/HelloActor.java
```
package actors;

import akka.actor.*;

public class HelloActor extends UntypedActor {

    static public Props props(String message) {
      return Props.create(HelloActor.class);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("Received: " + message);
        getSender().tell("Hello, " + message, getSelf());
    }
}
```

app/controlers/HomeController.java
```
package controllers;

import akka.actor.*;
import play.mvc.*;
import scala.compat.java8.FutureConverters;
import javax.inject.*;
import java.util.concurrent.CompletionStage;
import actors.*;
import static akka.pattern.Patterns.ask;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    final ActorSystem actorSystem = ActorSystem.create("playakka");
    final ActorRef helloActor = actorSystem.actorOf(HelloActor.props("Akka"));

    public Result index() {
        return ok("Hello World");
    }

    public CompletionStage<Result> sayHello() {
        return FutureConverters.toJava(
            ask(helloActor, "World from HelloActor", 1000))
                .thenApply(response -> ok(response.toString())
        );
    }

}
```



