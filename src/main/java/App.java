import kafka.ConsumerExample;
import kafka.KafkaProperties;
import kafka.ProducerExample;
import properties.PropertyLoader;

import static kafka.enums.Topic.DEMO;

public class App {
    public static void main(String[] args) {
        String pathToLocalKafkaProperties = "D:\\workspace\\repo\\kafka_draft\\src\\main\\resources\\env.properties";
        System.out.println(new PropertyLoader(pathToLocalKafkaProperties).getProps());

        KafkaProperties properties = new KafkaProperties(pathToLocalKafkaProperties);
        new ProducerExample(properties, DEMO.getName()).sendDefaultMessages(4);
        System.out.println(new ConsumerExample(properties, DEMO.getName()).getConsumerRecords(10));
    }
}
