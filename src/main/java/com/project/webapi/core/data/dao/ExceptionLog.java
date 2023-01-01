package com.project.webapi.core.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EXCEPTION_LOG")
@Getter
@Setter
public class ExceptionLog extends ExaminableDao {

    public String path;
    public String type;
    public String hostName;
    @Column(length = 3000)
    public String exception;
    @Column(length = 3000)
    public String stackTrace;
    public String ip;

    public ExceptionLog (String exception, String stackTrace, String path, String type, String hostName, String ip) {
        this.exception = setupExceptionDetail(exception);
        this.stackTrace = setupExceptionDetail(stackTrace);
        this.path = path;
        this.ip = ip;
        this.type = type;
        this.hostName = hostName;
    }

    private String setupExceptionDetail(String input) {
        if (input.length() > 3000)
            return input.substring(0, 2999);
        return input;
    }
}
