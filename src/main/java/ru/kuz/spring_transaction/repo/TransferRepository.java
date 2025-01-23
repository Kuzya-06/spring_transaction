package ru.kuz.spring_transaction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuz.spring_transaction.model.TransferEntity;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity,Integer> {
}
