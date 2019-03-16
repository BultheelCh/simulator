package be.kdg.simulator.Configuration;

import be.kdg.simulator.Input.RandomModus;
import be.kdg.simulator.model.CameraBerichten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class ApplicationConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Autowired
    private Environment env;

    @PostConstruct
    public void test(){
        log.info("Application Configuration loading");
    }

    @Bean
    public CameraBerichten getCameraBerichten(){
        return new CameraBerichten();
    }

    @Bean
    public RandomModus getRandomModus(){
        return new RandomModus();
    }

    @Bean
    public  AppProperties getAppProperties(){
        return new AppProperties();
    } 


    @PreDestroy
    public void destroy(){
        log.info("Application Configuration destroy");
    }



}
