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
public class SaleDetail extends Entity {

        private long id = 0;
        private int qty = 0;
        private float price = 0;
        private float subtotal = 0;

        private long product_id = 0;
        private long sale_id = 0;
        private String product_name = "";
        private String product_code = "";

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
        public int getQty() {
                return qty;
        }

        /**
         * @param code the code to set
         */
        public void setQty(int qty) {
                this.qty = qty;
        }

        /**
         * @return the name
         */
        public float getPrice() {
                return price;
        }

        /**
         * @param name the name to set
         */
        public void setPrice(float price) {
                this.price = price;
        }

        /**
         * @return the alamat
         */
        public float getSubtotal() {
                return subtotal;
        }

        /**
         * @param subtotal the subtotal to set
         */
        public void setSubtotal(float subtotal) {
                this.subtotal = subtotal;
        }

        /**
         * @return the name
         */
        public long getProductId() {
                return product_id;
        }

        /**
         * @param name the name to set
         */
        public void setProductId(long product_id) {
                this.product_id = product_id;
        }

        /**
         * @return the namaKaryawan
         */
        public long getSaleId() {
                return sale_id;
        }

        /**
         * @param sale_id the customer_name to set
         */
        public void setSaleId(long sale_id) {
                this.sale_id = sale_id;
        }

        /**
         * @return the namaKaryawan
         */
        public String getProductCode() {
                return product_code;
        }

        /**
         * @param product_code the product_code to set
         */
        public void setProductCode(String product_code) {
                this.product_code = product_code;
        }

        /**
         * @return the namaKaryawan
         */
        public String getProductName() {
                return product_name;
        }

        /**
         * @param product_name the product_name to set
         */
        public void setProductName(String product_name) {
                this.product_name = product_name;
        }
}
