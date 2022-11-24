package com.example.bbsbuild.dockertest.domain.entity;

import com.example.bbsbuild.dockertest.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nation_wide_hospitals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Integer businessStatusCode;
    private Float totalAreaSize;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(
                hospital.getId(),
                hospital.getHospitalName(),
                hospital.getRoadNameAddress(),
                hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),
                hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }

    public Hospital(Integer id, String hospitalName, String roadNameAddress, Integer businessStatusCode) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.roadNameAddress = roadNameAddress;
        this.businessStatusCode = businessStatusCode;
    }
}