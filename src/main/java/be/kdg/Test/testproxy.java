package be.kdg.Test;

import be.kdg.processor.Service.ProxyCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("be.kdg.processor.Service")
//@ComponentScan("com.package.where.my.class.is")
public class testproxy implements CommandLineRunner{

   @Autowired
  private ProxyCameraService proxyCameraService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(testproxy.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {
        try {
            //spring boot maakt object aan in container volgens het singleton pattern.
            //       ApplicationContext factory = new AnnotationConfigApplicationContext();
//
//        ProxyCameraService proxyCameraService = factory.getBean(ProxyCameraService.class);
       /*     System.out.println(proxyCameraService.get(1));
            System.out.println(proxyCameraService.get(2));
            System.out.println(proxyCameraService.get(3));
            System.out.println(proxyCameraService.get(4));
            System.out.println(proxyCameraService.get(5));
            System.out.println(proxyCameraService.get(6));
            System.out.println(proxyCameraService.get(7));*/

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}