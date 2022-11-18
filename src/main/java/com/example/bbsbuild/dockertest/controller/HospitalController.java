package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.entity.Hospital;
import com.example.bbsbuild.dockertest.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if (optionalHospital.isEmpty()) {
            return "hospitals/error";
        } else {
            model.addAttribute("hospital", optionalHospital.get());
            return "hospitals/show";
        }
    }

    @GetMapping("")
    public String searchList(@RequestParam(required = false) String keyword, Model model, Pageable pageable) {
        Page<Hospital> hospitals;
        if(keyword == null) { hospitals = hospitalRepository.findAll(pageable); }
        else { hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable); }
        log.info("keyword:{}", keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());

        return "hospitals/list";
    }
}