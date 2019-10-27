package com.jxau.service.impl;

import com.jxau.dao.ProvinceDao;
import com.jxau.dao.impl.ProvinceDaoImpl;
import com.jxau.domain.Province;
import com.jxau.service.ProvinceService;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
    //声明dao
    private ProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }
}
