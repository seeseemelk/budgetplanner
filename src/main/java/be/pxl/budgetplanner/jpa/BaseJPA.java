package be.pxl.budgetplanner.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

abstract public class BaseJPA implements AutoCloseable
{
	protected final EntityManagerFactory emf;
	protected final EntityManager em;

	public BaseJPA() {
		this("budgetplanner");
	}

	public BaseJPA(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	@Override
	public void close() throws Exception {
		em.close();
		emf.close();
	}

	protected EntityTransaction startTransaction() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		return tx;
	}

	protected void closeTransaction(EntityTransaction tx) {
		tx.commit();
	}
}
