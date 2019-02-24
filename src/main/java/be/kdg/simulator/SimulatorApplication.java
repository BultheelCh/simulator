package be.kdg.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@ComponentScan("be.kdg.simulator")
//@ImportResource("classpath:/spring/spring-config.xml")
public class SimulatorApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(SimulatorApplication.class);

	//Constanten
	public static final String EXCHANGE_NAME="cameraberichten_tx";
	public static final String QUEUE ="default_parser_q";
	public static final String ROUTING_KEY = "cameraberichten";
	public static final String FILE_PATH = "c:\\temp\\KDG\\SoftwareArchitecture\\input.csv";

	//Variabelen koppelen met Configuration file (application.properties)
	@Value("${rabbitmq.exchangeName}")
	private  String echangeName;
	@Value("${rabbitmq.queueName}")
	private String queueName;
	@Value("${rabbitmq.routingKey}")
	private String routingKey;
	@Value("${importPath}")
	private  String fileName;

	//getters and setters
	public String getEchangeName() {
		return echangeName;
	}
	public String getQueueName() {
		return queueName;
	}
	public String getRoutingKey() {
		return routingKey;
	}
	public String getFileName() {
		return fileName;
	}

	@Autowired
	private BerichtenGenerator generator;

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
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("exchangeName: " + echangeName);
		log.info("queueName: " + queueName );
		log.info("routingKey: " + routingKey);
		log.info("fileName: " + fileName);

		//spring boot maakt object aan in container volgens het singleton pattern.
		generator.CreateBerichtenByModus(Modus.Random, 1000, fileName, "," , false ) ;
	}

	//Configuratie van RabbitMQ
	//1) aanmaken van een nieuwe exchange
	@Bean
	public TopicExchange cameraBerichtenExchange(){
		//return new TopicExchange(EXCHANGE_NAME);
		return new TopicExchange(echangeName);
	}

	//2) aanmaken van een queue
	@Bean
	public Queue parsingQueue(){
		return new Queue(queueName);
	}

	//3) koppeling van de queue en de exchange
	@Bean
	public Binding queueToExchangeBinding(){
		return BindingBuilder.bind(parsingQueue()).to(cameraBerichtenExchange()).with(routingKey);
	}
}

