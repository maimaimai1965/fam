package ua.mai.fam.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging execution of repositories, services and controllers.
 */
@Aspect
@Component
public class LoggingAspect //implements ApplicationContextAware
{

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    //--------------------------------------------- Repository -----------------------------------------------------------
    /**
     * Pointcut для логирования репозиториев.<br>
     * Определяет все pubic методы в классах содержащих в имени "Repository" из пакета ua.mai.art.repository (и его
     * подпакетов).
     */
    @Pointcut("execution(public * ua.mai.fam.repository.*.*Repository*.*(..))")
    public void stepLogRepositoryPointcut() {}

    /**
     * Совет для логирования методов в репозиториях.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("stepLogRepositoryPointcut()")
    public Object stepLogRepositoryAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return stepLogAround(joinPoint, "Repository");
    }


    //--------------------------------------------- Service --------------------------------------------------------------
    /**
     * Pointcut для логирования сервисов.<br>
     * Определяет pubic методы в классах содержащих в имени "Service" из пакета <code>ua.mai.art.service</code> (и его
     * подпакетов) с аннотацией @StepLogServiceAnnotation.
     */
    @Pointcut("execution(public * ua.mai.fam.service.*.*.*Service*.*(..)) && @annotation(StepLogServiceAnnotation)")
    public void stepLogServicePointcut() {}

    /**
     * Совет для логирования методов в сервисах.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("stepLogServicePointcut()")
    public Object stepLogJobAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return stepLogAround(joinPoint, "Service");
    }

    //--------------------------------------------- Controller -----------------------------------------------------------
    /**
     * Pointcut для логирования контроллеров.<br>
     * Определяет все pubic методы в классах содержащих в имени "Controller" из пакета ua.mai.art.controller.rest (и его
     * подпакетов).
     */
    @Pointcut("execution(public * ua.mai.fam.controller.*.*.*Controller*.*(..))")
    public void stepLogControllerPointcut() {}

    /**
     * Совет для логирования методов в контроллерах.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("stepLogControllerPointcut()")
    public Object stepLogControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return stepLogAround(joinPoint, "Controller");
    }


    public Object stepLogAround(ProceedingJoinPoint joinPoint, String from) throws Throwable {
        String target = joinPoint.getTarget().toString();
        target = target.substring(target.lastIndexOf('.') + 1);
        target = target.substring(0, target.lastIndexOf('@')) + "." + joinPoint.getSignature().getName() + "()";

//    StepLog stepLog = null;
//    switch (logMarkerName) {
//      case DB:      //Используются установленные ранее Id.
//                    stepLog = new StepLogDb(logger, target);
//                    break;
//      case JOB:     //jobId получаем из бина в LogConfig.
//                    stepLog = new StepLogJob(logger, jobIdSupplier.get(), target);
//                    break;
//      case REQUEST: //requstId получаем из бина в LogConfig.
//                    stepLog = new StepLogRequest(logger, requestIdSupplier.get(), target);
//                    break;
//      default:      stepLog = new StepLogDetail(logger, detailIdSupplier.get(), target);
//    }
//    stepLog.startStep("...");
        try {
            LOG.debug(from + " start: " + target);
            Object result = joinPoint.proceed();
            LOG.debug(from + " finish OK: " + target);
            return result;
        }
        catch (IllegalArgumentException e) {
            LOG.error(from + " finish Error: Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
        catch (Throwable e) {
            LOG.error(from + " finish Error: {}", e.getMessage());
            throw e;
        }
    }

}