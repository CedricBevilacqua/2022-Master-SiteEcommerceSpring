package sra1.jee.projet.drive.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);
        ctx.register(JPAConfig.class);
        ctx.register(MailConfig.class);
        ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        registration.setLoadOnStartup(1);
        registration.addMapping("*.json", "*.html", "*.data");
        
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(ctx);
        container.addListener(contextLoaderListener);
    }

}
