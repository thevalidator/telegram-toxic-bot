package ru.thevalidator.telegramtoxicbot.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcut {

    @Pointcut("within(ru.thevalidator.telegramtoxicbot.api.controller..*)")
    public void inWebLayer() {
    }

    @Pointcut("within(ru.thevalidator.telegramtoxicbot.*.service..*)")
    public void inServiceLayer() {
    }

    @Pointcut("inWebLayer() || inServiceLayer()")
    public void inWebAndServiceLayer() {
    }

}
