package com.project.webapi.core.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "FLOW_LOG")
@Getter
@Setter
public class FlowLog extends ExaminableDao implements Serializable {

    public String request;
    public String response;
    public String type;

    public FlowLog(String request, String response, String type) {
        this.request = request;
        this.response = response;
        this.type = type;
    }
}
