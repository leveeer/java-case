package com.jxau.p2p.base.domain;


import lombok.Data;

/**
 * logininfo
 * @author xw
 */
@Data
public class Logininfo extends BaseDomain{

    public static final int STATE_NORMAL = 0;// 正常状态
    public static final int STATE_LOCK = 1;// 用户锁定

    private String username;
    private String password;
    private int state;
}