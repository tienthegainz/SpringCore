package app.dao.impl;

import app.MyAnnotation;
import app.aspect.LoggingAspect;
import app.dao.DeveloperDAO;
import app.model.Developer;
import app.validator.DeveloperValidator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Repository
public class DeveloperDAOImpl implements DeveloperDAO {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DeveloperValidator developerValidator;


    public DeveloperDAOImpl() {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Developer> findByPosition(String position) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Developer WHERE position = :position")
                .setParameter("position", position)
                .getResultList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Developer> loadDevelopers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Developer").getResultList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addDeveloper(String name, Integer age, String position) throws Exception {
        try {
            Session session = sessionFactory.getCurrentSession();
            Developer newDev = new Developer(name, age, position);
            DataBinder binder = new DataBinder(newDev);
            binder.addValidators(developerValidator);
            binder.validate();
            BindingResult results = binder.getBindingResult();
            if (results.hasErrors()) {
                logger.info(results);
                throwException();
            } else {
                session.persist(newDev);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Value not allow");
        }
        return;
    }

    @MyAnnotation
    @Override
    public void throwException() throws Exception {
        throw new RuntimeException("FooBar");
    }

}
