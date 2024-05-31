package de.dlh.lhind.warehouse.shared.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Around("execution(* de.dlh.lhind.warehouse.item.controller..*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LocalDateTime timestamp = LocalDateTime.now();
        String httpMethod = request.getMethod();
        String path = request.getRequestURI();
        String requestParams = objectMapper.writeValueAsString(request.getParameterMap());
        String requestBody = Arrays.toString(joinPoint.getArgs());
        String ipAddress = request.getRemoteAddr();

        logger.info("Request: timestamp={}, httpMethod={}, path={}, requestParams={}, requestBody={}, ipAddress={}", timestamp, httpMethod, path, requestParams, requestBody, ipAddress);

        Object result = joinPoint.proceed();

        if (result instanceof ResponseEntity<?> responseEntity) {
            int httpStatus = responseEntity.getStatusCodeValue();
            Object responseBody = responseEntity.getBody();
            logger.info("Response: timestamp={}, httpStatus={}, responseBody={}, ipAddress={}", timestamp, httpStatus, objectMapper.writeValueAsString(responseBody), ipAddress);
        }

        return result;
    }
}
