package com.dov.sbapp.rest.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table
public class TrackingRequest {

    @Id
    @GeneratedValue
    private Long reqTrackingId;

    @Column
    private String originCountryId;

    @Column
    private String destinationCountryId;

    @Column
    private BigDecimal createdAt;

    @Column
    private Timestamp createdId;

    @Column
    private String customerId;

    @Column
    private String customerName;

    @Column
    private String customerSlug;

    public Long getReqTrackingId() {
        return reqTrackingId;
    }

    public void setReqTrackingId(Long reqTrackingId) {
        this.reqTrackingId = reqTrackingId;
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public BigDecimal getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(BigDecimal createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Timestamp createdId) {
        this.createdId = createdId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSlug() {
        return customerSlug;
    }

    public void setCustomerSlug(String customerSlug) {
        this.customerSlug = customerSlug;
    }
}
