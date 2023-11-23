package com.productchallenge.productchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCollectionRecordRes {
    private String areaName;
    private String averageCollectionAt;
    private List<String> collectionTimes;
}