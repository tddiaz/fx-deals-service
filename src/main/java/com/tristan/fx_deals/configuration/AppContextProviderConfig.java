package com.tristan.fx_deals.configuration;

import com.tristan.fx_deals.AppContextProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tristandiaz on 10/31/17.
 */
@Configuration
public class AppContextProviderConfig {

    @Bean
    public AppContextProvider appContextProvider() {
        return new AppContextProvider();
    }
}
