/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.form.masterdata;

/**
 *
 * @author keys
 */
import com.dimata.hrd.entity.masterdata.AppUser;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmAppUser extends FRMHandler implements I_FRMInterface, I_FRMType {
  private AppUser entAppUser;
  public static final String FRM_NAME_APPUSER = "FRM_NAME_APPUSER";
  public static final int FRM_FIELD_USER_ID = 0;
  public static final int FRM_FIELD_LOGIN_ID = 1;
  public static final int FRM_FIELD_PASSWORD = 2;
  public static final int FRM_FIELD_FULL_NAME = 3;
  public static final int FRM_FIELD_EMAIL = 4;
  public static final int FRM_FIELD_DESCRIPTION = 5;
  public static final int FRM_FIELD_REG_DATE = 6;
  public static final int FRM_FIELD_UPDATE_DATE = 7;
  public static final int FRM_FIELD_USER_STATUS = 8;
  public static final int FRM_FIELD_LAST_LOGIN_DATE = 9;
  public static final int FRM_FIELD_LAST_LOGIN_IP = 10;
  public static final int FRM_FIELD_ID_KARYAWAN = 11;


public static String[] fieldNames = {
    "FRM_FIELD_USER_ID",
    "FRM_FIELD_LOGIN_ID",
    "FRM_FIELD_PASSWORD",
    "FRM_FIELD_FULL_NAME",
    "FRM_FIELD_EMAIL",
    "FRM_FIELD_DESCRIPTION",
    "FRM_FIELD_REG_DATE",
    "FRM_FIELD_UPDATE_DATE",
    "FRM_FIELD_USER_STATUS",
    "FRM_FIELD_LAST_LOGIN_DATE",
    "FRM_FIELD_LAST_LOGIN_IP",
    "FRM_FIELD_ID_KARYAWAN"
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_DATE,
    TYPE_DATE,
    TYPE_INT,
    TYPE_DATE,
    TYPE_STRING,
    TYPE_LONG
};

public FrmAppUser() {
}

public FrmAppUser(AppUser entAppUser) {
   this.entAppUser = entAppUser;
}

public FrmAppUser(HttpServletRequest request, AppUser entAppUser) {
   super(new FrmAppUser(entAppUser), request);
   this.entAppUser = entAppUser;
}

public String getFormName() {
   return FRM_NAME_APPUSER;
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

public AppUser getEntityObject() {
   return entAppUser;
}

public void requestEntityObject(AppUser entAppuser) {
   try {
        this.requestParam();
        entAppuser.setOID(getLong(FRM_FIELD_USER_ID));
        entAppuser.setLoginId(getString(FRM_FIELD_LOGIN_ID));
        entAppuser.setPassword(getString(FRM_FIELD_PASSWORD));
        entAppuser.setFullName(getString(FRM_FIELD_FULL_NAME));
        entAppuser.setEmail(getString(FRM_FIELD_EMAIL));
        entAppuser.setDescription(getString(FRM_FIELD_DESCRIPTION));
        entAppuser.setRegDate(getDate(FRM_FIELD_REG_DATE));
        entAppuser.setUpdateDate(getDate(FRM_FIELD_UPDATE_DATE));
        entAppuser.setUserStatus(getInt(FRM_FIELD_USER_STATUS));
        entAppuser.setLastLoginDate(getDate(FRM_FIELD_LAST_LOGIN_DATE));
        entAppuser.setLastLoginIp(getString(FRM_FIELD_LAST_LOGIN_IP));
        entAppuser.setIdKaryawan(getLong(FRM_FIELD_ID_KARYAWAN));
   } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
   }
}

}