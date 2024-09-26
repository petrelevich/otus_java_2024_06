package ru.petrelevich;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.petrelevich.transformer.IntTransformer;

@RestController
public class IndexController {
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    private final IntTransformer intTransformer;

    public IndexController(IntTransformer intTransformer) {
        this.intTransformer = intTransformer;
    }

    // curl -v http://localhost:8081/transform?val=5
    @GetMapping("/transform")
    public long transform(@RequestParam(name = "val") long value) {
        var startTime = System.currentTimeMillis();
        log.info("get request:{}", value);
        var result = intTransformer.transform(value);
        log.info("request processing time:{} sec.", (System.currentTimeMillis() - startTime) / 1000);
        return result;
    }
}
