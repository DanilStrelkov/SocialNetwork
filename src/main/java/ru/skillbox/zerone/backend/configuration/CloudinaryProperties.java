package ru.skillbox.zerone.backend.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "cloudinary")
public class CloudinaryProperties {
  private String cloudName;
  private String apiKey;
  private String apiSecret;
}