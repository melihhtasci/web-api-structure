package com.project.webapi.core.middlewares;

import com.project.webapi.core.data.ApiErrorResponse;
import com.project.webapi.core.data.dao.ExceptionLog;
import com.project.webapi.core.service.common.ExceptionLogService;
import com.project.webapi.core.service.es.ExceptionLogElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    private ExceptionLogService exceptionLogService;
    private ExceptionLogElasticService exceptionLogElasticService;

    @Autowired
    public ExceptionHandlerControllerAdvice(ExceptionLogService exceptionLogService,
                                            ExceptionLogElasticService exceptionLogElasticService) {
        this.exceptionLogService = exceptionLogService;
        this.exceptionLogElasticService = exceptionLogElasticService;
    }

    @ExceptionHandler
    public @ResponseBody ApiErrorResponse handleError(final Exception exception, final HttpServletRequest request) {

        ExceptionLog exceptionLog = prepareExceptionLog(exception, request);
        // feed log to some place
        exceptionLogService.add(exceptionLog);
        exceptionLogElasticService.save(exceptionLog);
        return new ApiErrorResponse("Error: " + exception.getMessage());
    }

    // Remove this method or usage as above if you don't want to write to db.
    private ExceptionLog prepareExceptionLog(Exception exception, HttpServletRequest request) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        String stackTrace = stringWriter.toString();

        ExceptionLog exceptionLog = new ExceptionLog(exception.getMessage(), stackTrace,
                request.getRequestURI(), request.getMethod(), "", "");
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ipAddress = inetAddress.getHostAddress();
            String hostname = inetAddress.getHostName();
            exceptionLog.setIp(ipAddress);
            exceptionLog.setHostName(hostname);
        } catch (Exception ex) {
            System.out.println("An error has occurred while reading ip and hostname");
        }
        return exceptionLog;
    }

}
