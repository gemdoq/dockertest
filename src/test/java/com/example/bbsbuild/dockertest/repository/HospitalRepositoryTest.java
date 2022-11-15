package com.example.bbsbuild.dockertest.repository;

import com.example.bbsbuild.dockertest.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
        }
        System.out.println(hospitals.size());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");

        inClues.add("보건진료소");
        List<Hospital> hospitalList = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (Hospital hospital :
                hospitalList) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("송파구의 병원 이름하고 주소")
    void containing() {
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("경희로 시작되는 병원이름")
    void startsWith() {
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("경희");// 가톨릭 서울 연세 경희1
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상 수가 10개 이상 20개 미만인 병원이름")
    void between() {
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetween(10, 20);
        printHospitalNameAndAddress(hospitals);
    }
}