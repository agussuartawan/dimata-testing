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
import com.dimata.testing.entity.masterdata.Sale;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class FrmSale extends FRMHandler implements I_FRMInterface, I_FRMType {
    private Sale entSale;
    public static final String FRM_NAME_SALE = "FRM_NAME_SALE";
    public static final int FRM_FIELD_ID = 0;
    public static final int FRM_FIELD_CUSTOMER_ID = 1;
    public static final int FRM_FIELD_CODE = 2;
    public static final int FRM_FIELD_DATE = 3;
    public static final int FRM_FIELD_GRAND_TOTAL = 4;

    public static String[] fieldNames = {
            "id",
            "customer_id",
            "code",
            "inv_date",
            "grand_total",
    };

    public static int[] fieldTypes = {
            TYPE_LONG,
            TYPE_LONG,
            TYPE_STRING,
            TYPE_STRING,
            TYPE_FLOAT,

    };

    public FrmSale() {
    }

    public FrmSale(Sale entSale) {
        this.entSale = entSale;
    }

    public FrmSale(HttpServletRequest request, Sale entSale) {
        super(new FrmSale(entSale), request);
        this.entSale = entSale;
    }

    public String getFormName() {
        return FRM_NAME_SALE;
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

    public Sale getEntityObject() {
        return entSale;
    }

    public void requestEntityObject(Sale entSale) {
        try {
            this.requestParam();
            entSale.setOID(getLong(FRM_FIELD_ID));
            entSale.setCode(getString(FRM_FIELD_CODE));
            entSale.setCustomerId(getLong(FRM_FIELD_CUSTOMER_ID));
            entSale.setInvDate(getString(FRM_FIELD_DATE));
            entSale.setGrandTotal(getFloat(FRM_FIELD_GRAND_TOTAL));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}