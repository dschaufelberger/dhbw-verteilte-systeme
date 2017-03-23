package dhwb.vs.clock;

import java.time.*;
import java.util.Random;

/**
 * Created by Dodo on 23.03.2017.
 */
public class ChristianClock {
    private LocalTime time;

    public void askTime(Server server) throws InterruptedException {
        Instant send = Instant.now();
        LocalTime timestamp = server.getTimestamp();
        Instant receive = Instant.now();

        Duration rtt = Duration.between(send, receive);
        this.time = LocalTime.from(timestamp.plus(rtt.dividedBy(2)));

        System.out.println("Clienttime: " + time);
    }

    public LocalTime getTime() throws InterruptedException {
        LocalTime servertime = ZonedDateTime.now(ZoneOffset.UTC).toLocalTime();
        System.out.println("Servertime: " + servertime);
        Thread.sleep(new Random().nextInt(420));
        return servertime;
    }

    public void setTime(LocalTime time) {

    }
}
