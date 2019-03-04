package be.kdg.processor.Test;

import be.kdg.processor.Service.ProxyCameraService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan (basePackages = "be.kdg.processor")
public class testproxy implements CommandLineRunner{

 /*   @Autowired
    private ProxyCameraService proxyCameraService;*/

    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(testproxy.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {
        //spring boot maakt object aan in container volgens het singleton pattern.
        ApplicationContext factory = new AnnotationConfigApplicationContext();

        ProxyCameraService  proxyCameraService = factory.getBean(ProxyCameraService.class);
        System.out.println(proxyCameraService.get(1));
    }

}