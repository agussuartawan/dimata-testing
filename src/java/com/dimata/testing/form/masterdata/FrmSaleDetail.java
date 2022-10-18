/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.testing.form.masterdata;

/**
 *
 * @author keys
 */
import com.dimata.testing.entity.masterdata.SaleDetail;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmSaleDetail extends FRMHandler implements I_FRMInterface, I_FRMType {
        private SaleDetail entSaleDetail;
        public static final String FRM_NAME_SALE_DETAIL = "FRM_NAME_SALE_DETAIL";
        public static final int FRM_FIELD_ID = 0;
        public static final int FRM_FIELD_SALE_ID = 1;
        public static final int FRM_FIELD_PRODUCT_ID = 2;
        public static final int FRM_FIELD_QTY = 3;
        public static final int FRM_FIELD_PRICE = 4;
        public static final int FRM_FIELD_SUBTOTAL = 5;

        public static String[] fieldNames = {
                        "id",
                        "sale_id",
                        "product_id",
                        "qty",
                        "price",
                        "subtotal",
        };

        public static int[] fieldTypes = {
                        TYPE_LONG,
                        TYPE_LONG,
                        TYPE_LONG,
                        TYPE_INT,
                        TYPE_FLOAT,
                        TYPE_FLOAT,
        };

        public FrmSaleDetail() {
        }

        public FrmSaleDetail(SaleDetail entSaleDetail) {
                this.entSaleDetail = entSaleDetail;
        }

        public FrmSaleDetail(HttpServletRequest request, SaleDetail entSaleDetail) {
                super(new FrmSaleDetail(entSaleDetail), request);
                this.entSaleDetail = entSaleDetail;
        }

        public String getFormName() {
                return FRM_NAME_SALE_DETAIL;
        }

        public int[] getFieldTypes() {
                return fieldTypes;
        }

        public String[] getFieldNames() {
                return fieldNames;
        }

        public int getFieldSize() {
                return fieldNames.length;
        }

        public SaleDetail getEntityObject() {
                return entSaleDetail;
        }

        public void requestEntityObject(SaleDetail entSaleDetail) {
                try {
                        this.requestParam();
                        entSaleDetail.setOID(getLong(FRM_FIELD_ID));
                        entSaleDetail.setSaleId(getLong(FRM_FIELD_SALE_ID));
                        entSaleDetail.setProductId(getLong(FRM_FIELD_PRODUCT_ID));
                        entSaleDetail.setQty(getInt(FRM_FIELD_QTY));
                        entSaleDetail.setPrice(getFloat(FRM_FIELD_PRICE));
                        entSaleDetail.setSubtotal(getFloat(FRM_FIELD_SUBTOTAL));
                } catch (Exception e) {
                        System.out.println("Error on requestEntityObject : " + e.toString());
                }
        }

}