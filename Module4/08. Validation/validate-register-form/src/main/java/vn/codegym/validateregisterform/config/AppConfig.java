package vn.codegym.validateregisterform.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import vn.codegym.validateregisterform.service.UserService;
import vn.codegym.validateregisterform.service.impl.UserServiceImpl;

/**
 * Spring configuration class for the web application.
 * Enables Spring MVC and configures Thymeleaf as the view resolver.
 */
@Configuration
@EnableWebMvc
@ComponentScan("vn.codegym.validateregisterform")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Sets the ApplicationContext for this configuration.
     *
     * @param applicationContext The ApplicationContext to be set.
     * @throws BeansException if there is an issue setting the context.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Configures the Thymeleaf template resolver.
     *
     * @return A configured SpringResourceTemplateResolver.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * Configures the Thymeleaf template engine.
     *
     * @return A configured SpringTemplateEngine.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * Configures the Thymeleaf view resolver.
     *
     * @return A configured ThymeleafViewResolver.
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    /**
     * Provides a singleton instance of {@link UserService}.
     *
     * @return An instance of {@link UserServiceImpl}.
     */
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}