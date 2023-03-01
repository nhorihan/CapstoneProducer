package com.capstone.producer.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Service
public class KafkaProducerExample {

    private final static String TOPIC_NAME = "TutorialTopic";
    private final static String BOOTSTRAP_SERVER = "ec2-35-173-74-249.compute-1.amazonaws.com:9092";

    private static Logger logger = Logger.getLogger(String.valueOf(KafkaProducerExample.class));

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer-TESTER");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }

    public static RecordMetadata runProducer(String message) {
        final Producer<Long, String> producer = createProducer();

        try {
            logger.info("Sending message to Topic");
            final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC_NAME, message);
            RecordMetadata metadata = producer.send(record).get();
            return metadata;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            logger.info("Flushing and closing Producer");
            producer.close();
            producer.flush();
        }
    }

}
