package com.jxau.p2p.base.service.impl;

import com.jxau.p2p.base.domain.Logininfo;
import com.jxau.p2p.base.mapper.LogininfoMapper;
import com.jxau.p2p.base.service.ILogininfoService;
import com.jxau.p2p.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;


    @Override
    public boolean checkUsername(String username) {
        return this.logininfoMapper.getCountByUsername(username) <= 0;
    }

    @Override
    public void register(String username, String password) {
        //判断用户名是否存在
        int count = this.logininfoMapper.getCountByUsername(username);

        //不存在，设置并保存
        if (count <= 0){
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NORMAL);
            this.logininfoMapper.insert(logininfo);
        }
        //存在，抛出异常
        throw new RuntimeException("用户名已存在");
    }
}
