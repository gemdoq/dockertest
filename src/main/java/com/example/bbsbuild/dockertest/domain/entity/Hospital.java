package com.example.bbsbuild.dockertest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "nation_wide_hospitals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hospital_name;
    private String road_name_address;
}