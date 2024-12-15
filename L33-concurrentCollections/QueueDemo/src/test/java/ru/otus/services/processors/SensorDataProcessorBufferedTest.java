package ru.otus.services.processors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.DoubleStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.model.SensorData;
import ru.otus.lib.SensorDataBufferedWriter;

@ExtendWith(MockitoExtension.class)
class SensorDataProcessorBufferedTest {
    private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBufferedTest.class);

    public static final int BUFFER_SIZE = 2000;
    public static final String ANY_ROOM = "AnyRoom";

    @Mock
    private SensorDataBufferedWriter writer;

    @Captor
    private ArgumentCaptor<List<SensorData>> captor;

    private SensorDataProcessorBuffered processor;

    @BeforeEach
    void setUp() {
        processor = spy(new SensorDataProcessorBuffered(BUFFER_SIZE, writer));
    }

    @Test
    @Disabled("Удалить до начала тестирования")
    void shouldExecFlushWhenBufferOverFlow() {
        List<SensorData> sensorDataList = getSensorDataForTest(BUFFER_SIZE + BUFFER_SIZE / 2);

        sensorDataList.forEach(sensorData -> processor.process(sensorData));
        var outOfFirstBufferData = new SensorData(LocalDateTime.now(), ANY_ROOM, 10500d);
        processor.process(outOfFirstBufferData);

        verify(processor, times(1)).flush();
        verify(writer).writeBufferedData(captor.capture());
        var flushedData = captor.getValue();

        assertThat(flushedData).hasSize(BUFFER_SIZE);
    }

    @Test
    @Disabled("Удалить до начала тестирования")
    void shouldFlushBufferDataSortedByTime() {
        var sensorDataList = new ArrayList<>(getSensorDataForTest(BUFFER_SIZE - 1));
        var originalSensorDataList = List.copyOf(sensorDataList);
        Collections.shuffle(sensorDataList);

        sensorDataList.forEach(sensorData -> processor.process(sensorData));
        verify(processor, never()).flush();

        processor.flush();

        verify(writer).writeBufferedData(captor.capture());
        var flushedData = captor.getValue();

        assertThat(flushedData).containsExactlyElementsOf(originalSensorDataList);
    }

    @Test
    @Disabled("Удалить до начала тестирования")
    void shouldFlushTheRestOfTheBufferDataWhenOnProcessingEndFired() {
        List<SensorData> sensorDataList = getSensorDataForTest(BUFFER_SIZE + BUFFER_SIZE / 2);
        sensorDataList.forEach(sensorData -> processor.process(sensorData));

        reset(processor, writer);

        processor.onProcessingEnd();

        verify(processor, times(1)).flush();
        verify(writer).writeBufferedData(captor.capture());
        var flushedData = captor.getValue();

        assertThat(flushedData).hasSize(BUFFER_SIZE / 2);
    }

    @RepeatedTest(100)
    @Disabled("Удалить до начала тестирования")
    void shouldCorrectFlushDataFromManyThreads() {
        List<SensorData> sensorDataList = getSensorDataForTest(BUFFER_SIZE - 1);
        sensorDataList.forEach(sensorData -> processor.process(sensorData));

        reset(processor, writer);

        var numberOfThreads = 10;
        var threads = new ArrayList<Thread>();
        var latch = new CountDownLatch(1);
        for (int i = 0; i < numberOfThreads; i++) {
            var thread = new Thread(() -> {
                awaitLatch(latch);
                processor.flush();
            });
            thread.start();
            threads.add(thread);
        }
        latch.countDown();
        threads.forEach(this::joinThread);

        verify(processor, times(numberOfThreads)).flush();
        verify(writer, atLeastOnce()).writeBufferedData(captor.capture());
        var flushedData = captor.getAllValues();

        assertThat(flushedData).hasSize(1);
    }

    @RepeatedTest(1_000)
    @Disabled("Удалить до начала тестирования")
    void shouldCorrectFlushDataAndWriteThreads() throws InterruptedException {
        List<SensorData> sensorDataList = getSensorDataForTest(BUFFER_SIZE - 1);

        var latchReady = new CountDownLatch(2);
        var processFlag = new AtomicBoolean(true);

        var sensorDataBufferedWriter = new SensorDataBufferedWriter() {
            private final List<SensorData> data = new ArrayList<>();

            @Override
            public void writeBufferedData(List<SensorData> bufferedData) {
                data.addAll(bufferedData);
            }

            public List<SensorData> getData() {
                return data;
            }
        };

        var sensorDataProcessorBuffered = new SensorDataProcessorBuffered(BUFFER_SIZE, sensorDataBufferedWriter);
        var writerThread = new Thread(() -> {
            latchReady.countDown();
            awaitLatch(latchReady);
            sensorDataList.forEach(sensorDataProcessorBuffered::process);
            processFlag.set(false);
        });
        var flusherThread = new Thread(() -> {
            latchReady.countDown();
            awaitLatch(latchReady);
            do {
                sensorDataProcessorBuffered.flush();
            } while (processFlag.get());
            sensorDataProcessorBuffered.flush();
        });

        writerThread.start();
        flusherThread.start();

        writerThread.join(100);
        flusherThread.join(100);

        assertThat(sensorDataBufferedWriter.getData()).hasSize(sensorDataList.size());
        assertThat(sensorDataBufferedWriter.getData()).isEqualTo(sensorDataList);
    }

    private List<SensorData> getSensorDataForTest(int limit) {
        var startTime = LocalDateTime.now();
        return DoubleStream.iterate(0.0, d -> d + 1)
                .limit(limit)
                .boxed()
                .map(d -> new SensorData(startTime.plusSeconds(d.longValue()), ANY_ROOM, d))
                .toList();
    }

    private void awaitLatch(CountDownLatch latch) {
        try {
            var result = latch.await(10, TimeUnit.SECONDS);
            if (!result) {
                log.warn("timeout");
            }
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    private void joinThread(Thread thread) {
        try {
            thread.join(10);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
