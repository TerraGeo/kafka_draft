package kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsumerExample {
    private KafkaProperties properties;
    private String topic;
    private final Consumer<Long, String> consumer;

    public ConsumerExample(KafkaProperties properties, String topic) {
        this.properties = properties;
        this.topic = topic;
        consumer = createConsumer();
    }

    private Consumer<Long, String> createConsumer() {
        final Consumer<Long, String> consumer = new KafkaConsumer<>(properties.getConsumerProperties());
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }

    public List<String> getConsumerRecords(final int giveUp) {
        List<String> recordValues = new ArrayList<>();

        for (int noRecordsCount = 0; noRecordsCount < giveUp; noRecordsCount++) {
            ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(500));

            if (!records.isEmpty()) {
                 recordValues = getConsumerRecordValues(records);
            } else continue;
            consumer.commitAsync();
        }
        consumer.close();
        return recordValues;
    }

    private List<String> getConsumerRecordValues(ConsumerRecords<Long, String> records) {
        List<String> messageValues = new ArrayList<>();
        records.forEach(r -> messageValues.add(r.value()));
        return messageValues;
    }
}
