package dhwb.vs.clock;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Dodo on 23.03.2017.
 */
public class Server {
    //public List<LampartClock> clients = new ArrayList<>();
    public List<ChristianClock> clients = new ArrayList<>();

    public void send(LampartClock client, String message, int timestamp) {
        for (LampartClock other : this.clients) {
            if (other != client) {
                other.receive(message, timestamp);
            }
        }
    }

    public LocalTime getTimestamp() throws InterruptedException {
        LocalTime servertime = ZonedDateTime.now(ZoneOffset.UTC).toLocalTime();
        System.out.println("Servertime: " + servertime);
        Thread.sleep(new Random().nextInt(420));
        return servertime;
    }

    public void computeTime() throws InterruptedException {
        LocalTime now = ZonedDateTime.now(ZoneOffset.UTC).toLocalTime();
        long timeSum = now.getNano();
        for (ChristianClock client : this.clients) {
            LocalTime time = client.getTime();
            timeSum += time.getNano();
        }
        timeSum = timeSum * 1000 * 1000;
        long median = timeSum / (this.clients.size() + 1);
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();

        for (int i = 0; i < 5; i++) {
            server.clients.add(new ChristianClock());
        }

        while(true) {
            server.computeTime();
        }
    }
}
