package sra1.jee.projet.drive.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages={"sra1.jee.projet.drive.repository"})
//@EnableTransactionManagement
public class JPAConfig {
    
    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("myApp");
        return emf;
    }
    
    @Bean(name="transactionManager")
    public PlatformTransactionManager transactionManager (EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager(emf);
        txManager.setPersistenceUnitName("myApp");
        return txManager;
    }
}
