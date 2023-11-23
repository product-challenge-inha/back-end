package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.dto.PostAreaCollectionRes;
import com.productchallenge.productchallenge.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final CollectionRecordService collectionRecordService;

    public PostAreaCollectionRes visitArea(Long areaId){
        // 수거 지역 조회
        Area area = loadArea(areaId);

        // 수거 내역 기록
        Date collectionAt = collectionRecordService.RecordCollection(area);

        return createResponseMessage(collectionAt, area.getName());
    }

    private PostAreaCollectionRes createResponseMessage(Date collectionAt, String areaName) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 a HH:mm:ss");
        String message = simpleDateFormat.format(collectionAt) + " 부로 '" + areaName + "' 지역의 수거함 QR코드 인식에 성공했습니다.";
        return new PostAreaCollectionRes(message);
    }

    private Area loadArea(Long areaId){
        return areaRepository.findById(areaId).orElseThrow(() -> new IllegalArgumentException("areaId를 확인해주세요"));
    }
}
