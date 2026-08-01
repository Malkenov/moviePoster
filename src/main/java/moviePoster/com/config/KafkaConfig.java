package moviePoster.com.config;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

import static moviePoster.com.config.KafkaTopicsConfig.*;

@Configuration
public class KafkaConfig {

    @Bean
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(KafkaTemplate<String, Object> kafkaTemplate) {
        Map<String, String> topicToDlq = Map.of(
                TICKET_PURCHASED, TICKET_PURCHASED_DLQ,
                TICKET_CANCELLED, TICKET_CANCELLED_DLQ,
                SEAT_RESERVATION_EXPIRED, SEAT_RESERVATION_EXPIRED_DLQ,
                NEW_MOVIE_RELEASED, NEW_MOVIE_RELEASED_DLQ
        );

        return new DeadLetterPublishingRecoverer(kafkaTemplate,
                (record, ex) -> {
                    String dlqTopic = topicToDlq.getOrDefault(record.topic(), record.topic() + "-dlq");
                    return new TopicPartition(dlqTopic, record.partition());
                });
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory,
            DeadLetterPublishingRecoverer recoverer) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // 3 попытки с интервалом 1 сек, потом — в DLQ
        factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 3)));

        return factory;
    }
}