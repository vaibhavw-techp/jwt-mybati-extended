package com.demo.jwt.JwtMybatisApplication;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
@MapperScan({"com.demo.jwt.JwtMybatisApplication.repository","com.demo.jwt.JwtMybatisApplication.repository.mgrepository"})
public class JwtMybatisApplication {

	public static void main(String[] args) {


		SpringApplication.run(JwtMybatisApplication.class, args);

		final Consumer<Long, String> consumer = new KafkaConsumer<>(getProps());
		consumer.subscribe(Collections.singletonList("temp-topic"));
		TopicPartition actualTopicPartition = new TopicPartition("temp-topic",0);
		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofSeconds(60));
			consumerRecords.forEach(record -> {
				try {
					if(record.value().equals("5")){
						TimeUnit.MILLISECONDS.sleep(10000);
						System.out.println("\n\n\n SPECIAL CASE WARNING!\n\n\n");
//						while(true);
					}
					else{
						TimeUnit.MILLISECONDS.sleep(0);
						System.out.println("Data: " + record.value());
					}

				} catch (InterruptedException e) {
				}
			});
			final long committedOffset = consumer.committed(Collections.singleton(actualTopicPartition)).get(actualTopicPartition).offset();
			final long consumerCurrentOffset = consumer.position(actualTopicPartition);
			System.out.println("Poll finish.. consumer-offset: " + consumerCurrentOffset + " - committed-offset: " + committedOffset + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		}
	}

	@SuppressWarnings("checkstyle:LineLength")
	private static Map<String, Object> getProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_group_1");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //  Default: latest
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // Default: true
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1); // Default: 500
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 2000); // Default: 5000
		return props;
	}

}
