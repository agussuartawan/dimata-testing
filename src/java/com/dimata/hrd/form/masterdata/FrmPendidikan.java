/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.form.masterdata;

/**
 *
 * @author VegaNirmala
 */
import com.dimata.hrd.entity.masterdata.Pendidikan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmPendidikan extends FRMHandler implements I_FRMInterface, I_FRMType {
  private Pendidikan entPendidikan;
  public static final String FRM_NAME_PENDIDIKAN = "FRM_NAME_PENDIDIKAN";
  public static final int FRM_FIELD_ID_PENDIDIKAN = 0;
  public static final int FRM_FIELD_PENDIDIKAN = 1;
  public static final int FRM_FIELD_LEVEL = 2;


public static String[] fieldNames = {
    "FRM_FIELD_ID_PENDIDIKAN",
    "FRM_FIELD_PENDIDIKAN",
    "FRM_FIELD_LEVEL"
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_STRING,
    TYPE_INT
};

public FrmPendidikan() {
}

public FrmPendidikan(Pendidikan entPendidikan) {
   this.entPendidikan = entPendidikan;
}

public FrmPendidikan(HttpServletRequest request, Pendidikan entPendidikan) {
   super(new FrmPendidikan(entPendidikan), request);
   this.entPendidikan = entPendidikan;
}

public String getFormName() {
   return FRM_NAME_PENDIDIKAN;
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

public Pendidikan getEntityObject() {
   return entPendidikan;
}

public void requestEntityObject(Pendidikan entPendidikan) {
   try {
        this.requestParam();
        entPendidikan.setOID(getLong(FRM_FIELD_ID_PENDIDIKAN));
        entPendidikan.setPendidikan(getString(FRM_FIELD_PENDIDIKAN));
        entPendidikan.setLevel(getInt(FRM_FIELD_LEVEL));
   } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
   }
}

}
