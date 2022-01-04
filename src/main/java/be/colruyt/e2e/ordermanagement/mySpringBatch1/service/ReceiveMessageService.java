package be.colruyt.e2e.ordermanagement.mySpringBatch1.service;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.poc.Dummy1;
import be.colruyt.e2e.ordermanagement.mySpringBatch1.repo.poc.Dummy1Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ReceiveMessageService {
    @Autowired
    public Dummy1Repository dummy1Repository;

    @JmsListener(destination = "DummyQueue1")
    @Transactional
    public void receiveMessage1(Dummy1 message) {
        log.info("Received on DummyQueue1 <" + message + ">");
//        dummy1Repository.save(message);

//        if (message.getId() < 0) {
//            throw new RuntimeException("Invalid id. Message should be on queue still.");
//        }
    }

    @JmsListener(destination = "DummyQueue2")
    @Transactional
    public void receiveMessage2(Dummy1 message) {
        log.info("Received on DummyQueue2 <" + message + ">");
    }

    @JmsListener(destination = "DummyQueue3")
    @Transactional
    public void receiveMessage3(Dummy1 message) {
        log.info("Received on DummyQueue3 <" + message + ">");
        if (message.getName().length() <= 5) {
            throw new RuntimeException("Receiving problem. Invalid Name (too short).");
        }
    }

    @JmsListener(destination = "MyTopic1")
    // TODO : to fix : subscribe does not work
    public void receiveMessage5(Dummy1 message) {
        log.info("Subscriber 1A - received on MyTopic1 <" + message + ">");
    }

    @JmsListener(containerFactory = "topicListenerFactory", destination = "MyTopic1")
    // TODO : to fix : subscribe does not work
    public void receiveMessage4(Dummy1 message) {
        log.info("Subscriber 1B - received on MyTopic1 <" + message + ">");
    }

}
