package com.agharibi.jmsdemo;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsDemoApplication {

    public static void main(String[] args) throws Exception {


        // Setting up simple activeMQ
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(Boolean.FALSE)
                .setJournalDirectory("target/data/journal")
                .addAcceptorConfiguration("invm", "vm://0"));

        server.start();

        SpringApplication.run(JmsDemoApplication.class, args);
    }

}
