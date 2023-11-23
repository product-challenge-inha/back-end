package com.productchallenge.productchallenge.repository;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.CollectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CollectionRecordRepository extends JpaRepository<CollectionRecord, Long> {
    CollectionRecord save(CollectionRecord collectionRecord);
    @Query("select c.createdAt from CollectionRecord c where c.area = :area order by c.createdAt DESC")
    List<LocalDateTime> findAllCreateAtByArea(Area area);
}
