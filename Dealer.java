package STOCK;



import java.util.Date;
//
import PARTS.Car;
import Window.MainWindow;

public class Dealer {
    private Thread th;
    private int delay;
    public Dealer(int t, int dealerId, StockCar sC) {
        delay = t;
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        Car c = sC.getFromStock();
                        if (MainWindow.doLog) {
                            System.out.printf("%s: Dealer #%d: Car #%d (Accessory #%d, Body #%d, Engine #%d)\n",
                                    new Date().toString(), dealerId, c.getPartId(), c.getAccessory().getPartId(),
                                    c.getBody().getPartId(), c.getEngine().getPartId());
                        }

                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        break;
                    }
                }

            }});
        th.start();
    }

    public void terminate() {
        th.interrupt();
    }

    public void setDelay(int t) {
        delay = t;
    }
}
