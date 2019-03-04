package be.kdg.simulator;

import be.kdg.simulator.Input.RandomModus;
import be.kdg.simulator.model.CameraBerichten;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Profile("!dev")

@Configuration
//@ComponentScan(basePackages = "be.kdg.simulator")
//@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    @PostConstruct
    public void test(){
        System.out.println("Application Configuration loading");
    }

/*    @Bean
    public CameraBerichtToRabbitMQ getRabbitMQ(){
        return new CameraBerichtToRabbitMQ();
    }*/

    @Bean
    public CameraBerichten getCameraBerichten(){
        return new CameraBerichten();
    }

    @Bean
    public RandomModus getRandomModus(){
        return new RandomModus();
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Application Configuration destroy");
        //System.out.println(delay);
    }



}
