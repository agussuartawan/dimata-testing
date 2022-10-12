/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.entity.masterdata;

/**
 *
 * @author VegaNirmala
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;
import org.json.JSONObject;

public class PstPendidikan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

   public static final String TBL_PENDIDIKAN = "pendidikan";
   public static final int FLD_ID_PENDIDIKAN = 0;
   public static final int FLD_PENDIDIKAN = 1;
   public static final int FLD_LEVEL = 2;

   public static String[] fieldNames = {
      "ID_PENDIDIKAN",
      "PENDIDIKAN",
      "LEVEL"
   };

   public static int[] fieldTypes = {
      TYPE_LONG+TYPE_PK+TYPE_ID,
      TYPE_STRING,
      TYPE_INT
   };

    private static long insert(Pendidikan entPendidikan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static long update(Pendidikan entPendidikan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public PstPendidikan() {
   }

   public PstPendidikan(int i) throws DBException {
      super(new PstPendidikan());
   }

   public PstPendidikan(String sOid) throws DBException {
      super(new PstPendidikan(0));
      if(!locate(sOid))
         throw new DBException(this, DBException.RECORD_NOT_FOUND);
      else
         return;
   }

   public PstPendidikan(long lOid) throws DBException {
      super(new PstPendidikan(0));
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
      return TBL_PENDIDIKAN;
   }

   public String[] getFieldNames() {
      return fieldNames;
   }

   public int[] getFieldTypes() {
      return fieldTypes;
   }

   public String getPersistentName() {
      return new PstPendidikan().getClass().getName();
   }

   public static Pendidikan fetchExc(long oid) throws DBException {
      try {
         Pendidikan entPendidikan = new Pendidikan();
         PstPendidikan pstPendidikan = new PstPendidikan(oid);
          entPendidikan.setOID(oid);
          entPendidikan.setPendidikan(pstPendidikan.getString(FLD_PENDIDIKAN));
          entPendidikan.setLevel(pstPendidikan.getInt(FLD_LEVEL));
         return entPendidikan;
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstPendidikan(0), DBException.UNKNOWN);
      }
   }

   public long fetchExc(Entity entity) throws Exception {
      Pendidikan entPendidikan = fetchExc(entity.getOID());
      entity = (Entity) entPendidikan;
      return entPendidikan.getOID();
   }

   public static synchronized long updateExc(Pendidikan entPendidikan) throws DBException {
      try {
         if (entPendidikan.getOID() != 0) {
            PstPendidikan pstPendidikan = new PstPendidikan(entPendidikan.getOID());
            pstPendidikan.setString(FLD_PENDIDIKAN, entPendidikan.getPendidikan());
            pstPendidikan.setInt(FLD_LEVEL, entPendidikan.getLevel());
            pstPendidikan.update();
            return entPendidikan.getOID();
         }
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstPendidikan(0), DBException.UNKNOWN);
      }
      return 0;
   }

   public long updateExc(Entity entity) throws Exception {
      return updateExc((Pendidikan) entity);
   }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPendidikan pstPendidikan = new PstPendidikan(oid);
            pstPendidikan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPendidikan(0), DBException.UNKNOWN);
        }
        return oid;
    }

   public long deleteExc(Entity entity) throws Exception {
   if (entity == null) {   throw new DBException(this, DBException.RECORD_NOT_FOUND);
   }   return deleteExc(entity.getOID());
   }

    public static synchronized long insertExc(Pendidikan entPendidikan) throws DBException {
        try {
            PstPendidikan pstPendidikan = new PstPendidikan(0);
            pstPendidikan.setString(FLD_PENDIDIKAN, entPendidikan.getPendidikan());
            pstPendidikan.setInt(FLD_LEVEL, entPendidikan.getLevel());
            pstPendidikan.insert();
            entPendidikan.setOID(pstPendidikan.getlong(FLD_ID_PENDIDIKAN));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPendidikan(0), DBException.UNKNOWN);
        }
        return entPendidikan.getOID();
    }
   public long insertExc(Entity entity) throws Exception {
   return insertExc((Pendidikan) entity);
   }
   public static void resultToObject(ResultSet rs, Pendidikan entPendidikan) {
   try {
          entPendidikan.setOID(rs.getLong(PstPendidikan.fieldNames[PstPendidikan.FLD_ID_PENDIDIKAN]));
            entPendidikan.setPendidikan(rs.getString(PstPendidikan.fieldNames[PstPendidikan.FLD_PENDIDIKAN]));
            entPendidikan.setLevel(rs.getInt(PstPendidikan.fieldNames[PstPendidikan.FLD_LEVEL]));
   } catch (Exception e) {
   }
   }
public static Vector listAll() {return list(0, 500, "", "");
}

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_PENDIDIKAN;
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
                Pendidikan entPendidikan = new Pendidikan();
                resultToObject(rs, entPendidikan);
                lists.add(entPendidikan);
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
            String sql = "SELECT COUNT(" + PstPendidikan.fieldNames[PstPendidikan.FLD_PENDIDIKAN] + ") FROM " + TBL_PENDIDIKAN;
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
                    Pendidikan entPendidikan = (Pendidikan) list.get(ls);
                    if (oid == entPendidikan.getOID()) {
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

    public static JSONObject fetchJSON(Pendidikan entPendidikan){
      JSONObject object = new JSONObject();
      try {
         object.put(PstPendidikan.fieldNames[PstPendidikan.FLD_ID_PENDIDIKAN],""+entPendidikan.getOID());
         object.put(PstPendidikan.fieldNames[PstPendidikan.FLD_PENDIDIKAN],""+entPendidikan.getPendidikan());
         object.put(PstPendidikan.fieldNames[PstPendidikan.FLD_LEVEL],""+entPendidikan.getLevel());
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return object;
   }


   public static long syncExc(JSONObject jSONObject) throws DBException {
      long oid = 0;
      try{
         oid = Long.valueOf((String)jSONObject.get(PstPendidikan.fieldNames[PstPendidikan.FLD_ID_PENDIDIKAN]));         Pendidikan entPendidikan = PstPendidikan.parseJsonObject(jSONObject);
         oid = entPendidikan.getOID();
         boolean chekOid = PstPendidikan.checkOID(oid);
            if(chekOid){
               // Doing update
               oid = PstPendidikan.update(entPendidikan);
            }else{
               // Doing insert
               oid = PstPendidikan.insert(entPendidikan);
            }
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return oid;
   }


   private static Pendidikan parseJsonObject(JSONObject jsonObject){
      Pendidikan entPendidikan = new Pendidikan();
      try {
         entPendidikan.setOID(Long.valueOf((String) jsonObject.get(PstPendidikan.fieldNames[PstPendidikan.FLD_ID_PENDIDIKAN])));
         entPendidikan.setPendidikan((String) jsonObject.get(PstPendidikan.fieldNames[PstPendidikan.FLD_PENDIDIKAN]));
         entPendidikan.setLevel(Integer.valueOf((String) jsonObject.get(PstPendidikan.fieldNames[PstPendidikan.FLD_LEVEL])));
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return entPendidikan;
   }

   public static boolean checkOID(long idPendidikan ) {
      DBResultSet dbrs = null;
      boolean result = false;
      try{
         String sql = "SELECT * FROM " + TBL_PENDIDIKAN
            +" WHERE "+ PstPendidikan.fieldNames[PstPendidikan.FLD_ID_PENDIDIKAN]
            +" = " + idPendidikan;
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




