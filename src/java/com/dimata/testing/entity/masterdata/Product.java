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
public class Product  extends Entity {
    private long id = 0;
    private String code = "";
    private String name = "";
    private float price = 0;
    private int stock = 0;
    private Date created_at = null;
    private Date updated_at = null;
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
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the name
     */
    public long getStock() {
        return stock;
    }

    /**
     * @param name the name to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the alamat
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
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
}
