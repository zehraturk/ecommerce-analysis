package com.ecommerce.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class SearchProducer {
    Producer producer;

    @PostConstruct
    public void init(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"machine-ip:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, new StringSerializer().getClass().getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new StringSerializer().getClass().getName());
        producer=new KafkaProducer<String, String>(properties);
    }

    public void send(String search_term){
        ProducerRecord<String, String> record=new ProducerRecord<String, String>("search-analysis-stream",search_term);
        producer.send(record);
        System.out.println("send");
    }

    public void close(){
        producer.close();
    }
}
