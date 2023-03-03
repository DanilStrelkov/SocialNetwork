package ru.skillbox.zerone.backend.configuration;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CloudinaryProperties.class)
public class CloudinaryConfig {
  private final CloudinaryProperties properties;
  @Bean
  public Cloudinary cloudinary() {
    return new Cloudinary(Map.of(
        "cloud_name", properties.getCloudName(),
        "api_key", properties.getApiKey(),
        "api_secret", properties.getApiSecret()));
  }
}