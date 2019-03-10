package be.kdg.processor.Configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rabbitmq")
public class RabbitMqProperties {
    private String queueName;
    private String exchangeName;
    private String routingKey;

    public String getQueueName() {
        return queueName;
    }
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    public String getExchangeName() {
        return exchangeName;
    }
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }
    public String getRoutingKey() {
        return routingKey;
    }
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }


}
