package com.aiden.bank.repository;

import com.aiden.bank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransferRepository extends JpaRepository<Transfer, Long> {
}
