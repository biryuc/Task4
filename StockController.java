package STOCK;



import PARTS.Request;
import Window.MainWindow;

public class StockController {
    private static volatile Thread th;
    public int rq_active=0;
    public StockController() {
        th = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.interrupted()) {

                    if(th == null){
                        synchronized(StockController.class) {

                            if (rq_active==0) {
                                try {
                                    StockController.this.wait();
                                } catch (InterruptedException e) {
                                    break;
                                }
                            }
                            rq_active--;
                        }
                        MainWindow.pool.addRequest(new Request());
                    }
                }}});
        th.start();
    }

    public void terminate() {
        th.interrupt();
    }
}

