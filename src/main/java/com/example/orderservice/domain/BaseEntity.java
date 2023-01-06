package com.example.orderservice.domain;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals (id, that.id) && Objects.equals (createdDate, that.createdDate) && Objects.equals (lastModifiedDate, that.lastModifiedDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash (id, createdDate, lastModifiedDate);
    }
}