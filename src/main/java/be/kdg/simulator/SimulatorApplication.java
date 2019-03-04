package be.kdg.simulator;

import be.kdg.simulator.Input.RandomModus;
import be.kdg.simulator.Output.CameraBerichtToRabbitMQ;
import be.kdg.simulator.model.CameraBerichten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = "be.kdg.simulator")
//@Import({ApplicationConfiguration.class})
//@EnableScheduling
//@ComponentScan("be.kdg.simulator")
//@ImportResource("classpath:/spring/spring-config.xml")
public class SimulatorApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SimulatorApplication.class);
	//private final CameraBerichten cameraBerichten;

	@Autowired
	private Environment env;

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

		cameraBerichten.setDelay(Integer.valueOf( env.getProperty("app.delay")));
		cameraBerichten.setCameraInputModus(factory.getBean(RandomModus.class));
		cameraBerichten.setOutputModusCameraBerichten(cameraBerichtToRabbitMQ); ;
		//Foutmelding bij getBean door het rabbittemplate
		//cameraBerichten.setOutputModusCameraBerichten(factory.getBean(CameraBerichtToRabbitMQ.class)); ;
		cameraBerichten.CreateCameraBerichten();
	}

	//Configuratie van RabbitMQ
	//1) aanmaken van een nieuwe exchange
	@Bean
	public TopicExchange cameraBerichtenExchange(){
		//return new TopicExchange(EXCHANGE_NAME);
		//System.out.println( env.getProperty("rabbitmq.exchangeName")	);
		return new TopicExchange(env.getProperty("rabbitmq.exchangeName"));
	}

	//2) aanmaken van een queue
	@Bean
	public Queue parsingQueue(){
		return new Queue(env.getProperty("rabbitmq.queueName"));
	}

	//3) koppeling van de queue en de exchange
	@Bean
	public Binding queueToExchangeBinding(){
		return BindingBuilder.bind(parsingQueue()).to(cameraBerichtenExchange()).with(env.getProperty("rabbitmq.routingKey"));
	}

	@Bean
	public Jackson2JsonMessageConverter producerMessageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerMessageConverter());
		return rabbitTemplate;
	}


}

