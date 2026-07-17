package com.demo.order;

public class LegacyOrderProcessor {

    /**
     * Callback interface used by the legacy processor.
     */
    public interface OrderCallback {

        void onSuccess(Order order);

        void onFailure(Exception exception);

    }

    /**
     * Simulates legacy asynchronous order processing using
     * Thread + Runnable + synchronized + Thread.sleep().
     */
    public synchronized void processOrder(
            final Order order,
            final OrderCallback callback) {

        if (order == null) {

            callback.onFailure(
                    new IllegalArgumentException("Order cannot be null"));

            return;
        }

        Thread worker = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    System.out.println(
                            "Processing Order : " + order.getOrderId());

                    // Simulate long running task
                    Thread.sleep(2000);

                    order.setStatus("PROCESSED");

                    callback.onSuccess(order);

                } catch (InterruptedException e) {

                    callback.onFailure(e);

                    Thread.currentThread().interrupt();

                } catch (Exception ex) {

                    callback.onFailure(ex);

                }

            }

        });

        worker.start();

    }

}