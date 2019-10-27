package com.jxau.service;

import com.jxau.domain.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();

    public String findAllJson();
}
