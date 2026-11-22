package com.eventticket.repository;

import com.eventticket.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUsernameAndSepetId(String username, Long sepetId);

    List<Cart> findByUsername(String username);

    Optional<Cart> findBySepetId(Long sepetId);

}
