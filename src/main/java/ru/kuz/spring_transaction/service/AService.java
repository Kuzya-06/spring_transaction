package ru.kuz.spring_transaction.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kuz.spring_transaction.exception.TransferServiceException;
import ru.kuz.spring_transaction.model.TransferEntity;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.repo.TransferRepository;

@Service
@RequiredArgsConstructor
public class AService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";   // Сброс цвета
    private final BService bService;
    private final TransferRepository transferRepository;


    @Transactional()
    public boolean methodA(TransferRestModel transferRestModel){

      LOGGER.info("{} Start methodA {}",GREEN,RESET);
     try {
         TransferEntity transferEntity = new TransferEntity();

         BeanUtils.copyProperties(transferRestModel, transferEntity); // маппинг
         transferRepository.save(transferEntity);
         LOGGER.info("{} Transfer methodB {}", GREEN, RESET);
          bService.methodB();
         LOGGER.info("{} methodB в methodA {}", GREEN, RESET);
//         throw new TransferServiceException(new Exception());
     }catch (Exception ex) {
         LOGGER.error(ex.getMessage(), ex);
         throw new TransferServiceException(ex);
     }
        return true;
    }
}
