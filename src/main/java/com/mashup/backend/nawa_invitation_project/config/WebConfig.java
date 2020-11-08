package com.mashup.backend.nawa_invitation_project.config;

import com.mashup.backend.nawa_invitation_project.interceptor.DeviceIdentifierInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private DeviceIdentifierInterceptor deviceIdentifierInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(deviceIdentifierInterceptor)
        .addPathPatterns("/apis/v2/users");
  }
}
