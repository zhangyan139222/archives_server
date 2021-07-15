package com.nantian.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 跨域配置
 * @author DELL
 */
@Configuration
public class CorsConfiguration extends WebMvcConfigurationSupport {


    @Value("${file.server.uploadFolder}")
    private String winStorageRootFolder;

    @Value("${file.server.uploadVirtual}")
    private String virtualPath;

    @Value("${file.server.linuxFolder}")
    private String linStorageRootFolder;


    /**
     * swagger-ui.html  替换  doc.html
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler(virtualPath + "**").addResourceLocations("file:" + winStorageRootFolder);
        } else {
            registry.addResourceHandler(virtualPath + "**").addResourceLocations("file:" + linStorageRootFolder);
        }
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}