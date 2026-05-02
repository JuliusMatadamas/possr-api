package com.possr.utils;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.Converter;
public class TestConverter extends Converter<ILoggingEvent> {
    public String convert(ILoggingEvent event) { return ""; }
}
