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
import com.dimata.testing.entity.masterdata.Customer;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmCustomer extends FRMHandler implements I_FRMInterface, I_FRMType {
    private Customer entCustomer;
    public static final String FRM_NAME_CUSTOMER = "FRM_NAME_CUSTOMER";
    public static final int FRM_FIELD_ID = 0;
    public static final int FRM_FIELD_NAME = 1;
    public static final int FRM_FIELD_PHONE = 2;
    public static final int FRM_FIELD_ADDRESS = 3;

    public static String[] fieldNames = {
            "id",
            "name",
            "phone",
            "address",
    };

    public static int[] fieldTypes = {
            TYPE_LONG,
            TYPE_STRING,
            TYPE_STRING,
            TYPE_STRING,
    };

    public FrmCustomer() {
    }

    public FrmCustomer(Customer entCustomer) {
        this.entCustomer = entCustomer;
    }

    public FrmCustomer(HttpServletRequest request, Customer entCustomer) {
        super(new FrmCustomer(entCustomer), request);
        this.entCustomer = entCustomer;
    }

    public String getFormName() {
        return FRM_NAME_CUSTOMER;
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

    public Customer getEntityObject() {
        return entCustomer;
    }

    public void requestEntityObject(Customer entCustomer) {
        try {
            this.requestParam();
            entCustomer.setOID(getLong(FRM_FIELD_ID));
            entCustomer.setName(getString(FRM_FIELD_NAME));
            entCustomer.setPhone(getString(FRM_FIELD_PHONE));
            entCustomer.setAddress(getString(FRM_FIELD_ADDRESS));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}