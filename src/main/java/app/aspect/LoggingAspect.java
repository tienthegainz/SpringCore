package app.aspect;

import app.dao.impl.DeveloperDAOImpl;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* app.dao.*.*(..))")
    public void anyDAOMethod() {
    }

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void anyDbChangeMethod() {
    }


    @Before("anyDAOMethod()")
    public void logDAOMethod(JoinPoint jp) {
        String s = String.format("DAO method signature: %s", jp.getSignature());
        logger.info(s);
    }

    @AfterThrowing(value = "anyDAOMethod()", throwing = "ex")
    public void logDAOError(Throwable ex) {
        logger.error("DAO method throwed Exception: " + ex);
    }

    @Around("anyDAOMethod()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("Going to call the method " + pjp.toShortString());
        Object output = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info(pjp.toShortString() + " execution time: " + elapsedTime + " milliseconds.");
        return output;
    }

    @Before("anyDbChangeMethod()")
    public void logTransaction(JoinPoint jp) {
        logger.info("Transaction operation start");
    }

}