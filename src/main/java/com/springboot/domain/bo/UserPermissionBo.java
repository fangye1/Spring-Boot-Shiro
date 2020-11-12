package com.springboot.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qizenan
 * @date 2020/11/11 16:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserPermissionBo {
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 角色代码
     */
    private String permissionName;
}
