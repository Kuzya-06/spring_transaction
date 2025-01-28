package ru.kuz.spring_transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.service.AService;

@RestController
@RequestMapping("/ab")
public class ABController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final AService aService;

    public ABController(AService aService) {
        this.aService = aService;
    }

    @PostMapping()
    public boolean methodA(@RequestBody TransferRestModel transferRestModel) {
        boolean answer = aService.methodA(transferRestModel);
        LOGGER.info("answer = {}", answer);
        return answer;
    }
}
