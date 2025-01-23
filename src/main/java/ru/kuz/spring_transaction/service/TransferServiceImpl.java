package ru.kuz.spring_transaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kuz.spring_transaction.exception.TransferServiceException;
import ru.kuz.spring_transaction.model.TransferEntity;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.repo.TransferRepository;

@Service
public class TransferServiceImpl
        implements TransferService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";   // Сброс цвета
    private final TransferRepository transferRepository;

    private final BeanFactory beanFactory;

    public TransferServiceImpl(TransferRepository transferRepository
            , BeanFactory beanFactory
    ) {
        this.transferRepository = transferRepository;
        this.beanFactory = beanFactory;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean transfer(TransferRestModel transferRestModel) {

        LOGGER.info("{} Start transfer method {}", GREEN, RESET);
        try {
            TransferEntity transferEntity = new TransferEntity();
            BeanUtils.copyProperties(transferRestModel, transferEntity); // маппинг
            transferRepository.save(transferEntity);

            LOGGER.info("{} Transfer method callRemoteService {}", GREEN, RESET);
//            callRemoteService();
//            transferService.callRemoteService(); // вызываем через прокси
            beanFactory.getBean(TransferService.class).callRemoteService(); // вызываем через прокси
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new TransferServiceException(ex);
        }
        LOGGER.info("{} Finish transfer method {}", GREEN, RESET);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void callRemoteService() {
        LOGGER.info("{} CallRemoteService method {}", BLUE, RESET);
    }
}
