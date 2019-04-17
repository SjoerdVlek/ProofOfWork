import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        Data data = new Data(155, "Chloe Price", "Dit is de echte gamestate.");
        Data data2 = new Data(156, "Max Caulfield", "Dit is een andere gamestate.");

        Thread thread1 = new Thread(data, "thread1");
        Thread thread2 = new Thread(data2, "thread2");

        thread1.start();
        thread2.start();

        long startMillis = System.currentTimeMillis();

        while(thread1.isAlive() && thread2.isAlive()) {
            System.out.println("Data 1 : " + data.hash());
            System.out.println("Data 2 : " + data2.hash());

        }

        long timeToHash = System.currentTimeMillis() - startMillis;

        System.out.println();

        System.out.println("data1 : " + data.getData());
        System.out.println("data2 : " + data2.getData());

        System.out.println();

        if(thread1.isAlive()) {
            thread1.stop();

            System.out.println("Thread 2 was eerder klaar.");

            data.setData(data2.getData());

            System.out.println(data2.hash());
            System.out.println("Check : " + data.hash());
            System.out.println(timeToHash/1000 + " seconden en " + timeToHash % 1000 + " milliseconden." + System.lineSeparator());
            System.out.println(data2.toString());

        } else if(thread2.isAlive()) {
            thread2.stop();
            System.out.println("Thread 1 was eerder klaar.");

            data2.setData(data.getData());

            System.out.println(data.hash());
            System.out.println("Check : " + data2.hash());
            System.out.println(timeToHash/1000 + " seconden en " + timeToHash % 1000 + " milliseconden." + System.lineSeparator());
            System.out.println(data.toString());
        }

        System.out.println();
        System.out.println("Data : "
                + System.lineSeparator() + "Data 1 : " + data.getData()
                + System.lineSeparator() + "Data 2 : " + data2.getData());

    }
}

