package ru.kuz.spring_transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.service.TransferService;
import ru.kuz.spring_transaction.service.TransferServiceImpl;

@RestController
@RequestMapping("/transfers")
public class TransfersController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private TransferServiceImpl transferService;

    public TransfersController(TransferServiceImpl transferService) {
        this.transferService = transferService;
    }

    @PostMapping()
    public boolean transfer(@RequestBody TransferRestModel transferRestModel) {
        boolean answer = transferService.transfer(transferRestModel);
        LOGGER.info("transfer = {}",answer);
        return answer;
    }
}