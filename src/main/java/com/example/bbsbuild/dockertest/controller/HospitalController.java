package com.example.bbsbuild.dockertest.controller;

import com.example.bbsbuild.dockertest.domain.entity.Hospital;
import com.example.bbsbuild.dockertest.domain.entity.Review;
import com.example.bbsbuild.dockertest.repository.HospitalRepository;
import com.example.bbsbuild.dockertest.repository.ReviewRepository;
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
    private final ReviewRepository reviewRepository;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model, Pageable pageable) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        Page<Review> reviews = reviewRepository.findByHospitalId(id, pageable);

        if (optionalHospital.isEmpty()) {
            return "hospitals/error";
        } else {
            log.info("review cnt:{} {}", reviews.getSize(), reviews);
            model.addAttribute("hospital", optionalHospital.get());
            model.addAttribute("reviews", reviews);
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