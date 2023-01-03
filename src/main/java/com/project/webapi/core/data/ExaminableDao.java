package com.project.webapi.core.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.User;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class ExaminableDao extends BaseDao {

    public long createdBy;
    public LocalDateTime createdDate;
    public long updatedBy;
    public LocalDateTime updatedDate;
    public boolean isActive;

}
