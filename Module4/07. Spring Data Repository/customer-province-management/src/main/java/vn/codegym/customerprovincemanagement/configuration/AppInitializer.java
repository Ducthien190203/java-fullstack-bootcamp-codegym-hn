package vn.codegym.customerprovincemanagement.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Initializes the Spring DispatcherServlet and configures the application context.
 * This class replaces the traditional web.xml configuration.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Specifies the configuration classes for the root application context.
     * These classes typically contain infrastructure beans (e.g., data sources, transaction managers).
     * @return An array of configuration classes.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{PersistenceJPAConfig.class};
    }

    /**
     * Specifies the configuration classes for the DispatcherServlet application context.
     * These classes typically contain web-related beans (e.g., controllers, view resolvers).
     * @return An array of configuration classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class, AppConfiguration.class};
    }

    /**
     * Specifies the URL patterns that the DispatcherServlet will handle.
     * @return An array of servlet mappings.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Specifies the filters to be applied to the DispatcherServlet.
     * @return An array of servlet filters.
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[]{characterEncodingFilter};
    }
}
