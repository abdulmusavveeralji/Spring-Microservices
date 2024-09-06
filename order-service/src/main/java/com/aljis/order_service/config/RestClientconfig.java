package com.aljis.order_service.config;

import com.aljis.order_service.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientconfig {

    @Value("${inventory.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryRestClient() {
        RestClient build = RestClient.builder().baseUrl(inventoryServiceUrl).build();

        var restClientAdapter = RestClientAdapter.create(build);
        var serviceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return serviceProxyFactory.createClient(InventoryClient.class);
    }
}
