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