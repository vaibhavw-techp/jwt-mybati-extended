package com.demo.jwt.JwtMybatisApplication.service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private ApplicationContext applicationContext;

    public final String topicHai = "temp-topic";


//    @KafkaListener(topics = "temp-topic", groupId = "consumer_group_1")
//    public String receiveData(String message) throws Exception {
//        String temp = "HELLO-2";
//        String quoted = "\"" + temp + "\"";
//        if(message.equals(quoted)) {
//            System.out.println("HIII");
//            x++;
//            throw new Exception("HEY");
//        }
//        System.out.println(message);
//        System.out.println(x);
//
//        return "SUCCESS";
//    }

    private void shutDownApplication() {
        SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // return the exit code
                return 0;
            }
        });
    }

    @RetryableTopic(attempts = "3", dltTopicSuffix = ".my-own-dlt")
    @KafkaListener(topics = "temp-topic", groupId = "consumer_group_1", containerFactory = "studentListener")
    public void receiveData1(ConsumerRecord<String, String> record) throws InterruptedException {
        String message = record.value();
        long offset = record.offset();
        int partition = record.partition();
        String topic = record.topic();

        System.out.println("Received message: " + message);
        System.out.println("From topic: " + topic + ", partition: " + partition + ", offset: " + offset);

        if(message.equals("2")) {
            throw new RuntimeException("Exception Aa gya");
        }

        System.out.println("Processing message again: " + message);
        System.out.println("From topic: " + topic + ", partition: " + partition + ", offset: " + offset);
    }

//    @KafkaListener(topics = "temp-topic", groupId = "consumer_group_1", containerFactory = "studentListener")
//    public void receiveData2(String message) {
//
//        if(message.equals("5")) {
//            System.out.println("Hello-2");
////            ack.acknowledge();
//              throw new RuntimeException("Ha Ha Ha");
//        }
//        System.out.println(message + " Consumer-Group-2");
////        ack.acknowledge();
//    }



//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-1")
//    public String receiveData1(String message) {
//        System.out.println(message + "  I'm the second Consumer\n");
//        return message;
//    }
//
//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-1")
//    public String receiveData2(String message) {
//        System.out.println(message + "  I'm the Third Consumer\n");
//        return message;
//    }
//
//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-1")
//    public String receiveData3(String message) {
//        System.out.println(message + "  I'm the Fourth Consumer\n");
//        return message;
//    }
//
//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-2")
//    public String consumergroup20(String message) {
//        System.out.println(message + "  I'm the C2- First Consumer\n");
//        return message;
//    }
//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-2")
//    public String consumergroup21(String message) {
//        System.out.println(message + "  I'm the  C2- Second Consumer\n");
//        return message;
//    }
//    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-2")
//    public String consumergroup22(String message) {
//        System.out.println(message + "  I'm the C2- Third Consumer\n");
//        return message;
//    }

}
