package ru.kuz.spring_transaction.service;

import ru.kuz.spring_transaction.model.TransferRestModel;

public interface TransferService {
    boolean transfer(TransferRestModel transferRestModel);

    void callRemoteService();

}
