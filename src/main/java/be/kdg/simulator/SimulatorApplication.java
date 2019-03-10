package be.kdg.simulator;

import be.kdg.simulator.Configuration.AppProperties;
import be.kdg.simulator.Configuration.ApplicationConfiguration;
import be.kdg.simulator.Input.FileModus;
import be.kdg.simulator.Input.RandomModus;
import be.kdg.simulator.Output.CameraBerichtToRabbitMQ;
import be.kdg.simulator.model.CameraBerichten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
//@ComponentScan(basePackages = "be.kdg.simulator")
//@Import({ApplicationConfiguration.class})
//@EnableScheduling
//@ComponentScan("be.kdg.simulator")
//@ImportResource("classpath:/spring/spring-config.xml")
public class SimulatorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SimulatorApplication.class);

	@Autowired
	private Environment env;
	@Autowired
    private FileModus fileModus;
	@Autowired
	private RandomModus randomModus;
    @Autowired
    AppProperties appProperties;


	@Autowired
	private CameraBerichtToRabbitMQ cameraBerichtToRabbitMQ;

	public static void main(String[] args) throws InterruptedException {
		//Wat gebeurt hier:
		//1)Set up default configuration
		//2)initialisatie van de spring container (= Spring application context)
		//3)Class path scan
		//4)Start Tomcat server (web application)

		//Mogelijkheid 1:
		//SpringApplication.run(SimulatorApplication.class, args);

		//Mogelijkheid 2:
		SpringApplication app = new SpringApplication(SimulatorApplication.class);
		//app.setBannerMode(Banner.Mode.OFF );
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext factory = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

		CameraBerichten cameraBerichten = factory.getBean(CameraBerichten.class);
        cameraBerichten.setDelay(appProperties.getDelay());
        //cameraBerichten.setDelay(Integer.valueOf( env.getProperty("app.delay")));
		//cameraBerichten.setCameraInputModus(factory.getBean(RandomModus.class));
        //cameraBerichten.setCameraInputModus(factory.getBean(FileModus.class));
        cameraBerichten.setCameraInputModus(fileModus);
		cameraBerichten.setOutputModusCameraBerichten(cameraBerichtToRabbitMQ); ;
		//Foutmelding bij getBean door het rabbittemplate
		//cameraBerichten.setOutputModusCameraBerichten(factory.getBean(CameraBerichtToRabbitMQ.class)); ;
		cameraBerichten.CreateCameraBerichten();
	}

}

