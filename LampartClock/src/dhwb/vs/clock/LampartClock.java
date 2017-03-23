package dhwb.vs.clock;

import java.util.Random;

public class LampartClock implements Runnable{
    private int clock;
    private Server server;

    public void send(String message) {
        this.clock = clock + 1;
        int timestamp = clock;
        server.send(this, "", timestamp);
    }

    public void receive(String message, int timestamp) {
        this.clock = Math.max(timestamp, clock) + 1;
        System.out.println("Received \"" + message + "\"");
    }

    @Override
    public void run() {
        while(true) {
            Random random = new Random(System.currentTimeMillis() + (long)(Math.random() * 100));
            send("Hi from " + Thread.currentThread().getName());

            try {
                Thread.sleep(random.nextInt(3600));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}