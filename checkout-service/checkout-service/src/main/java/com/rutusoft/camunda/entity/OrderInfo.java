package com.rutusoft.camunda.entity;


import jakarta.persistence.*;

@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String traceId;
    @Column
    private Long custId;
    @Column
    private Double totalAmount;
    @Column
    private String status;

    public OrderInfo() {
    }

    public OrderInfo(Long id, String traceId, Long custId, Double totalAmount, String status) {
        this.id = id;
        this.traceId = traceId;
        this.custId = custId;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}