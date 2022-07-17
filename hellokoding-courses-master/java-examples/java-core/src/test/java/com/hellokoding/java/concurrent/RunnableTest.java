package com.hellokoding.java.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class RunnableTest {
    @Test
    public void testRunnable() throws InterruptedException, ExecutionException {
        AtomicInteger counter = new AtomicInteger();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Object result = executorService.submit(new RunnableCounter(counter)).get();
        assertThat(result).isNull();
        assertThat(counter.get()).isEqualTo(100);

        executorService.shutdown();
    }
}
