package be.kdg.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
        //spring boot maakt object aan in container volgens het singleton pattern.
        System.out.println(proxyCameraService.get(1));
    }

}