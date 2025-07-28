package vn.codegym.validatemusicinfo;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.springframework.web.filter.CharacterEncodingFilter;
import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Configures and returns the servlet filters.
     * A CharacterEncodingFilter is added to ensure UTF-8 encoding for all requests.
     *
     * @return An array of servlet filters.
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }
}