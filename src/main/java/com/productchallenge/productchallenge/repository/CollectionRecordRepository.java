package com.productchallenge.productchallenge.repository;

import com.productchallenge.productchallenge.domain.CollectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRecordRepository extends JpaRepository<CollectionRecord, Long> {
    CollectionRecord save(CollectionRecord collectionRecord);
}
