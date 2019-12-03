package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class ProducerExample {
    private KafkaProperties properties;
    private String topic;
    private final Producer<Long, String> producer;

    public ProducerExample(KafkaProperties properties, String topic) {
        this.properties = properties;
        this.topic = topic;
        producer = createProducer();
    }

    private Producer<Long, String> createProducer() {
        return new KafkaProducer<>(properties.getProducerProperties());
    }


    public void sendMessage(String message) {
        long time = System.currentTimeMillis();

        try {
            final ProducerRecord<Long, String> record = new ProducerRecord<>(topic, message);

            RecordMetadata metadata = producer.send(record).get();

            long elapsedTime = System.currentTimeMillis() - time;
            System.out.printf("sent record(value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d\n", record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void sendDefaultMessages(int messageCount) {
        long time = System.currentTimeMillis();

        for (long i = time; i < time + messageCount; i++) {
            sendMessage("Hello from Kafka " + i);
        }
    }
}
