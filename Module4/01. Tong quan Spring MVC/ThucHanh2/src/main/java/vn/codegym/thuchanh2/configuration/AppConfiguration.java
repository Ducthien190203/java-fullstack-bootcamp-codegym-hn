package vn.codegym.thuchanh2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import vn.codegym.thuchanh2.service.CustomerService;
import vn.codegym.thuchanh2.service.impl.SimpleCustomerServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "vn.codegym.thuchanh2")
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setPrefix("/WEB-INF/templates/");
       viewResolver.setSuffix(".jsp");
       return viewResolver;
    }

    @Bean
    public CustomerService customerService() {
       return new SimpleCustomerServiceImpl();
    }
}