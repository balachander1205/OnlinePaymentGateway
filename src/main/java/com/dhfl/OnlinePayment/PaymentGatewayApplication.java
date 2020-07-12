package com.dhfl.OnlinePayment;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.dhfl.OnlinePayment.controller.PaymentController;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableRabbit
//(basePackages ={"com.dhfl.OnlinePayment.repo"})
@ComponentScan(basePackageClasses = PaymentController.class)
@ComponentScan(basePackages = { "com.dhfl.OnlinePayment" })
//(exclude={DataSourceAutoConfiguration.class})
public class PaymentGatewayApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(PaymentGatewayApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(PaymentGatewayApplication.class);
	}
}
