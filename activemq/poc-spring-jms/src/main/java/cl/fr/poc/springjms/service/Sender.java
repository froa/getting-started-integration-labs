package cl.fr.poc.springjms.service;

import javax.jms.Destination;

/**
 * Created by froa on 8/23/15.
 */
public interface Sender {

    void send(final String text);

    void send(final Destination destination,final String text);
}
