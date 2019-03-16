
package be.kdg.processor.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Configuration
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    @PostConstruct
    public void test(){
        System.out.println("Application Configuration loading");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Application Configuration destroy");
        //System.out.println(delay);
    }

    @Autowired
    public void configureObservers(){


    }


}
