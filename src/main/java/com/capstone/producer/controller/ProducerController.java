package com.capstone.producer.controller;

import com.capstone.producer.kafka.KafkaProducerExample;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProducerController {

    @RequestMapping(
            path = "/message/{message}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public String controller(@PathVariable("message") String message) {
        RecordMetadata metadata = KafkaProducerExample.runProducer(message);
        return "Message Sent to Kafka Broker was: " + message + "\n" + "MetaData from send: " + metadata.toString();
    }
}
