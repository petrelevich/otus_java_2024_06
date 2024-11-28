package com.datasrc;

import com.datasrc.producer.DataProducer;
import com.datasrc.producer.StringValue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SourceDataController {
    private static final Logger log = LoggerFactory.getLogger(SourceDataController.class);

    private final DataProducer<Flux<StringValue>> dataProducerFlux;
    private final DataProducer<StringValue> dataProducerStringBlocked;
    private final Executor blockingExecutor;

    public SourceDataController(
            @Qualifier("dataProducerFlux") DataProducer<Flux<StringValue>> dataProducerFlux,
            @Qualifier("dataProducerStringBlocked") DataProducer<StringValue> dataProducerStringBlocked,
            @Qualifier("blockingExecutor") Executor blockingExecutor) {
        this.dataProducerFlux = dataProducerFlux;
        this.dataProducerStringBlocked = dataProducerStringBlocked;
        this.blockingExecutor = blockingExecutor;
    }

    @GetMapping(value = "/data/{seed}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StringValue> data(@PathVariable("seed") long seed) {
        log.info("request for string data, seed:{}", seed);
        var stringData = dataProducerFlux.produce(seed);

        log.info("Method request for string data done");
        return stringData;
    }

    @GetMapping(value = "/data-mono/{seed}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<StringValue> dataMono(@PathVariable("seed") long seed) {
        log.info("request for string data-mono, seed:{}", seed);

        var future = CompletableFuture.supplyAsync(() -> dataProducerStringBlocked.produce(seed), blockingExecutor);
        return Mono.fromFuture(future);
    }
}
