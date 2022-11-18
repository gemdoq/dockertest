package com.example.bbsbuild.dockertest.service;

import com.example.bbsbuild.dockertest.domain.dto.HospitalResponse;
import com.example.bbsbuild.dockertest.domain.entity.Hospital;
import com.example.bbsbuild.dockertest.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository;

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalRepository = Mockito.mock(HospitalRepository.class);
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("가져와서 영업중인지 확인할 것")
    void getHospital() {
        Mockito.when(hospitalRepository.findById(1))
                .thenReturn(Optional.of(new Hospital(1, "111", "11111", 13)));

        HospitalResponse hospitalResponse = hospitalService.getHospital(1);

        assertEquals(1, hospitalResponse.getId());
        assertEquals("111", hospitalResponse.getHospitalName());
        assertEquals("11111", hospitalResponse.getRoadNameAddress());
        assertEquals("영업중", hospitalResponse.getBusinessStatusName());
    }
}