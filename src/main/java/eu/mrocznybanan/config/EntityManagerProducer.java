package eu.mrocznybanan.config;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

public class EntityManagerProducer {

    @PersistenceContext
    EntityManager em;

    @Produces
    @TransactionScoped
    public EntityManager exposeEm() {        
        return em;
    }

    /*
    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
    */

}
