package app;

import app.dao.DeveloperDAO;
import app.dao.impl.DeveloperDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        testHibernate();
    }

    public static void testHibernate() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        DeveloperDAO dao = (DeveloperDAO) app.getBean("developerDAO");

//        dao.addDeveloper("O", 15, "Intern");

        dao.findByPosition("Dev");

        dao.loadStudents();

        try {
            dao.throwException();
        } catch (Exception e) {
        }
//            dao.loadStudents();

    }
}
