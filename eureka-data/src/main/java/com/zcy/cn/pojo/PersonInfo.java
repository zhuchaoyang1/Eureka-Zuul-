package com.zcy.cn.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人员信息Redis
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {
    /**
     * 工号
     */
    private String code;

    private String username;

    private String password;

    private Integer role;

}
