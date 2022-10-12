package com.dimata.hrd.form.masterdata;

import com.dimata.hrd.entity.masterdata.kompetensi;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class Frmkompetensi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private kompetensi entkompetensi;
    public static final String FRM_NAME_KOMPETENSI = "FRM_NAME_KOMPETENSI";
    public static final int FRM_FIELD_ID_KOMPETENSI = 0;
    public static final int FRM_FIELD_KOMPETENSI = 1;

    public static String[] fieldNames = {
        "FRM_FIELD_ID_KOMPETENSI",
        "FRM_FIELD_KOMPETENSI"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING
    };

    public Frmkompetensi() {
    }

    public Frmkompetensi(kompetensi entkompetensi) {
        this.entkompetensi = entkompetensi;
    }

    public Frmkompetensi(HttpServletRequest request, kompetensi entkompetensi) {
        super(new Frmkompetensi(entkompetensi), request);
        this.entkompetensi = entkompetensi;
    }

    public String getFormName() {
        return FRM_NAME_KOMPETENSI;
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

    public kompetensi getEntityObject() {
        return entkompetensi;
    }

    public void requestEntityObject(kompetensi entkompetensi) {
        try {
            this.requestParam();
            entkompetensi.setIdKompetensi(getLong(FRM_FIELD_ID_KOMPETENSI));
            entkompetensi.setKompetensi(getString(FRM_FIELD_KOMPETENSI));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
