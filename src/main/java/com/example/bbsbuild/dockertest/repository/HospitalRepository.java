package com.example.bbsbuild.dockertest.repository;

import com.example.bbsbuild.dockertest.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByRoadNameAddressContaining(String keyword); // 포함
    List<Hospital> findByRoadNameAddressStartsWith(String keyword); // 시작
    List<Hospital> findByRoadNameAddressEndingWith(String keyword); // 끝남

    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작

    List<Hospital> findByTotalNumberOfBedsBetween(Integer start, Integer end);

}