package org.example;

import java.util.concurrent.*;

public class Schedule {
    public static void main(String[] args ){
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        final ScheduledExecutorService service= Executors.newScheduledThreadPool(1);
        final ScheduledFuture scheduledFuture=service.scheduleAtFixedRate((Runnable) transactionAnalysis,1,5, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                service.shutdown();
            }
        },30,TimeUnit.SECONDS);
    }
}
