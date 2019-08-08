package com.wni.demo;

//import io.micrometer.core.instrument.MeterRegistry;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@SpringBootApplication
//@EnableEurekaClient
public class MyspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyspringbootApplication.class, args);
	}

//		@Bean MeterRegistryCustomizer<MeterRegistry> configurer(
//				@Value("${spring.application.name}") String applicationName) {
//				return (registry) -> registry.config().commonTags("application", applicationName);
//		}

}
