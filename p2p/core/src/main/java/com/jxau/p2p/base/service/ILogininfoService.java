package com.jxau.p2p.base.service;

public interface ILogininfoService {

    /**
     * 验证用户名是否存在
     * @param username
     * @return true：不存在   false：存在
     */
    boolean checkUsername(String username);

    /**
     * 用户注册
     * @param username
     * @param password
     */
    void register(String username, String password);
}
