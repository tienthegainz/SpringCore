package app.dao.impl;

import app.MyAnnotation;
import app.dao.DeveloperDAO;
import app.model.Developer;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeveloperDAOImpl implements DeveloperDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public DeveloperDAOImpl() {
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Developer> findByPosition(String position) {
		Session session = sessionFactory.getCurrentSession();
		return  session.createQuery("FROM Developer WHERE position = :position")
				.setParameter("position", position)
				.getResultList();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<Developer> loadStudents() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Developer").getResultList();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addDeveloper(String name, Integer age, String position){
		Session session = sessionFactory.getCurrentSession();
		session.persist(new Developer(name, age, position));
	}

	@MyAnnotation
	@Override
	public void throwException() throws Exception{
		throw new RuntimeException("FooBar");
	}

}
