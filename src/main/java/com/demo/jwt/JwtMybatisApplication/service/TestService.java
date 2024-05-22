package com.demo.jwt.JwtMybatisApplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    @Autowired
    private ApplicationContext applicationContext;

    @KafkaListener(topics = "temp-topic", groupId = "consumer-group-1")
    public String receiveData(String message) {
        String theySay = "HELLO-2";
        String quoted = "\"" + theySay + "\"";
        if(message.equals(quoted)) {
            System.out.println(message);
            shutDownApplication();
            throw new RuntimeException("HEY");
        }

        System.out.println(message);


        return "SUCCESS";
    }

    private void shutDownApplication() {
        SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                // return the exit code
                return 0;
            }
        });
    }

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
