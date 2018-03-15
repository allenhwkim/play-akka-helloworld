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

