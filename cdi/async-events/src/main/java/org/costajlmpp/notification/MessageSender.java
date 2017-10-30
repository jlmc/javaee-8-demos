package org.costajlmpp.notification;

import org.costajlmpp.account.entity.Account;

import javax.ejb.Stateless;
import javax.enterprise.event.ObservesAsync;

/**
 * @author costa
 * on 29/09/2017.
 */
@Stateless
public class MessageSender {

    public void sendNotification(@ObservesAsync Account account) {

        System.out.println("Calculate the total of all book, and send Notification to all managers");

    }
}
