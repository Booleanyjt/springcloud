package com.tao.springcloud.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulApplication.class, args);
	}

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private ConsulClient consulClient;

	@PostConstruct
	public void destroy() {
		System.out.println("unregister consul services: " + this.appName);

		List<HealthService> response = consulClient.getHealthServices(this.appName, false, null).getValue();
		for(HealthService service : response) {
			service.getChecks().forEach(check -> {
				if(check.getStatus() != Check.CheckStatus.PASSING) {
					consulClient.agentServiceDeregister(check.getServiceId());
					System.out.println("unregister : " + check.getServiceId());
				}
			});
		}
	}


}
