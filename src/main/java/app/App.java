package app;

import kafka.ConsumerExample;
import kafka.KafkaProperties;
import kafka.ProducerExample;

import static kafka.enums.Topic.DEMO;

public class App {
    public static void main(String[] args) {
        KafkaProperties properties = new KafkaProperties(System.getProperty("kafka.properties.path"));
        new ProducerExample(properties, DEMO.getName()).sendDefaultMessages(4);

        System.out.println(new ConsumerExample(properties, DEMO.getName()).getConsumerRecords(10));
    }
}
