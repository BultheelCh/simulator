package be.kdg.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProcessorApplication {

    public static void main(String[] args)
    {
        //initialisatie van de spring container
        //ConfigurableApplicationContext context =  SpringApplication.run(ProcessorApplication.class, args);
        //spring boot maakt object aan in container volgens het singleton pattern
        SpringApplication.run(ProcessorApplication.class, args);
        //c = context.getBean(Consumer.class);
    }
}
