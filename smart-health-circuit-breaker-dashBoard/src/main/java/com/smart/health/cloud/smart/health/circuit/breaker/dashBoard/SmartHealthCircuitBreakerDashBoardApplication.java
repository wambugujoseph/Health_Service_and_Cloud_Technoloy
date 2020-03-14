package com.smart.health.cloud.smart.health.circuit.breaker.dashBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class SmartHealthCircuitBreakerDashBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHealthCircuitBreakerDashBoardApplication.class, args);
	}

}
