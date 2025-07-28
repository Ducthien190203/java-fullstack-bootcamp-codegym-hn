package vn.codegym.customerprovincemanagement.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import vn.codegym.customerprovincemanagement.formatter.ProvinceFormatter;
import vn.codegym.customerprovincemanagement.repository.IProvinceRepository;

/**
 * Web MVC configuration for the application.
 * Configures view resolvers, formatters, and enables Spring Data web support.
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebMvcConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Sets the ApplicationContext for this configuration.
     * @param applicationContext The ApplicationContext to set.
     * @throws BeansException if the context cannot be set.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Adds custom formatters to the registry.
     * @param registry The FormatterRegistry to add formatters to.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(provinceFormatter());
    }

    /**
     * Configures and provides the ProvinceFormatter bean.
     * @return A new instance of ProvinceFormatter.
     */
    @Bean
    public ProvinceFormatter provinceFormatter() {
        return new ProvinceFormatter(applicationContext.getBean(IProvinceRepository.class));
    }

    /**
     * Configures and provides the SpringResourceTemplateResolver for Thymeleaf.
     * @return A new instance of SpringResourceTemplateResolver.
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
     * Configures and provides the SpringTemplateEngine for Thymeleaf.
     * @return A new instance of SpringTemplateEngine.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * Configures and provides the ThymeleafViewResolver.
     * @return A new instance of ThymeleafViewResolver.
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
}
