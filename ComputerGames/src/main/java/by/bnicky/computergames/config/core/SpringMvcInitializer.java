/**
 * SpringMvcInitializer.java
 */
package by.bnicky.computergames.config.core;

import by.bnicky.computergames.config.ApplicationConfig;
import by.bnicky.computergames.config.SecurityConfig;
import by.bnicky.computergames.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Nick Korp
 */
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Create root config
     * @return Class<?>[]
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class, SecurityConfig.class};
    }

    /**
     * Create servlet config
     * @return Class<?>[]
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Servlet mapping
     * @return  String[]
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}