package com.demo.order;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LegacyOrderProcessor {

    private static final Logger logger = LoggerFactory.getLogger(LegacyOrderProcessor.class);

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

        // SRAO: Replaced explicit null check with Optional.ofNullable().ifPresentOrElse() for handling null order.
        Optional.ofNullable(order).ifPresentOrElse(
            o -> {
                // SRAO: Replaced manual Thread creation with CompletableFuture for asynchronous execution and non-blocking delay.
                CompletableFuture.runAsync(() -> { // SRAO: Offload initial task to a managed thread pool
                    // SRAO: Replaced System.out.println with SLF4J logger.info for better logging practices.
                    logger.info("Processing Order : {}", o.getOrderId());
                })
                .thenCompose(v -> {
                    // SRAO: Introduce a non-blocking delay for 2 seconds using CompletableFuture.delayedExecutor.
                    // This allows the worker thread to be released during the delay, improving resource utilization.
                    return CompletableFuture.supplyAsync(() -> null, CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS));
                })
                .thenRun(() -> {
                    // This part executes after the 2-second non-blocking delay.
                    o.setStatus("PROCESSED");
                    callback.onSuccess(o);
                })
                .exceptionally(ex -> {
                    // Handle any exceptions that occurred during the async chain.
                    Throwable cause = (ex instanceof java.util.concurrent.CompletionException) ? ex.getCause() : ex;
                    // SRAO: Pass the specific exception type to the callback if it's an Exception, otherwise wrap the Throwable.
                    if (cause instanceof Exception) {
                        callback.onFailure((Exception) cause);
                    } else {
                        callback.onFailure(new Exception("An unexpected error occurred", cause));
                    }
                    return null; // Return null to complete exceptionally
                });
            },
            () -> {
                callback.onFailure(
                        new IllegalArgumentException("Order cannot be null"));
            }
        );
    }

}
