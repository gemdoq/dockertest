package com.example.bbsbuild.dockertest.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HospitalResponse {
        private Integer id;
        private String roadNameAddress;
        private String hospitalName;
        private Integer patientRoomCount;
        private Integer totalNumberOfBeds;
        private String businessTypeName;
        private String businessStatusName;
        private Float totalAreaSize;

        public HospitalResponse(Integer id, String hospitalName, String roadNameAddress, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
                this.id = id;
                this.hospitalName = hospitalName;
                this.roadNameAddress = roadNameAddress;
                this.patientRoomCount = patientRoomCount;
                this.totalNumberOfBeds = totalNumberOfBeds;
                this.businessTypeName = businessTypeName;
                this.businessStatusName = businessStatusName;
                this.totalAreaSize = totalAreaSize;
        }

        public void setBusinessStatusName(String businessStatusName) {
                this.businessStatusName = businessStatusName;
        }
}