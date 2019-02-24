package be.kdg.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorApplication {

    @Autowired
    private static Consumer c;

    public static void main(String[] args) throws InterruptedException {
        //initialisatie van de spring container
        //ConfigurableApplicationContext context =  SpringApplication.run(ProcessorApplication.class, args);
        //spring boot maakt object aan in container volgens het singleton pattern
        SpringApplication.run(ProcessorApplication.class, args);
        //c = context.getBean(Consumer.class);
    }




}
