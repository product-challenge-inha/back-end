package com.productchallenge.productchallenge.repository;


import com.productchallenge.productchallenge.domain.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Area save(Area area);
    List<Area> findAll();
    List<Area> findAreaByNameContaining(String name);

}
