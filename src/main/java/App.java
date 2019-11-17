import kafka.ConsumerExample;
import kafka.KafkaProperties;
import kafka.ProducerExample;
import kafka.enums.Topic;
import properties.PropertyLoader;

import static kafka.enums.Topic.DEMO;

public class App {
    public static void main(String[] args) {
        //ProducerExample.runProducer(3);
        String pathToLocalKafkaProperties = "/home/johnny/IdeaProjects/kafka_draft/src/main/resources/env.properties";
        System.out.println(new PropertyLoader(pathToLocalKafkaProperties).getProps());

        KafkaProperties properties = new KafkaProperties(pathToLocalKafkaProperties);
        new ProducerExample(properties, DEMO.getName()).sendDefaultMessages(4);
        new ConsumerExample(properties, DEMO.getName()).runConsumer();
    }
}
