package com.bluebik.shorturl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Table(name = "User")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @Column(name = "created_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7:00")
    private Date createDatetime;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "modified_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7:00")
    private Date modifiedDatetime;

    @Column(name = "modified_by")
    private Integer modifiedBy;
}
