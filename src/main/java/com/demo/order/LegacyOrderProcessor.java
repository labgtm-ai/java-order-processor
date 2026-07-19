package com.demo.order;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CompletionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyOrderProcessor {

    // SRAO: Replaced System.out.println with SLF4J logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LegacyOrderProcessor.class);

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

        // SRAO: Replaced explicit null check with Optional.ofNullable().ifPresentOrElse()
        Optional.ofNullable(order)
                .ifPresentOrElse(
                        o -> {
                            // Original processing logic for non-null order
                            CompletableFuture.runAsync(() -> {
                                LOGGER.info("Processing Order : {}", o.getOrderId());
                            })
                            .thenRunAsync(() -> {
                                // This block executes after the non-blocking delay
                                o.setStatus("PROCESSED");
                            }, CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS)) // Simulate long running task with a non-blocking delay
                            .whenComplete((v, ex) -> {
                                if (ex != null) {
                                    // Unwrap CompletionException to get the actual cause
                                    Throwable cause = (ex instanceof CompletionException) ? ex.getCause() : ex;
                                    // SRAO: Pass the specific exception if it's an Exception, otherwise wrap Throwable in a new Exception.
                                    callback.onFailure((cause instanceof Exception) ? (Exception) cause : new Exception(cause));
                                } else {
                                    callback.onSuccess(o);
                                }
                            });
                        },
                        () -> {
                            // Logic for null order
                            callback.onFailure(
                                    new IllegalArgumentException("Order cannot be null"));
                        }
                );
    }
}