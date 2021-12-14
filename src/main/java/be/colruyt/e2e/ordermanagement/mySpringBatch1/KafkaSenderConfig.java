package be.colruyt.e2e.ordermanagement.mySpringBatch1;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wim Van den Brande on 20/08/2018.
 */
@Configuration
public class KafkaSenderConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.retries}")
    private int producerRetries;

    @Value("${spring.kafka.producer.client-id}")
    private String clientIdConfig;

    @Value("${spring.kafka.producer.defaultTopic}")
    private String defaultTopic;

    @Value("${spring.kafka.producer.requestTimeoutMS}")
    private int requestTimeoutMS;

    @Value("${spring.kafka.producer.transactionTimeoutMS}")
    private int transactionTimeoutMS;

    @Value("${spring.kafka.producer.maxBlockMS}")
    private int maxBlockMS;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, clientIdConfig);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMS);
        props.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, transactionTimeoutMS);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Customer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.ByteArraySerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Customer> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Customer> kafkaTemplate() {
        KafkaTemplate<String, Customer> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setDefaultTopic(defaultTopic);
        return kafkaTemplate;
    }

}
