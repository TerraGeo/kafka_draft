package kafka;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class KafkaTest extends BaseTest {

    @Test
    public void producerTest() {
        String simpleMessage = "Hi, it is a Kafka producer test. " + System.currentTimeMillis();

        producer.sendMessage(simpleMessage);
        List<String> messages = consumer.getConsumerRecords(6);

        assertEquals(simpleMessage, messages.get(messages.size() - 1));
    }
}
