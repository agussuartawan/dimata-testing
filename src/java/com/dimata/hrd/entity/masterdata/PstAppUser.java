/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.entity.masterdata;

/**
 *
 * @author keys
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import java.util.Vector;
import org.json.JSONObject;

public class PstAppUser extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

   public static final String TBL_APPUSER = "app_user";
   public static final int FLD_USER_ID = 0;
   public static final int FLD_LOGIN_ID = 1;
   public static final int FLD_PASSWORD = 2;
   public static final int FLD_FULL_NAME = 3;
   public static final int FLD_EMAIL = 4;
   public static final int FLD_DESCRIPTION = 5;
   public static final int FLD_REG_DATE = 6;
   public static final int FLD_UPDATE_DATE = 7;
   public static final int FLD_USER_STATUS = 8;
   public static final int FLD_LAST_LOGIN_DATE = 9;
   public static final int FLD_LAST_LOGIN_IP = 10;
   public static final int FLD_ID_KARYAWAN = 11;

   public static String[] fieldNames = {
      "USER_ID",
      "LOGIN_ID",
      "PASSWORD",
      "FULL_NAME",
      "EMAIL",
      "DESCRIPTION",
      "REG_DATE",
      "UPDATE_DATE",
      "USER_STATUS",
      "LAST_LOGIN_DATE",
      "LAST_LOGIN_IP",
      "EMPLOYEE_ID"
   };

   public static int[] fieldTypes = {
      TYPE_LONG+TYPE_PK+TYPE_ID,
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

   public PstAppUser() {
   }

   public PstAppUser(int i) throws DBException {
      super(new PstAppUser());
   }

   public PstAppUser(String sOid) throws DBException {
      super(new PstAppUser(0));
      if(!locate(sOid))
         throw new DBException(this, DBException.RECORD_NOT_FOUND);
      else
         return;
   }

   public PstAppUser(long lOid) throws DBException {
      super(new PstAppUser(0));
      String sOid = "0";
      try {
         sOid = String.valueOf(lOid);
      }catch(Exception e) {
         throw new DBException(this, DBException.RECORD_NOT_FOUND);
      }
      if(!locate(sOid))
         throw new DBException(this, DBException.RECORD_NOT_FOUND);
      else
         return;
   }

   public int getFieldSize() {
      return fieldNames.length;
   }

   public String getTableName() {
      return TBL_APPUSER;
   }

   public String[] getFieldNames() {
      return fieldNames;
   }

   public int[] getFieldTypes() {
      return fieldTypes;
   }

   public String getPersistentName() {
      return new PstAppUser().getClass().getName();
   }

   public static AppUser fetchExc(long oid) throws DBException {
      try {
         AppUser entAppuser = new AppUser();
         PstAppUser pstAppUser = new PstAppUser(oid);
          entAppuser.setOID(oid);
          entAppuser.setLoginId(pstAppUser.getString(FLD_LOGIN_ID));
          entAppuser.setPassword(pstAppUser.getString(FLD_PASSWORD));
          entAppuser.setFullName(pstAppUser.getString(FLD_FULL_NAME));
          entAppuser.setEmail(pstAppUser.getString(FLD_EMAIL));
          entAppuser.setDescription(pstAppUser.getString(FLD_DESCRIPTION));
          entAppuser.setRegDate(pstAppUser.getDate(FLD_REG_DATE));
          entAppuser.setUpdateDate(pstAppUser.getDate(FLD_UPDATE_DATE));
          entAppuser.setUserStatus(pstAppUser.getInt(FLD_USER_STATUS));
          entAppuser.setLastLoginDate(pstAppUser.getDate(FLD_LAST_LOGIN_DATE));
          entAppuser.setLastLoginIp(pstAppUser.getString(FLD_LAST_LOGIN_IP));
         return entAppuser;
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstAppUser(0), DBException.UNKNOWN);
      }
   }

   public long fetchExc(Entity entity) throws Exception {
      AppUser entAppUser = fetchExc(entity.getOID());
      entity = (Entity) entAppUser;
      return entAppUser.getOID();
   }

   public static synchronized long updateExc(AppUser entAppUser) throws DBException {
      try {
         if (entAppUser.getOID() != 0) {
            PstAppUser pstAppUser = new PstAppUser(entAppUser.getOID());
            pstAppUser.setString(FLD_LOGIN_ID, entAppUser.getLoginId());
            pstAppUser.setString(FLD_PASSWORD, entAppUser.getPassword());
            pstAppUser.setString(FLD_FULL_NAME, entAppUser.getFullName());
            pstAppUser.setString(FLD_EMAIL, entAppUser.getEmail());
            pstAppUser.setString(FLD_DESCRIPTION, entAppUser.getDescription());
            pstAppUser.setDate(FLD_REG_DATE, entAppUser.getRegDate());
            pstAppUser.setDate(FLD_UPDATE_DATE, entAppUser.getUpdateDate());
            pstAppUser.setInt(FLD_USER_STATUS, entAppUser.getUserStatus());
            pstAppUser.setDate(FLD_LAST_LOGIN_DATE, entAppUser.getLastLoginDate());
            pstAppUser.setString(FLD_LAST_LOGIN_IP, entAppUser.getLastLoginIp());
            pstAppUser.setLong(FLD_ID_KARYAWAN, entAppUser.getIdKaryawan());
            pstAppUser.update();
            return entAppUser.getOID();
         }
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstAppUser(0), DBException.UNKNOWN);
      }
      return 0;
   }

   public long updateExc(Entity entity) throws Exception {
      return updateExc((AppUser) entity);
   }

   public static synchronized long deleteExc(long oid) throws DBException {
   try {
   PstAppUser pstAppUser = new PstAppUser(oid);
   pstAppUser.delete();
   } catch (DBException dbe) {
   throw dbe;
   } catch (Exception e) {
   throw new DBException(new PstAppUser(0), DBException.UNKNOWN);
   }
   return oid;
   }

   public long deleteExc(Entity entity) throws Exception {
   if (entity == null) {   throw new DBException(this, DBException.RECORD_NOT_FOUND);
   }   return deleteExc(entity.getOID());
   }

   public static synchronized long insertExc(AppUser entAppUser) throws DBException
   {
   try {
   PstAppUser pstAppUser = new PstAppUser(0);
            pstAppUser.setString(FLD_LOGIN_ID, entAppUser.getLoginId());
            pstAppUser.setString(FLD_PASSWORD, entAppUser.getPassword());
            pstAppUser.setString(FLD_FULL_NAME, entAppUser.getFullName());
            pstAppUser.setString(FLD_EMAIL, entAppUser.getEmail());
            pstAppUser.setString(FLD_DESCRIPTION, entAppUser.getDescription());
            pstAppUser.setDate(FLD_REG_DATE, entAppUser.getRegDate());
            pstAppUser.setDate(FLD_UPDATE_DATE, entAppUser.getUpdateDate());
            pstAppUser.setInt(FLD_USER_STATUS, entAppUser.getUserStatus());
            pstAppUser.setDate(FLD_LAST_LOGIN_DATE, entAppUser.getLastLoginDate());
            pstAppUser.setString(FLD_LAST_LOGIN_IP, entAppUser.getLastLoginIp());
            pstAppUser.setLong(FLD_ID_KARYAWAN, entAppUser.getIdKaryawan());
   pstAppUser.insert();
   entAppUser.setOID(pstAppUser.getlong(FLD_USER_ID));
   } catch (DBException dbe) {
   throw dbe;
   } catch (Exception e) {
   throw new DBException(new PstAppUser(0), DBException.UNKNOWN);
   }
   return entAppUser.getOID();
   }
   public long insertExc(Entity entity) throws Exception {
   return insertExc((AppUser) entity);
   }
   public static void resultToObject(ResultSet rs, AppUser entAppUser) {
   try {
          entAppUser.setOID(rs.getLong(PstAppUser.fieldNames[PstAppUser.FLD_USER_ID]));
            entAppUser.setLoginId(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID]));
            entAppUser.setPassword(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_PASSWORD]));
            entAppUser.setFullName(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME]));
            entAppUser.setEmail(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_EMAIL]));
            entAppUser.setDescription(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_DESCRIPTION]));
            entAppUser.setRegDate(rs.getDate(PstAppUser.fieldNames[PstAppUser.FLD_REG_DATE]));
            entAppUser.setUpdateDate(rs.getDate(PstAppUser.fieldNames[PstAppUser.FLD_UPDATE_DATE]));
            entAppUser.setUserStatus(rs.getInt(PstAppUser.fieldNames[PstAppUser.FLD_USER_STATUS]));
            entAppUser.setLastLoginDate(rs.getDate(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_DATE]));
            entAppUser.setLastLoginIp(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_IP]));
   } catch (Exception e) {
   }
   }
public static Vector listAll() {return list(0, 500, "", "");
}

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_APPUSER;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                AppUser entAppUser = new AppUser();
                resultToObject(rs, entAppUser);
                lists.add(entAppUser);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    public static Vector listWithJoinKaryawan(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT app_user.*,karyawan.`NAMA`  AS NAMA_KARYAWAN FROM app_user  \n" +
                        "INNER JOIN karyawan \n" +
                        "ON karyawan.`ID_KARYAWAN` = app_user.`EMPLOYEE_ID` " ;
             if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                AppUser entAppUser = new AppUser();
                    entAppUser.setOID(rs.getLong(PstAppUser.fieldNames[PstAppUser.FLD_USER_ID]));
                    entAppUser.setLoginId(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID]));
                    entAppUser.setPassword(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_PASSWORD]));
                    entAppUser.setFullName(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME]));
                    entAppUser.setEmail(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_EMAIL]));
                    entAppUser.setDescription(rs.getString(PstAppUser.fieldNames[PstAppUser.FLD_DESCRIPTION]));
                    entAppUser.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
                lists.add(entAppUser);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    public static Vector listKaryawan(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM karyawan " ;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Karyawan entKaryawan= new Karyawan();
                entKaryawan.setOID(rs.getLong("ID_KARYAWAN"));
                entKaryawan.setNama(rs.getString("NAMA"));
                entKaryawan.setUmur(rs.getInt("UMUR"));
                entKaryawan.setAlamat(rs.getString("ALAMAT"));
                lists.add(entKaryawan);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

public static int getCount(String whereClause) {
DBResultSet dbrs = null;
try {
String sql = "SELECT COUNT(" + PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID] + ") FROM " + TBL_APPUSER;
if (whereClause != null && whereClause.length() > 0) {
sql = sql + " WHERE " + whereClause;
}
dbrs = DBHandler.execQueryResult(sql);
ResultSet rs = dbrs.getResultSet();
int count = 0;
while (rs.next()) {
count = rs.getInt(1);
}
rs.close();
return count;
} catch (Exception e) {
return 0;
} finally {
DBResultSet.close(dbrs);
}
}

public static int findLimitStart(long oid, int recordToGet, String whereClause, String orderClause) {
int size = getCount(whereClause);
int start = 0;
boolean found = false;
for (int i = 0; (i < size) && !found; i = i + recordToGet) {
Vector list = list(i, recordToGet, whereClause, orderClause);
start = i;
if (list.size() > 0) {
for (int ls = 0; ls < list.size(); ls++) {
AppUser entAppUser = (AppUser) list.get(ls);
if (oid == entAppUser.getOID()) {
found = true;
}
}
}
}
if ((start >= size) && (size > 0)) {
start = start - recordToGet;
}
return start;
}

public static int findLimitCommand(int start, int recordToGet, int vectSize) {
int cmd = Command.LIST;
int mdl = vectSize % recordToGet;
vectSize = vectSize + (recordToGet - mdl);
if (start == 0) {
cmd = Command.FIRST;
} else {
if (start == (vectSize - recordToGet)) {
cmd = Command.LAST;
} else {
start = start + recordToGet;
if (start <= (vectSize - recordToGet)) {
cmd = Command.NEXT;
System.out.println("next.......................");
} else {
start = start - recordToGet;
if (start > 0) {
cmd = Command.PREV;
System.out.println("prev.......................");
}
}
}
}
return cmd;
}
//Add by Eri Yudi 2020-07-01
//Method for API

    public static JSONObject fetchJSON(AppUser entAppUser){
      JSONObject object = new JSONObject();
      try {
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_USER_ID],""+entAppUser.getOID());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID],""+entAppUser.getLoginId());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_PASSWORD],""+entAppUser.getPassword());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME],""+entAppUser.getFullName());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_EMAIL],""+entAppUser.getEmail());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_DESCRIPTION],""+entAppUser.getDescription());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_REG_DATE],(entAppUser.getRegDate() != null)?Formater.formatDate(entAppUser.getRegDate(),"yyyy-MM-dd HH:mm:ss"):"0000-00-00 00:00:00");
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_UPDATE_DATE],(entAppUser.getUpdateDate() != null)?Formater.formatDate(entAppUser.getUpdateDate(),"yyyy-MM-dd HH:mm:ss"):"0000-00-00 00:00:00");
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_USER_STATUS],""+entAppUser.getUserStatus());
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_DATE],(entAppUser.getLastLoginDate() != null)?Formater.formatDate(entAppUser.getLastLoginDate(),"yyyy-MM-dd HH:mm:ss"):"0000-00-00 00:00:00");
         object.put(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_IP],(entAppUser.getLastLoginIp() != null)?entAppUser.getLastLoginIp():"");
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return object;
   }


   public static long syncExc(JSONObject jSONObject) throws DBException {
      long oid = 0;
      try{
         oid = Long.valueOf((String)jSONObject.get(PstAppUser.fieldNames[PstAppUser.FLD_USER_ID]));         AppUser entAppUser = PstAppUser.parseJsonObject(jSONObject);
         oid = entAppUser.getOID();
         boolean chekOid = PstAppUser.checkOID(oid);
            if(chekOid){
               // Doing update
               oid = PstAppUser.updateExc(entAppUser);
            }else{
               // Doing insert
               oid = PstAppUser.insertExc(entAppUser);
            }
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return oid;
   }


   private static AppUser parseJsonObject(JSONObject jsonObject){
      AppUser entAppUser = new AppUser();
      try {
         entAppUser.setOID(Long.valueOf((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_USER_ID])));
         entAppUser.setLoginId((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_LOGIN_ID]));
         entAppUser.setPassword((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_PASSWORD]));
         entAppUser.setFullName((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_FULL_NAME]));
         entAppUser.setEmail((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_EMAIL]));
         entAppUser.setDescription((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_DESCRIPTION]));
         entAppUser.setRegDate(Formater.formatDate((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_REG_DATE]),"yyyy-MM-dd HH:mm:ss"));
         entAppUser.setUpdateDate(Formater.formatDate((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_UPDATE_DATE]),"yyyy-MM-dd HH:mm:ss"));
         entAppUser.setUserStatus(Integer.valueOf((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_USER_STATUS])));
         entAppUser.setLastLoginDate(Formater.formatDate((String) jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_DATE]),"yyyy-MM-dd HH:mm:ss"));
         entAppUser.setLastLoginIp((String)jsonObject.get(PstAppUser.fieldNames[PstAppUser.FLD_LAST_LOGIN_IP]));
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return entAppUser;
   }
   
   
   public static boolean checkOID(long userId ) {
      DBResultSet dbrs = null;
      boolean result = false;
      try{
         String sql = "SELECT * FROM " + TBL_APPUSER
            +" WHERE "+ PstAppUser.fieldNames[PstAppUser.FLD_USER_ID]
            +" = " + userId;
         dbrs = DBHandler.execQueryResult(sql);
         ResultSet rs = dbrs.getResultSet();
         
         while (rs.next()) {
            result = true;
         }
         rs.close();
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      } finally {
         DBResultSet.close(dbrs);
      }
      return result;
   }


}

