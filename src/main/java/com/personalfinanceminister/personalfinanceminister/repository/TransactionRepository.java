package com.personalfinanceminister.personalfinanceminister.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.personalfinanceminister.personalfinanceminister.modal.Transaction;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByType(String type);
}