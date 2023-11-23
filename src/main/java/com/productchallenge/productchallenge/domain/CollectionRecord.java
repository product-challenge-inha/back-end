package com.productchallenge.productchallenge.domain;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Table(name = "collection_records")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static CollectionRecord newCollectionRecord(Area area){
        CollectionRecord newCollectionRecord = new CollectionRecord();
        newCollectionRecord.area = area;
        return newCollectionRecord;
    }
}