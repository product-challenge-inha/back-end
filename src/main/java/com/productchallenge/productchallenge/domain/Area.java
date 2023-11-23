package com.productchallenge.productchallenge.domain;


// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;
import lombok.Data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Getter
@Table(name = "areas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Area newArea(String name){
        Area area = new Area();
        area.name = name;
        return area;
    }

}