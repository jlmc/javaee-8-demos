package org.costajlmpp.account.boundary;

import org.costajlmpp.account.entity.Account;
import org.costajlmpp.account.entity.Moviment;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountManager {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<Account> event;


    public List<Account> search() {
        return this.em.createNamedQuery(Account.FIND).getResultList();
    }

    public Account create(Account account) {
        final Account newAccount = this.em.merge(account);
        this.em.flush();
        return newAccount;
    }

    public void addMoviment(String id, Moviment moviment) {
        final Account account = getAccount(id);

        account.addMoviment(moviment);

        this.event.fireAsync(account);
    }

    private Account getAccount(String id) {
        return this.em.createNamedQuery(Account.FIND_BY_ID, Account.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
