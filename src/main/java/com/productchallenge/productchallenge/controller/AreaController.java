package com.productchallenge.productchallenge.controller;

import com.productchallenge.productchallenge.dto.PostAreaCollectionRes;
import com.productchallenge.productchallenge.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
public class AreaController {
    private final AreaService areaService;

    @PostMapping
    public ResponseEntity<PostAreaCollectionRes> visitArea(@RequestParam Long areaId) {
        return ResponseEntity.ok(areaService.visitArea(areaId));
    }
}
