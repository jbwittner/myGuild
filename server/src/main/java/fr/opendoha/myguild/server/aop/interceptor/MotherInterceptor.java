package fr.opendoha.myguild.server.aop.interceptor;

import fr.opendoha.myguild.server.exception.ValidationDataException;
import fr.opendoha.myguild.server.validation.InputServiceValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Mother class for the Interceptor
 */
public class MotherInterceptor {

    /**
     * General execution logger time
     *
     * @param joinPoint Event intercepted by the aop
     * @param logger    Logger used to log information's
     * @return Proceed of the event
     * @throws Throwable exception
     */
    protected Object logExecutionTime(final ProceedingJoinPoint joinPoint, final Logger logger) throws Throwable {
        final long start = System.currentTimeMillis();
        final List<Object> list = Arrays.asList(joinPoint.getArgs());
        final Iterator<Object> iterator = list.iterator();

        String method = joinPoint.getSignature().getDeclaringTypeName();
        method += "." + joinPoint.getSignature().getName();

        logger.info("ENTERING :: " + method);

        Object object;

        while (iterator.hasNext() && logger.isDebugEnabled()) {
            object = iterator.next();

            String message;

            if (null == object) {
                message = "Args list :: " + method + " :: null";
            } else {
                message = "Args list :: " + method + " :: " + object.toString();
            }

            logger.debug(message);

        }

        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        logger.info("EXITING :: " + method + " executed in " + executionTime + "ms");

        return proceed;
    }

    /**
     * Method used to check the validation of input data
     *
     * @param joinPoint Event intercepted by the aop
     * @param logger    Logger used to log information's
     * @throws ValidationDataException
     */
    protected void validationInputData(final JoinPoint joinPoint, final Logger logger) throws ValidationDataException {

        final List<Object> list = Arrays.asList(joinPoint.getArgs());
        final Iterator<Object> iterator = list.iterator();

        String method = joinPoint.getSignature().getDeclaringTypeName();
        method += "." + joinPoint.getSignature().getName();

        Object object;

        final InputServiceValidator<Object> validator = new InputServiceValidator<>();

        logger.info("INPUT VALIDATION :: " + method);

        while (iterator.hasNext()) {
            object = iterator.next();
            logger.debug("VALIDATION OF :: " + object.toString());
            validator.validate(object);
        }

    }

}