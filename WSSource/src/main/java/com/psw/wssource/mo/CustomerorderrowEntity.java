package com.psw.wssource.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customerorderrow", schema = "ordersdb", catalog = "")
public class CustomerorderrowEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderRowId", nullable = false)
    private int orderRowId;
    @Basic
    @Column(name = "productId", nullable = false)
    private int productId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private double price;

    @JsonIgnore // TODO: per risolvere il problema della relazione circolare
    @ManyToOne // Relazione Many To One,
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false) // con la colonna con cui si fa il Join (verso la tabella che fa da padre a questa entità)
    private CustomerorderEntity customerorderByOrderId; // con la possibilità di recuperare l'order a cui l'orderrow è collegata

    public int getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(int orderRowId) {
        this.orderRowId = orderRowId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerorderrowEntity that = (CustomerorderrowEntity) o;
        return orderRowId == that.orderRowId && productId == that.productId && quantity == that.quantity && Double.compare(price, that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderRowId, productId, quantity, price);
    }

    public CustomerorderEntity getCustomerorderByOrderId() {
        return customerorderByOrderId;
    }

    public void setCustomerorderByOrderId(CustomerorderEntity customerorderByOrderId) {
        this.customerorderByOrderId = customerorderByOrderId;
    }
}
