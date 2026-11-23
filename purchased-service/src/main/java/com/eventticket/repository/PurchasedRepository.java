package com.eventticket.repository;

import com.eventticket.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByUsername(String username);

}
