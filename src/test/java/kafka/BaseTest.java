package kafka;

import kafka.enums.Topic;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ProducerExample producer;
    protected ConsumerExample consumer;

    @BeforeClass
    public void setUp() {
        String pathToLocalKafkaProperties = System.getProperty("kafka.properties.path");
        KafkaProperties properties = new KafkaProperties(pathToLocalKafkaProperties);

        producer = new ProducerExample(properties, Topic.DEMO.getName());
        consumer = new ConsumerExample(properties, Topic.DEMO.getName());
    }
}
