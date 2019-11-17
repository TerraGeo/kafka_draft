package kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

public class ConsumerExample {
    private KafkaProperties properties;
    private String topic;

    public ConsumerExample(KafkaProperties properties, String topic) {
        this.properties = properties;
        this.topic = topic;
    }

    public Consumer<Long, String> createConsumer() {
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(properties.getConsumerProperties());

        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

    public void runConsumer() {
        final Consumer<Long, String> consumer = createConsumer();

        final int giveUp = 5;  int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(Duration.ofMillis(500));

            if (consumerRecords.count() == 0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                    record.key(), record.value(),
                    record.partition(), record.offset()));

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
}
