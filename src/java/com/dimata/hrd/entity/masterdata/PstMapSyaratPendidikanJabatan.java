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

public class PstMapSyaratPendidikanJabatan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

   public static final String TBL_MAPSYARATPENDIDIKANJABATAN = "mapsyaratpendidikanjabatan";
   public static final int FLD_ID_MAP_PENDIDIKAN_JABATAN = 0;
   public static final int FLD_ID_JABATAN = 1;
   public static final int FLD_PENDIDIKAN_MIN = 2;
   public static final int FLD_PENDIDIKAN_REK = 3;

   public static String[] fieldNames = {
      "ID_MAP_PENDIDIKAN_JABATAN",
      "ID_JABATAN",
      "PENDIDIKAN_MIN",
      "PENDIDIKAN_REK"
   };

   public static int[] fieldTypes = {
      TYPE_LONG+TYPE_PK+TYPE_ID,
      TYPE_LONG,
      TYPE_STRING,
      TYPE_STRING
   };
    private static String TBL_MAP_SYARAT_PENDIDIKAN_JABATAN;

    private static long update(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static long insert(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public PstMapSyaratPendidikanJabatan() {
   }

   public PstMapSyaratPendidikanJabatan(int i) throws DBException {
      super(new PstMapSyaratPendidikanJabatan());
   }

   public PstMapSyaratPendidikanJabatan(String sOid) throws DBException {
      super(new PstMapSyaratPendidikanJabatan(0));
      if(!locate(sOid))
         throw new DBException(this, DBException.RECORD_NOT_FOUND);
      else
         return;
   }

   public PstMapSyaratPendidikanJabatan(long lOid) throws DBException {
      super(new PstMapSyaratPendidikanJabatan(0));
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
      return TBL_MAPSYARATPENDIDIKANJABATAN;
   }

   public String[] getFieldNames() {
      return fieldNames;
   }

   public int[] getFieldTypes() {
      return fieldTypes;
   }

   public String getPersistentName() {
      return new PstMapSyaratPendidikanJabatan().getClass().getName();
   }

   public static MapSyaratPendidikanJabatan fetchExc(long oid) throws DBException {
      try {
         MapSyaratPendidikanJabatan entMapsyaratpendidikanjabatan = new MapSyaratPendidikanJabatan();
         PstMapSyaratPendidikanJabatan pstMapSyaratPendidikanJabatan = new PstMapSyaratPendidikanJabatan(oid);
          entMapsyaratpendidikanjabatan.setOID(oid);
          entMapsyaratpendidikanjabatan.setIdJabatan(pstMapSyaratPendidikanJabatan.getlong(FLD_ID_JABATAN));
          entMapsyaratpendidikanjabatan.setPendidikanMin(pstMapSyaratPendidikanJabatan.getString(FLD_PENDIDIKAN_MIN));
          entMapsyaratpendidikanjabatan.setPendidikanRek(pstMapSyaratPendidikanJabatan.getString(FLD_PENDIDIKAN_REK));
         return entMapsyaratpendidikanjabatan;
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstMapSyaratPendidikanJabatan(0), DBException.UNKNOWN);
      }
   }

   public long fetchExc(Entity entity) throws Exception {
      MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan = fetchExc(entity.getOID());
      entity = (Entity) entMapSyaratPendidikanJabatan;
      return entMapSyaratPendidikanJabatan.getOID();
   }

   public static synchronized long updateExc(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) throws DBException {
      try {
         if (entMapSyaratPendidikanJabatan.getOID() != 0) {
            PstMapSyaratPendidikanJabatan pstMapSyaratPendidikanJabatan = new PstMapSyaratPendidikanJabatan(entMapSyaratPendidikanJabatan.getOID());
            pstMapSyaratPendidikanJabatan.setLong(FLD_ID_JABATAN, entMapSyaratPendidikanJabatan.getIdJabatan());
            pstMapSyaratPendidikanJabatan.setString(FLD_PENDIDIKAN_MIN, entMapSyaratPendidikanJabatan.getPendidikanMin());
            pstMapSyaratPendidikanJabatan.setString(FLD_PENDIDIKAN_REK, entMapSyaratPendidikanJabatan.getPendidikanRek());
            pstMapSyaratPendidikanJabatan.update();
            return entMapSyaratPendidikanJabatan.getOID();
         }
      } catch (DBException dbe) {
         throw dbe;
      } catch (Exception e) {
         throw new DBException(new PstMapSyaratPendidikanJabatan(0), DBException.UNKNOWN);
      }
      return 0;
   }

   public long updateExc(Entity entity) throws Exception {
      return updateExc((MapSyaratPendidikanJabatan) entity);
   }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMapSyaratPendidikanJabatan pstMapSyaratPendidikanJabatan = new PstMapSyaratPendidikanJabatan(oid);
            pstMapSyaratPendidikanJabatan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapSyaratPendidikanJabatan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) throws DBException {
        try {
            PstMapSyaratPendidikanJabatan pstMapSyaratPendidikanJabatan = new PstMapSyaratPendidikanJabatan(0);
            pstMapSyaratPendidikanJabatan.setLong(FLD_ID_JABATAN, entMapSyaratPendidikanJabatan.getIdJabatan());
            pstMapSyaratPendidikanJabatan.setString(FLD_PENDIDIKAN_MIN, entMapSyaratPendidikanJabatan.getPendidikanMin());
            pstMapSyaratPendidikanJabatan.setString(FLD_PENDIDIKAN_REK, entMapSyaratPendidikanJabatan.getPendidikanRek());
            pstMapSyaratPendidikanJabatan.insert();
            entMapSyaratPendidikanJabatan.setOID(pstMapSyaratPendidikanJabatan.getlong(FLD_ID_MAP_PENDIDIKAN_JABATAN));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapSyaratPendidikanJabatan(0), DBException.UNKNOWN);
        }
        return entMapSyaratPendidikanJabatan.getOID();
    }
    
    public long insertExc(Entity entity) throws Exception {
        return insertExc((MapSyaratPendidikanJabatan) entity);
    }
    
    public static void resultToObject(ResultSet rs, MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan) {
        try {
            entMapSyaratPendidikanJabatan.setOID(rs.getLong(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN]));
            entMapSyaratPendidikanJabatan.setIdJabatan(rs.getLong(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_JABATAN]));
            entMapSyaratPendidikanJabatan.setPendidikanMin(rs.getString(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_MIN]));
            entMapSyaratPendidikanJabatan.setPendidikanRek(rs.getString(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_REK]));
        } catch (Exception e) {
        }
    }
    
    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_MAPSYARATPENDIDIKANJABATAN;
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
                MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan = new MapSyaratPendidikanJabatan();
                resultToObject(rs, entMapSyaratPendidikanJabatan);
                lists.add(entMapSyaratPendidikanJabatan);
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
            String sql = "SELECT COUNT(" + PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_JABATAN] + ") FROM " + TBL_MAPSYARATPENDIDIKANJABATAN;
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
                    MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan = (MapSyaratPendidikanJabatan) list.get(ls);
                    if (oid == entMapSyaratPendidikanJabatan.getOID()) {
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

    public static JSONObject fetchJSON(MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan){
      JSONObject object = new JSONObject();
      try {
         object.put(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN],""+entMapSyaratPendidikanJabatan.getOID());
         object.put(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_JABATAN],""+entMapSyaratPendidikanJabatan.getIdJabatan());
         object.put(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_MIN],""+entMapSyaratPendidikanJabatan.getPendidikanMin());
         object.put(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_REK],""+entMapSyaratPendidikanJabatan.getPendidikanRek());
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return object;
   }


   public static long syncExc(JSONObject jSONObject) throws DBException {
      long oid = 0;
      try{
         oid = Long.valueOf((String)jSONObject.get(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN]));         MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan = PstMapSyaratPendidikanJabatan.parseJsonObject(jSONObject);
         oid = entMapSyaratPendidikanJabatan.getOID();
         boolean chekOid = PstMapSyaratPendidikanJabatan.checkOID(oid);
            if(chekOid){
               // Doing update
               oid = PstMapSyaratPendidikanJabatan.update(entMapSyaratPendidikanJabatan);
            }else{
               // Doing insert
               oid = PstMapSyaratPendidikanJabatan.insert(entMapSyaratPendidikanJabatan);
            }
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return oid;
   }


   private static MapSyaratPendidikanJabatan parseJsonObject(JSONObject jsonObject){
      MapSyaratPendidikanJabatan entMapSyaratPendidikanJabatan = new MapSyaratPendidikanJabatan();
      try {
         entMapSyaratPendidikanJabatan.setOID(Long.valueOf((String) jsonObject.get(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN])));
         entMapSyaratPendidikanJabatan.setIdJabatan(Long.valueOf((String) jsonObject.get(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_JABATAN])));
         entMapSyaratPendidikanJabatan.setPendidikanMin((String) jsonObject.get(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_MIN]));
         entMapSyaratPendidikanJabatan.setPendidikanRek((String) jsonObject.get(PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_PENDIDIKAN_REK]));
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return entMapSyaratPendidikanJabatan;
   }

   public static boolean checkOID(long idMapPendidikanJabatan ) {
      DBResultSet dbrs = null;
      boolean result = false;
      try{
         String sql = "SELECT * FROM " + TBL_MAP_SYARAT_PENDIDIKAN_JABATAN
            +" WHERE "+ PstMapSyaratPendidikanJabatan.fieldNames[PstMapSyaratPendidikanJabatan.FLD_ID_MAP_PENDIDIKAN_JABATAN]
            +" = " + idMapPendidikanJabatan;
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


