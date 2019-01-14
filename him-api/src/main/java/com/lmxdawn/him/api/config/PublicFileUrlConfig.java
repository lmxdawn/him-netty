package com.lmxdawn.him.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 公共文件的配置
 */

@Configuration
@ConfigurationProperties(prefix = "public-file")
public class PublicFileUrlConfig {

    /**
     * 上传的地址
     */
    private static String uploadUrl;

    public void setUploadUrl(String uploadUrl) {
        PublicFileUrlConfig.uploadUrl = uploadUrl;
    }

    public static String getUploadUrl() {
        return uploadUrl;
    }

    /**
     * 资源的域名
     */
    private static String domain;

    public void setDomain(String domain) {
        PublicFileUrlConfig.domain = domain;
    }

    public static String getDomain() {
        return domain;
    }
}
