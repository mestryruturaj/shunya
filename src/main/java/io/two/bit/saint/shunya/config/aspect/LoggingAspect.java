package io.two.bit.saint.shunya.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    /**
     * Around advice to wrap service methods.
     * Pointcut: execution(* [package]..*[class].*[method](..))
     */
    @Around("execution(* io.two.bit.saint.shunya..*(..)) " +
            "&& !execution(* io.two.bit.saint.shunya.config..*(..))")
    public Object logServiceExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch();

        // 1. Log Entry with Arguments
        log.info(">>>> [ENTRY] {}.{} | Args: {}", className, methodName, Arrays.toString(joinPoint.getArgs()));

        stopWatch.start();

        try {
            // 2. Execute the actual business method
            Object result = joinPoint.proceed();

            stopWatch.stop();

            // 3. Log Exit with Duration
            log.info("<<<< [EXIT] {}.{} | Duration: {}ms", className, methodName, stopWatch.getTotalTimeMillis());

            return result;

        } catch (Throwable throwable) {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }

            // 4. Log failure and the duration until it crashed
            log.error("!!!! [FAIL] {}.{} | Error: {} | Duration: {}ms",
                    className, methodName, throwable.getMessage(), stopWatch.getTotalTimeMillis());

            // Re-throw so your ExceptionHandlers can catch it
            throw throwable;
        }
    }
}

