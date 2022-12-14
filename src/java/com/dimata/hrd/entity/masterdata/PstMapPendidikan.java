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

public class PstMapPendidikan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

   public static final String TBL_MAPPENDIDIKAN = "mappendidikan";
   public static final int FLD_ID_MAP_PENDIDIKAN = 0;
   public static final int FLD_ID_KARYAWAN = 1;
   public static final int FLD_ID_PENDIDIKAN = 2;
   public static final int FLD_TAHUN_MULAI = 3;
   public static final int FLD_TAHUN_SELESAI = 4;

   public static String[] fieldNames = {
      "ID_MAP_PENDIDIKAN",
      "ID_KARYAWAN",
      "ID_PENDIDIKAN",
      "TAHUN_MULAI",
      "TAHUN_SELESAI"
   };

   public static int[] fieldTypes = {
      TYPE_LONG+TYPE_PK+TYPE_ID,
      TYPE_LONG,
      TYPE_LONG,
      TYPE_INT,
      TYPE_INT
   };

    private static long update(MapPendidikan entMapPendidikan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static long insert(MapPendidikan entMapPendidikan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PstMapPendidikan() {
    }

    public PstMapPendidikan(int i) throws DBException {
        super(new PstMapPendidikan());
    }

    public PstMapPendidikan(String sOid) throws DBException {
        super(new PstMapPendidikan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMapPendidikan(long lOid) throws DBException {
        super(new PstMapPendidikan(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        } catch (Exception e) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public String getTableName() {
        return TBL_MAPPENDIDIKAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMapPendidikan().getClass().getName();
    }

    public static MapPendidikan fetchExc(long oid) throws DBException {
        try {
            MapPendidikan entMappendidikan = new MapPendidikan();
            PstMapPendidikan pstMapPendidikan = new PstMapPendidikan(oid);
            entMappendidikan.setOID(oid);
            entMappendidikan.setIdKaryawan(pstMapPendidikan.getlong(FLD_ID_KARYAWAN));
            entMappendidikan.setIdPendidikan(pstMapPendidikan.getlong(FLD_ID_PENDIDIKAN));
            entMappendidikan.setTahunMulai(pstMapPendidikan.getInt(FLD_TAHUN_MULAI));
            entMappendidikan.setTahunSelesai(pstMapPendidikan.getInt(FLD_TAHUN_SELESAI));
            return entMappendidikan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapPendidikan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MapPendidikan entMapPendidikan = fetchExc(entity.getOID());
        entity = (Entity) entMapPendidikan;
        return entMapPendidikan.getOID();
    }

    public static synchronized long updateExc(MapPendidikan entMapPendidikan) throws DBException {
        try {
            if (entMapPendidikan.getOID() != 0) {
                PstMapPendidikan pstMapPendidikan = new PstMapPendidikan(entMapPendidikan.getOID());
                pstMapPendidikan.setLong(FLD_ID_KARYAWAN, entMapPendidikan.getIdKaryawan());
                pstMapPendidikan.setLong(FLD_ID_PENDIDIKAN, entMapPendidikan.getIdPendidikan());
                pstMapPendidikan.setInt(FLD_TAHUN_MULAI, entMapPendidikan.getTahunMulai());
                pstMapPendidikan.setInt(FLD_TAHUN_SELESAI, entMapPendidikan.getTahunSelesai());
                pstMapPendidikan.update();
                return entMapPendidikan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapPendidikan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MapPendidikan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMapPendidikan pstMapPendidikan = new PstMapPendidikan(oid);
            pstMapPendidikan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapPendidikan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MapPendidikan entMapPendidikan) throws DBException {
        try {
            PstMapPendidikan pstMapPendidikan = new PstMapPendidikan(0);
            pstMapPendidikan.setLong(FLD_ID_KARYAWAN, entMapPendidikan.getIdKaryawan());
            pstMapPendidikan.setLong(FLD_ID_PENDIDIKAN, entMapPendidikan.getIdPendidikan());
            pstMapPendidikan.setInt(FLD_TAHUN_MULAI, entMapPendidikan.getTahunMulai());
            pstMapPendidikan.setInt(FLD_TAHUN_SELESAI, entMapPendidikan.getTahunSelesai());
            pstMapPendidikan.insert();
            entMapPendidikan.setOID(pstMapPendidikan.getlong(FLD_ID_MAP_PENDIDIKAN));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMapPendidikan(0), DBException.UNKNOWN);
        }
        return entMapPendidikan.getOID();
    }
    
    public long insertExc(Entity entity) throws Exception {
        return insertExc((MapPendidikan) entity);
    }
    
    public static void resultToObject(ResultSet rs, MapPendidikan entMapPendidikan) {
        try {
            entMapPendidikan.setOID(rs.getLong(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_MAP_PENDIDIKAN]));
            entMapPendidikan.setIdKaryawan(rs.getLong(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_KARYAWAN]));
            entMapPendidikan.setIdPendidikan(rs.getLong(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_PENDIDIKAN]));
            entMapPendidikan.setTahunMulai(rs.getInt(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_MULAI]));
            entMapPendidikan.setTahunSelesai(rs.getInt(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_SELESAI]));
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
            String sql = "SELECT * FROM " + TBL_MAPPENDIDIKAN;
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
                MapPendidikan entMapPendidikan = new MapPendidikan();
                resultToObject(rs, entMapPendidikan);
                lists.add(entMapPendidikan);
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
String sql = "SELECT COUNT(" + PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_KARYAWAN] + ") FROM " + TBL_MAPPENDIDIKAN;
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
MapPendidikan entMapPendidikan = (MapPendidikan) list.get(ls);
if (oid == entMapPendidikan.getOID()) {
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

    public static JSONObject fetchJSON(MapPendidikan entMapPendidikan){
      JSONObject object = new JSONObject();
      try {
         object.put(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_MAP_PENDIDIKAN],""+entMapPendidikan.getOID());
         object.put(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_KARYAWAN],""+entMapPendidikan.getIdKaryawan());
         object.put(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_PENDIDIKAN],""+entMapPendidikan.getIdPendidikan());
         object.put(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_MULAI],""+entMapPendidikan.getTahunMulai());
         object.put(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_SELESAI],""+entMapPendidikan.getTahunSelesai());
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return object;
   }


   public static long syncExc(JSONObject jSONObject) throws DBException {
      long oid = 0;
      try{
         oid = Long.valueOf((String)jSONObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_MAP_PENDIDIKAN]));         MapPendidikan entMapPendidikan = PstMapPendidikan.parseJsonObject(jSONObject);
         oid = entMapPendidikan.getOID();
         boolean chekOid = PstMapPendidikan.checkOID(oid);
            if(chekOid){
               // Doing update
               oid = PstMapPendidikan.update(entMapPendidikan);
            }else{
               // Doing insert
               oid = PstMapPendidikan.insert(entMapPendidikan);
            }
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return oid;
   }


   private static MapPendidikan parseJsonObject(JSONObject jsonObject){
      MapPendidikan entMapPendidikan = new MapPendidikan();
      try {
         entMapPendidikan.setOID(Long.valueOf((String) jsonObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_MAP_PENDIDIKAN])));
         entMapPendidikan.setIdKaryawan(Long.valueOf((String) jsonObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_KARYAWAN])));
         entMapPendidikan.setIdPendidikan(Long.valueOf((String) jsonObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_PENDIDIKAN])));
         entMapPendidikan.setTahunMulai(Integer.valueOf((String) jsonObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_MULAI])));
         entMapPendidikan.setTahunSelesai(Integer.valueOf((String) jsonObject.get(PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_TAHUN_SELESAI])));
      }catch(Exception exc){
         System.out.println("Err :"+exc);
      }
      return entMapPendidikan;
   }

public static boolean checkOID(long idMapPendidikan ) {
      DBResultSet dbrs = null;
      boolean result = false;
      try{
         String sql = "SELECT * FROM " + TBL_MAPPENDIDIKAN
            +" WHERE "+ PstMapPendidikan.fieldNames[PstMapPendidikan.FLD_ID_MAP_PENDIDIKAN]
            +" = " + idMapPendidikan;
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

   

 
