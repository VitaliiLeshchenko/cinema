package config;

import cinema.config.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
public class AppConfigTest {
}
