package com.productchallenge.productchallenge.service;


import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.domain.CollectionRecord;
import com.productchallenge.productchallenge.repository.CollectionRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CollectionRecordService {
    private final CollectionRecordRepository collectionRecordRepository;

    public Date RecordCollection(Area area){
        CollectionRecord newCollectionRecord = CollectionRecord.newCollectionRecord(area);
        collectionRecordRepository.save(newCollectionRecord);
        return newCollectionRecord.getCreatedAt();
    }
}
