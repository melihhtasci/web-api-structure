package com.project.webapi.core.data.dao;

import com.project.webapi.core.data.ExaminableDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "FLOW_LOG")
@Getter
@Setter
@Document(indexName = "flow_log")
public class FlowLog extends ExaminableDao implements Serializable {

    @Field(type = FieldType.Text)
    public String request;
    @Column(length = 100000) @Field(type = FieldType.Text)
    public String response;
    public String type;

    public FlowLog(String request, String response, String type) {
        this.request = request;
        this.response = response;
        this.type = type;
    }
}
