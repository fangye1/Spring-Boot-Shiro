package com.springboot.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * @author qizenan
 * @date 2020/11/11 16:56
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Alias("Permission")
public class Permission {
    /**
     * 唯一标识
     */
    private Long id;
    /**
     * url地址
     */
    private String url;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 删除标识：0删除，1存活
     */
    private Integer active;
}
