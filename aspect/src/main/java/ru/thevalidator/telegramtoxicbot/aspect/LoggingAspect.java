package ru.thevalidator.telegramtoxicbot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("ru.thevalidator.telegramtoxicbot.aspect.LoggingPointcut.inWebAndServiceLayer()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String message = "==> method  started";
        log(joinPoint, message);
    }

    @AfterReturning(pointcut = "ru.thevalidator.telegramtoxicbot.aspect.LoggingPointcut.inWebAndServiceLayer()",
            returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String message = "<== method finished";
        log(joinPoint, message);
    }

    private void log(JoinPoint joinPoint, String message) {
        Signature signature = joinPoint.getSignature();
        log.debug("{}: {} :: {}", message, signature.getDeclaringTypeName(), signature.getName());
    }

}
