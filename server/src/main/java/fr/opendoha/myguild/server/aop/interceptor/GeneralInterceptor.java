package fr.opendoha.myguild.server.aop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Interceptor used for all classes
 */
@Aspect
@Component
public class GeneralInterceptor extends MotherInterceptor {

    final Logger logger = LoggerFactory.getLogger(GeneralInterceptor.class);

    /**
     * Log interceptor to log the method, the time to proceed and the arguments of
     * repositories methods
     *
     * @param joinPoint Event intercepted by the aop
     * @return Proceed of the event
     * @throws Throwable exception
     */
    @Around("execution(* fr.opendoha.myguild.server.*.*.*(..))" +
            "&& !execution(* org.springframework.data.repository.CrudRepository.*(..))" +
            "&& !execution(* org.springframework.data.jpa.repository.JpaRepository.*(..))")
    public Object logInterceptor(final ProceedingJoinPoint joinPoint) throws Throwable {

        return this.logExecutionTime(joinPoint, this.logger);
    }

}