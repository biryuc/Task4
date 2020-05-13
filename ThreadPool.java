package Thread;



import java.util.ArrayDeque;
import java.util.Queue;

import STOCK.Stock;
import STOCK.StockCar;
import PARTS.Accessory;
import PARTS.Body;
import PARTS.Engine;
import PARTS.Request;
import Window.MainWindow;

public class ThreadPool {
    Thread threads[];
    Queue<Request> q;
    Stock<Accessory> sA;
    Stock<Body> sB;
    Stock<Engine> sE;
    StockCar sC;
    int carId;

    public ThreadPool(int sz) {

        q = new ArrayDeque<>();
        threads = new Thread[sz];
        for (int i=0;i<sz;i++) {
            threads[i] = new Thread(new WorkerRunnable(this));
            threads[i].start();
        }
    }

    public synchronized void addRequest(Request rq) {
        q.add(rq);
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException {
        while (q.isEmpty()) {
            wait();
        }
        Request obj = q.poll();
        return obj;
    }

    public synchronized int getNewId() {
        return carId++;
    }

    public void terminate() {
        for (int i=0;i<threads.length;i++) {
            threads[i].interrupt();
        }
    }
}



