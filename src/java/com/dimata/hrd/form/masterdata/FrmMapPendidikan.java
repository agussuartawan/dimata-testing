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
import com.dimata.hrd.entity.masterdata.MapPendidikan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmMapPendidikan extends FRMHandler implements I_FRMInterface, I_FRMType {
  private MapPendidikan entMapPendidikan;
  public static final String FRM_NAME_MAPPENDIDIKAN = "FRM_NAME_MAPPENDIDIKAN";
  public static final int FRM_FIELD_ID_MAP_PENDIDIKAN = 0;
  public static final int FRM_FIELD_ID_KARYAWAN = 1;
  public static final int FRM_FIELD_ID_PENDIDIKAN = 2;
  public static final int FRM_FIELD_TAHUN_MULAI = 3;
  public static final int FRM_FIELD_TAHUN_SELESAI = 4;

public static String[] fieldNames = {
    "FRM_FIELD_ID_MAP_PENDIDIKAN",
    "FRM_FIELD_ID_KARYAWAN",
    "FRM_FIELD_ID_PENDIDIKAN",
    "FRM_FIELD_TAHUN_MULAI",
    "FRM_FIELD_TAHUN_SELESAI"
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_LONG,
    TYPE_LONG,
    TYPE_INT,
    TYPE_INT
};

public FrmMapPendidikan() {
}

public FrmMapPendidikan(MapPendidikan entMapPendidikan) {
   this.entMapPendidikan = entMapPendidikan;
}

public FrmMapPendidikan(HttpServletRequest request, MapPendidikan entMapPendidikan) {
   super(new FrmMapPendidikan(entMapPendidikan), request);
   this.entMapPendidikan = entMapPendidikan;
}

public String getFormName() {
   return FRM_NAME_MAPPENDIDIKAN;
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

public MapPendidikan getEntityObject() {
   return entMapPendidikan;
}

public void requestEntityObject(MapPendidikan entMapPendidikan) {
   try {
        this.requestParam();
        entMapPendidikan.setOID(getLong(FRM_FIELD_ID_MAP_PENDIDIKAN));
        entMapPendidikan.setIdKaryawan(getLong(FRM_FIELD_ID_KARYAWAN));
        entMapPendidikan.setIdPendidikan(getLong(FRM_FIELD_ID_PENDIDIKAN));
        entMapPendidikan.setTahunMulai(getInt(FRM_FIELD_TAHUN_MULAI));
        entMapPendidikan.setTahunSelesai(getInt(FRM_FIELD_TAHUN_SELESAI));
   } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
   }
}

}