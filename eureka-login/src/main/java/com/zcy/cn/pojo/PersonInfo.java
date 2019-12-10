package com.zcy.cn.pojo;

import lombok.Data;

/**
 * 人员信息Redis
 */
@Data
public class PersonInfo {
    /**
     * 工号
     */
    private String code;

    private String username;

    private String password;

    private Integer role;

}
