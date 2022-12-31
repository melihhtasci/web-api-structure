package com.project.news.core.data;

import java.time.LocalDate;
import java.util.Date;

public abstract class ExaminableDao extends BaseDao {

    public long createdBy;
    public LocalDate createdDate;
    public long updatedBy;
    public Date updatedDate;
    public boolean isActive;

}
