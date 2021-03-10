package com.cleancrud.cleancrudrest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath*:cleancrud-beans.xml"})
public class BeansLoader {
}
