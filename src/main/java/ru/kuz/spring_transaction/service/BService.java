package ru.kuz.spring_transaction.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kuz.spring_transaction.exception.TransferServiceException;
import ru.kuz.spring_transaction.model.TransferEntity;
import ru.kuz.spring_transaction.repo.TransferRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";   // Сброс цвета

    private final TransferRepository transferRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = TransferServiceException.class)
    public boolean methodB() {
        LOGGER.info("{} Start methodB {}",BLUE,RESET);
        try{
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setRecipientId("Helena");
        transferEntity.setSenderId("Klim");
        transferEntity.setAmount(BigDecimal.valueOf(90));
        transferRepository.save(transferEntity);
        LOGGER.info("{} Finish methodB {}",BLUE,RESET);
        throw new TransferServiceException(new Exception("gggg"));
    }catch (Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        throw new TransferServiceException(ex);
    }
//        return true;
    }
}
