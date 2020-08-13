package app.dao.impl;

import app.dao.DeveloperDAO;
import app.model.Developer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public class DeveloperDAOImpl implements DeveloperDAO {

	private static final Logger logger = Logger.getLogger(DeveloperDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public DeveloperDAOImpl() {
	}


	@Override
	public List<Developer> findByPosition(String position) {
		logger.info("Get developers as " + position);
		Session session = sessionFactory.getCurrentSession();
		return  session.createQuery("FROM Developer WHERE position = :position")
				.setParameter("position", position)
				.getResultList();
	}

	@Override
	public List<Developer> loadStudents() {
		logger.info("Get all Developers");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Developer").getResultList();
	}

	@Override
	public void addDeveloper(String name, Integer age, String position){
		logger.info("Get all Developers");
		Session session = sessionFactory.getCurrentSession();
		session.persist(new Developer(name, age, position));
	}

	@Override
	public void throwException() throws Exception{
		throw new RuntimeException("FooBar");
	}

}
