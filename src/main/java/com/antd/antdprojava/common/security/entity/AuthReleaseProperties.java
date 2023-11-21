package com.antd.antdprojava.common.security.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 放行属性设置
 *
 * @author Joshua
 * @version 1.0
 * @date 21/11/2023 1:27
 */
@Data
@ConfigurationProperties(prefix = "security.properties")
public class AuthReleaseProperties {

    /**
     * 放行静态资源
     */
    private List<String> permitStatic;

    /**
     * 放行方法
     */
    private List<String> permitMethod;

    public String[] getPermitStatic() {
        return permitStatic.toArray(new String[0]);
    }

    public String[] getPermitMethod() {
        return permitMethod.toArray(new String[0]);
    }

}
