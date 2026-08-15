package ru.thevalidator.telegramtoxicbot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeLogAspect {

    @Around("@annotation(timeLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, TimeLog timeLog) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            long durationMs = System.currentTimeMillis() - startTime;
            log.debug("Method '{}' of class '{}' execution time: {} ms",
                    joinPoint.getSignature().getName(),
                    joinPoint.getTarget().getClass().getSimpleName(),
                    durationMs
            );
        }
        return result;
    }

}
