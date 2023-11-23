package com.productchallenge.productchallenge.service;

import com.productchallenge.productchallenge.domain.Area;
import com.productchallenge.productchallenge.dto.GetAllAreaRes;
import com.productchallenge.productchallenge.dto.GetCollectionRecordRes;
import com.productchallenge.productchallenge.dto.PostAreaCollectionRes;
import com.productchallenge.productchallenge.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final CollectionRecordService collectionRecordService;

    public PostAreaCollectionRes visitArea(Long areaId){
        // 수거 지역 조회
        Area area = loadArea(areaId);

        // 수거 내역 기록
        LocalDateTime collectionAt = collectionRecordService.recordCollection(area);

        return createResponseMessage(collectionAt, area.getName());
    }

    private PostAreaCollectionRes createResponseMessage(LocalDateTime collectionAt, String areaName) {
        String message = collectionAt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + " 부로 '" + areaName + "' 지역의 수거함 QR코드 인식에 성공했습니다.";
        return new PostAreaCollectionRes(message);
    }

    public GetCollectionRecordRes findAreaCollectionRecord(Long areaId) {
        Area area = loadArea(areaId);

        List<LocalDateTime> collectionTimes = collectionRecordService.findCollectionRecordTime(area);
        List<String> collectionRecord = collectionTimes.stream().map(a -> a.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))).collect(Collectors.toList());

        return GetCollectionRecordRes.builder()
                .areaName(area.getName())
                .averageCollectionAt(calculateAverage(collectionTimes))
                .collectionTimes(collectionRecord)
                .build();
    }

    private String calculateAverage(List<LocalDateTime> collectionTimes){
        long totalSeconds = 0;
        long durationSize = 0;
        for(int i = 0; i < collectionTimes.size() - 1; i++){
            LocalDateTime end = collectionTimes.get(i);
            LocalDateTime start = collectionTimes.get(i + 1);
            long seconds = ChronoUnit.SECONDS.between(start, end);
            totalSeconds += seconds;
            durationSize += 1;
        }

        double averageDurations = (double) totalSeconds / durationSize;

        Long days = (long) averageDurations / (24 * 60 * 60);
        Long hours = (long) (averageDurations % (24 * 60 * 60)) / (60 * 60);
        Long minutes = (long) (averageDurations % (60 * 60)) / 60;

        return days + "일 " + hours + "시간 " + minutes + "분";
    }

    private Area loadArea(Long areaId){
        return areaRepository.findById(areaId).orElseThrow(() -> new IllegalArgumentException("areaId를 확인해주세요"));
    }

    public List<GetAllAreaRes> findAllArea() {
        return areaRepository.findAll().stream()
                .map(area -> new GetAllAreaRes(area.getId(), area.getName()))
                .collect(Collectors.toList());
    }

    public List<GetAllAreaRes> searchArea(String keyword) {
        return areaRepository.findAreaByNameContaining(keyword).stream()
                .map(area -> new GetAllAreaRes(area.getId(), area.getName()))
                .collect(Collectors.toList());
    }

}
