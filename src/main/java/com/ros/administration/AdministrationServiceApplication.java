package com.ros.administration;

import java.util.ArrayList;
import java.util.List;

import com.ros.administration.mapper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "AdministrationServiceAPI", version = "1.0", description = "API for Administration Service"))
public class AdministrationServiceApplication {

	@Value(value = "${swagger.url}")
	public String url;

	public static void main(String[] args) {
		SpringApplication.run(AdministrationServiceApplication.class, args);
	}

	
	 @Bean public OpenAPI customOpenAPI() { 
		 Server server = new Server();
	 List<Server>servers = new ArrayList(); 
	 server.setUrl(url);
	 servers.add(server); 
	 OpenAPI openAPI =new OpenAPI();
	 openAPI.setServers(servers);
	
	 return openAPI; // return new OpenAPI().servers(List.of(server)); 
	 }
	 

	// Mapstruct mappings

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}

	@Bean
	public AccountMapper accountMapper() {
		return new AccountMapperImpl();
	}

	@Bean
	public AddressMapper addressMapper() {
		return new AddressMapperImpl();
	}
	


	@Bean
	public RestaurantMapper restaurantMapper() {
		return new RestaurantMapperImpl();
	}

	@Bean
	public IntegrationMapper intergrationMapper() {
		return new IntegrationMapperImpl();
	}

	@Bean
	public ClientMapper clientMapper() {
		return new ClientMapperImpl();
	}
	
	
	  @Bean 
	  public SubscriptionMapper subscriptionMapper() { 
		  return new  SubscriptionMapperImpl(); }
	 

	@Bean
	public RestaurantConfigurationMapper restaurantmapperConfiguration() {
		return new RestaurantConfigurationMapperImpl();
	}


	@Bean
	public ShiftConfigurationMapper shiftConfigurationMapper(){
		return new ShiftConfigurationMapperImpl();
	}

	@Bean
	public DepartmentConfigurationMapper departmentConfigurationMapper(){
		return new DepartmentConfigurationMapperImpl();
	}
	
	
	  @Bean public ProductMapper ProductMapper() { 
		  return new ProductMapperImpl();
	  }
	 

}
