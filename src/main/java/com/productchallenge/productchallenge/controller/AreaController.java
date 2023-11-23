package com.productchallenge.productchallenge.controller;

import com.productchallenge.productchallenge.dto.GetAllAreaRes;
import com.productchallenge.productchallenge.dto.GetCollectionRecordRes;
import com.productchallenge.productchallenge.dto.PostAreaCollectionRes;
import com.productchallenge.productchallenge.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<PostAreaCollectionRes> visitArea(@RequestParam Long areaId) {
        return ResponseEntity.ok(areaService.visitArea(areaId));
    }

    @GetMapping("/{areaId}")
    public ResponseEntity<GetCollectionRecordRes> findAreaCollectionRecord(@PathVariable Long areaId) {
        return ResponseEntity.ok(areaService.findAreaCollectionRecord(areaId));
    }

    @GetMapping
    public ResponseEntity<List<GetAllAreaRes>> findAllArea() {
        return ResponseEntity.ok(areaService.findAllArea());
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetAllAreaRes>> searchArea(@RequestParam String keyword) {
        return ResponseEntity.ok(areaService.searchArea(keyword));
    }
}
