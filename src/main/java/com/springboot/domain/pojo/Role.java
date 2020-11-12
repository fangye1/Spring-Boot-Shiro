package com.springboot.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/11/11 17:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("Role")
public class Role {
    /**
     * 唯一标识
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色代号
     */
    private String code;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 删除标识：0删除，1存活
     */
    private Integer active;
}
