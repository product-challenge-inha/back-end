package com.productchallenge.productchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAreaRes {
    private Long areaId;
    private String areaName;
}