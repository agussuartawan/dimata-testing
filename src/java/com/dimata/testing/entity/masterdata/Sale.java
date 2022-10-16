package com.dimata.testing.entity.masterdata;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author keys
 */
public class Sale extends Entity {

    private long id = 0;
    private String code = "";
    private Date date = null;
    private float grand_total = 0;
    private Date created_at = null;
    private Date updated_at = null;

    private long customer_id = 0;
    private String customer_name = "";
    private String customer_phone = "";
    private String customer_address = "";

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param name the name to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the alamat
     */
    public float getGrandTotal() {
        return grand_total;
    }

    /**
     * @param grand_total the grand_total to set
     */
    public void setGrandTotal(float grand_total) {
        this.grand_total = grand_total;
    }

    /**
     * @return the alamat
     */
    public Date getCreatedAt() {
        return created_at;
    }

    /**
     * @param created_at the created_at to set
     */
    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * @return the alamat
     */
    public Date getUpdatedAt() {
        return updated_at;
    }

    /**
     * @param updated_at the updated_at to set
     */
    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    /**
     * @return the name
     */
    public long getCustomerId() {
        return customer_id;
    }

    /**
     * @param name the name to set
     */
    public void setCustomerId(long customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the namaKaryawan
     */
    public String getCustomerName() {
        return customer_name;
    }

    /**
     * @param customer_name the customer_name to set
     */
    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * @return the namaKaryawan
     */
    public String getCustomerPhone() {
        return customer_phone;
    }

    /**
     * @param customer_phone the customer_phone to set
     */
    public void setCustomerPhone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    /**
     * @return the namaKaryawan
     */
    public String getCustomerAddress() {
        return customer_address;
    }

    /**
     * @param customer_address the customer_address to set
     */
    public void setCustomerAddress(String customer_address) {
        this.customer_address = customer_address;
    }
}
