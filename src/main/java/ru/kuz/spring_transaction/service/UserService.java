package ru.kuz.spring_transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kuz.spring_transaction.model.TransferEntity;
import ru.kuz.spring_transaction.model.TransferRestModel;
import ru.kuz.spring_transaction.repo.TransferRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {


    private final TransferRepository transferRepository;

//    private final UserService userService;
//
//    public UserService(TransferRepository transferRepository, @Lazy UserService userService) {
//        this.transferRepository = transferRepository;
//        this.userService = userService;
//    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void createUser(TransferRestModel transferRestModel) {
        System.out.println("Begin");
        // Создаем пользователя
        TransferEntity transferEntity = new TransferEntity();
        BeanUtils.copyProperties(transferRestModel, transferEntity);
        System.out.println(transferEntity);
        transferRepository.save(transferEntity);
        
        // Вложенный метод
//        userService.assignRole(transferEntity);
       try {
           assignRole(transferRestModel);
       }catch (RuntimeException e){

       }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void assignRole(TransferRestModel transferRestModel) {

        TransferEntity transferEntity = new TransferEntity();
        BeanUtils.copyProperties(transferRestModel, transferEntity);
        transferEntity.setSenderId("Egor");
        transferEntity.setAmount(new BigDecimal(10000));

        transferRepository.save(transferEntity);
        
        // Проблема на этом этапе вызовет откат только этой вложенной транзакции
        throw new RuntimeException("Ошибка при присвоении роли");
    }
}
