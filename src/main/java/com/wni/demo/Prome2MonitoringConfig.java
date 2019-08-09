package com.wni.demo;

import io.prometheus.client.exporter.MetricsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by niwei on 2019/8/8.
 */
@Configuration
public class Prome2MonitoringConfig {
    @Bean
    ServletRegistrationBean servletRegistrationBean() {

        return new ServletRegistrationBean(new MetricsServlet(), "/ydmetrics");
    }
}
