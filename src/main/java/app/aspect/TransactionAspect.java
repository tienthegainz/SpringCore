package app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class TransactionAspect {

    @Autowired
    private SessionFactory sessionFactory;

    @Pointcut("execution(* app.dao.*.*(..))")
    public void anyDAOMethod(){
    }

//    @Before("anyDAOMethod()")
//    public void startTransaction(JoinPoint jp){
//        System.out.println("Start session");
//        sessionFactory.openSession();
//    }

//    @After("anyDAOMethod()")
//    public void closeTransaction(JoinPoint jp){
//        System.out.println("Close session");
//        Session session = sessionFactory.getCurrentSession();
//        session.close();
//    }
//
//    @AfterThrowing(value = "anyDAOMethod()", throwing = "ex")
//    public void rollBack(Throwable ex){
//        System.out.println("Rollback transaction");
//        Session session = sessionFactory.getCurrentSession();
//        session.getTransaction().rollback();
//    }
}
