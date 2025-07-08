package com.aftertaste.onlinebanking.transaction.repository;

import com.aftertaste.onlinebanking.transaction.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

    @Query("Select t from Transaction t where (t.receiverAccountNumber = :accountNumber OR t.senderAccountNumber = :accountNumber) ORDER by t.timestamp DESC")
    Page<Transaction> fetchTransactions(@Param("accountNumber") String accountNumber, Pageable pg);
}
