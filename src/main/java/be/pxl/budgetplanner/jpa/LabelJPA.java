package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.LabelDAO;
import be.pxl.budgetplanner.data.Label;

import javax.persistence.*;
import java.util.List;

public class LabelJPA implements LabelDAO, AutoCloseable {
    private EntityManagerFactory emf;
    private EntityManager em;

    public LabelJPA() {
        emf = Persistence.createEntityManagerFactory("budgetplanner");
        em = emf.createEntityManager();
    }

    public EntityTransaction startTransaction() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        return tx;
    }

    private void closeTransaction(EntityTransaction tx) {
        tx.commit();
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }

    @Override
    public List<Label> getLabels() {
        EntityTransaction tx = startTransaction();
        List<Label> labels = em.createQuery("from Label", Label.class).getResultList();
        closeTransaction(tx);
        return labels;
    }

    @Override
    public Label getLabel(int id) {
        EntityTransaction tx = startTransaction();
        Label label = em.find(Label.class, id);
        closeTransaction(tx);
        return label;
    }

    @Override
    public void addLabel(Label label) {
        EntityTransaction tx = startTransaction();
        em.persist(label);
        closeTransaction(tx);
    }

    @Override
    public void removeLabel(Label label) {
        EntityTransaction tx = startTransaction();
        em.remove(label);
        closeTransaction(tx);
    }
}
