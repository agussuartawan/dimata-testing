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
import com.dimata.testing.entity.masterdata.Product;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmProduct extends FRMHandler implements I_FRMInterface, I_FRMType {
   private Product entProduct;
   public static final String FRM_NAME_PRODUCT = "FRM_NAME_PRODUCT";
   public static final int FRM_FIELD_ID = 0;
   public static final int FRM_FIELD_CODE = 1;
   public static final int FRM_FIELD_NAME = 2;
   public static final int FRM_FIELD_STOCK = 3;
   public static final int FRM_FIELD_PRICE = 4;
   public static final int FRM_FIELD_CREATED_AT = 5;
   public static final int FRM_FIELD_UPDATED_AT = 6;


public static String[] fieldNames = {
      "id",
      "code",
      "name",
      "stock",
      "price",
      "created_at",
      "updated_at",
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_INT,
    TYPE_STRING,
    TYPE_DATE,
    TYPE_DATE,
};

public FrmProduct() {
}

public FrmProduct(Product entProduct) {
   this.entProduct = entProduct;
}

public FrmProduct(HttpServletRequest request, Product entProduct) {
   super(new FrmProduct(entProduct), request);
   this.entProduct = entProduct;
}

public String getFormName() {
   return FRM_NAME_PRODUCT;
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

public Product getEntityObject() {
   return entProduct;
}

public void requestEntityObject(Product entProduct) {
   try {
        this.requestParam();
        entProduct.setOID(getLong(FRM_FIELD_ID));
        entProduct.setCode(getString(FRM_FIELD_CODE));
        entProduct.setName(getString(FRM_FIELD_NAME));
        entProduct.setStock(getInt(FRM_FIELD_STOCK));
        entProduct.setPrice(getFloat(FRM_FIELD_PRICE));
        entProduct.setCreatedAt(getDate(FRM_FIELD_CREATED_AT));
        entProduct.setUpdatedAt(getDate(FRM_FIELD_UPDATED_AT));
   } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
   }
}

}