package com.productchallenge.productchallenge.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "areas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "createdAt", cascade = CascadeType.ALL)
    private List<CollectionRecord> collectionRecords = new ArrayList<>();

    @CreationTimestamp
    private Date createdAt;

    public static Area newArea(String name){
        Area area = new Area();
        area.name = name;
        return area;
    }
}