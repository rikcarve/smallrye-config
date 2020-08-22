package io.smallrye.config.source.consul;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;

@MessageLogger(projectCode = "SRCFG", length = 5)
interface ConsulConfigLogging extends BasicLogger {
    ConsulConfigLogging log = Logger.getMessageLogger(ConsulConfigLogging.class,
            ConsulConfigLogging.class.getPackage().getName());

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = 5000, value = "Could not establish connection to consul: %s")
    void failedToEstablishConnection(String message);

    @LogMessage(level = Logger.Level.INFO)
    @Message(id = 4503, value = "Successfully established connection to Consul. Current cluster leader is %s")
    void connectionEstablished(String host);
}
