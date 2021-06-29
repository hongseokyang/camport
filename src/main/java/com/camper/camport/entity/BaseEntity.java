package com.camper.camport.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public class BaseEntity {
    private Date createdDate;       // 등록일
    private Date lastModifieddate;  // 수정일
}
