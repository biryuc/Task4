package STOCK;



import PARTS.Car;
import Window.MainWindow;

public class StockCar extends Stock<Car>{

    public StockCar(int q_limit) {
        super(q_limit);
    }

    @Override
    public Car getFromStock() throws InterruptedException {
        synchronized(MainWindow.stc) {
            MainWindow.stc.rq_active++;
            MainWindow.stc.notify();
        }
        return super.getFromStock();
    }
}

