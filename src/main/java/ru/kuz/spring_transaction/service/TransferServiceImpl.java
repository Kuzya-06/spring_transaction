package ru.kuz.spring_transaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuz.spring_transaction.exception.TransferServiceException;
import ru.kuz.spring_transaction.model.TransferEntity;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.repo.TransferRepository;

import java.math.BigDecimal;

@Service
public class TransferServiceImpl
        implements TransferService
{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";   // Сброс цвета
    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    @Transactional(noRollbackFor = TransferServiceException.class)
//    @Transactional()
    public boolean transfer(TransferRestModel transferRestModel) throws  TransferServiceException{

        LOGGER.info("{} Start transfer method {}",GREEN,RESET);
        try {
            TransferEntity transferEntity = new TransferEntity();
            BeanUtils.copyProperties(transferRestModel, transferEntity); // маппинг

            transferRepository.save(transferEntity);

            LOGGER.info("{} Transfer method callRemoteService {}",GREEN,RESET);
            callRemoteService(); // выкидывает RuntimeException()
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new TransferServiceException(ex);
        }
        LOGGER.info("{} Finish transfer method {}",GREEN,RESET);
        return true;
    }

    @Override
//    @Transactional(noRollbackFor = RuntimeException.class)
    @Transactional()
    public void callRemoteService() {
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setSenderId("Misha");
        transferEntity.setRecipientId("Olya");
        transferEntity.setAmount(new BigDecimal(555));
        transferRepository.save(transferEntity);
        LOGGER.info("{} CallRemoteService method {}",BLUE,RESET);
        throw new RuntimeException();
    }
}
