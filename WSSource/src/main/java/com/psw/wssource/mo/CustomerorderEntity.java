package com.psw.wssource.mo;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity // Dice a Spring che è una Entity (cioè che un Model Object)
@Table(name = "customerorder", schema = "ordersdb", catalog = "") // Dice a quale tabella è collegato, di quale schema
public class CustomerorderEntity {
    /** Ci mette un campo per ogni colonna della tabella */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // Dice che questa è la chiave della tabella
    @Column(name = "orderId", nullable = false) // Metto il campo del database al quale corrisponde questo attributo
    private int orderId;
    @Basic
    @Column(name = "userId", nullable = false)
    private int userId;
    @Basic
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;
    @Basic
    @Column(name = "deliveryAddress", nullable = false, length = 80)
    private String deliveryAddress;
    @Basic
    @Column(name = "deliveryCity", nullable = false, length = 45)
    private String deliveryCity;
    @Basic
    @Column(name = "deliveryZip", nullable = false, length = 5)
    private String deliveryZip;
    @Basic
    @Column(name = "exportTimestamp", nullable = true)
    private Timestamp exportTimestamp;

    /** Collection di CustomerorderrowEntity che va a mappare la relazione 1:N
     *  Questa Collection è stata messa perchè l'ho spuntata in fase di creazione del mapping
     * */
    @OneToMany(mappedBy = "customerorderByOrderId") // Mapping 1:N ==> dico che questa è una relazione One to Many, mappata da uno specifico campo ("customerorderByOrderId")
    private Collection<CustomerorderrowEntity> customerorderrowsByOrderId; // Ci mette una intera Collection di CustomerorderrowEntity

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryZip() {
        return deliveryZip;
    }

    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public Timestamp getExportTimestamp() {
        return exportTimestamp;
    }

    public void setExportTimestamp(Timestamp exportTimestamp) {
        this.exportTimestamp = exportTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerorderEntity that = (CustomerorderEntity) o;
        return orderId == that.orderId && userId == that.userId && Objects.equals(orderDate, that.orderDate) && Objects.equals(deliveryAddress, that.deliveryAddress) && Objects.equals(deliveryCity, that.deliveryCity) && Objects.equals(deliveryZip, that.deliveryZip) && Objects.equals(exportTimestamp, that.exportTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, orderDate, deliveryAddress, deliveryCity, deliveryZip, exportTimestamp);
    }

    public Collection<CustomerorderrowEntity> getCustomerorderrowsByOrderId() {
        return customerorderrowsByOrderId;
    }

    public void setCustomerorderrowsByOrderId(Collection<CustomerorderrowEntity> customerorderrowsByOrderId) {
        this.customerorderrowsByOrderId = customerorderrowsByOrderId;
    }
}
