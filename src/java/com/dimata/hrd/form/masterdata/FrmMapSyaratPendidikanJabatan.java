/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.form.masterdata;

import com.dimata.hrd.entity.masterdata.MapSyaratPendidikanJabatan;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author VegaNirmala
 */
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmMapSyaratPendidikanJabatan extends FRMHandler implements I_FRMInterface, I_FRMType {
  private MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan;
  public static final String FRM_NAME_MAPSYARATPENDIDIKANJABATAN = "FRM_NAME_MAPSYARATPENDIDIKANJABATAN";
  public static final int FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN = 0;
  public static final int FRM_FIELD_ID_JABATAN = 1;
  public static final int FRM_FIELD_PENDIDIKAN_MIN = 2;
  public static final int FRM_FIELD_PENDIDIKAN_REK = 3;


public static String[] fieldNames = {
    "FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN",
    "FRM_FIELD_ID_JABATAN",
    "FRM_FIELD_PENDIDIKAN_MIN",
    "FRM_FIELD_PENDIDIKAN_REK"
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_LONG,
    TYPE_STRING,
    TYPE_STRING
};

public FrmMapSyaratPendidikanJabatan() {
}

public FrmMapSyaratPendidikanJabatan(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
   this.entMapSyaratPendidikanJabatan = entMapSyaratPendidikanJabatan;
}

public FrmMapSyaratPendidikanJabatan(HttpServletRequest request, MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
   super(new FrmMapSyaratPendidikanJabatan(entMapSyaratPendidikanJabatan), request);
   this.entMapSyaratPendidikanJabatan = entMapSyaratPendidikanJabatan;
}

public String getFormName() {
   return FRM_NAME_MAPSYARATPENDIDIKANJABATAN;
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

public MapSyaratPendidikanJabatan getEntityObject() {
   return entMapSyaratPendidikanJabatan;
}

public void requestEntityObject(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
   try {
        this.requestParam();
        entMapSyaratPendidikanJabatan.setIdMapPendidikanJabatan(getLong(FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN));
        entMapSyaratPendidikanJabatan.setOID(getLong(FRM_FIELD_ID_JABATAN));
        entMapSyaratPendidikanJabatan.setPendidikanMin(getString(FRM_FIELD_PENDIDIKAN_MIN));
        entMapSyaratPendidikanJabatan.setPendidikanRek(getString(FRM_FIELD_PENDIDIKAN_REK));
   } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
   }
}

}
