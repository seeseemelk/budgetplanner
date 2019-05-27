package be.pxl.budgetplanner.jpa;

import be.pxl.budgetplanner.dao.LabelDAO;
import be.pxl.budgetplanner.beans.Label;

import javax.persistence.*;
import java.util.List;

public class LabelJPA extends BaseJPA implements LabelDAO {
    public LabelJPA()
    {
    }

    public LabelJPA(String persistenceUnitName)
    {
        super(persistenceUnitName);
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
    public Label addLabel(Label label) {
        EntityTransaction tx = startTransaction();
        em.persist(label);
        closeTransaction(tx);
        return label;
    }

    @Override
    public Label removeLabel(Label label) {
        EntityTransaction tx = startTransaction();
        em.remove(label);
        closeTransaction(tx);
        return label;
    }
}
