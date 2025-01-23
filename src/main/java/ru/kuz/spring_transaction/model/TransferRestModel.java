package ru.kuz.spring_transaction.model;

import java.math.BigDecimal;


public record TransferRestModel(String senderId,
                                String recipientId,
                                BigDecimal amount) {
}
