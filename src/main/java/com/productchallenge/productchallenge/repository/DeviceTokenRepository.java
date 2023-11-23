package com.productchallenge.productchallenge.repository;

import com.productchallenge.productchallenge.domain.DeviceToken;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
    @Query(value = "SELECT * FROM devicetokens ORDER BY id LIMIT 1", nativeQuery = true)
    DeviceToken findUserFCMToken();
}