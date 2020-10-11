package fr.opendoha.myguild.server.aop.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Interceptor used for services
 */
@Aspect
@Component
public class ServiceInterceptor extends MotherInterceptor {

    protected final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    /**
     * Interceptor used to check the input data validity
     *
     * @param joinPoint Event intercepted by the aop
     */
    @Before("execution(* fr.opendoha.myguild.server.service.*.*(..))")
    public void validationInterceptor(final JoinPoint joinPoint) {

        this.validationInputData(joinPoint, this.logger);

    }

}