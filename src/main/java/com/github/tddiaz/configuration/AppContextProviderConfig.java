package com.github.tddiaz.configuration;

import com.github.tddiaz.AppContextProvider;
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
