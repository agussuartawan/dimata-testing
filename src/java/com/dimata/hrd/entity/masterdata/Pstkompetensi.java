package com.dimata.hrd.entity.masterdata;

import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;
import org.json.JSONObject;

public class Pstkompetensi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_KOMPETENSI = "kompetensi";
    public static final int FLD_ID_KOMPETENSI = 0;
    public static final int FLD_KOMPETENSI = 1;

    public static String[] fieldNames = {
        "ID_KOMPETENSI",
        "KOMPETENSI"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING
    };

    public Pstkompetensi() {
    }

    public Pstkompetensi(int i) throws DBException {
        super(new Pstkompetensi());
    }

    public Pstkompetensi(String sOid) throws DBException {
        super(new Pstkompetensi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public Pstkompetensi(long lOid) throws DBException {
        super(new Pstkompetensi(0));
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
        return TBL_KOMPETENSI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new Pstkompetensi().getClass().getName();
    }

    public static kompetensi fetchExc(long oid) throws DBException {
        try {
            kompetensi entKompetensi = new kompetensi();
            Pstkompetensi pstKompetensi = new Pstkompetensi(oid);
            entKompetensi.setOID(oid);
            entKompetensi.setKompetensi(pstKompetensi.getString(FLD_KOMPETENSI));
            return entKompetensi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new Pstkompetensi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        kompetensi entkompetensi = fetchExc(entity.getOID());
        entity = (Entity) entkompetensi;
        return entkompetensi.getOID();
    }

    public static synchronized long updateExc(kompetensi entkompetensi) throws DBException {
        try {
            if (entkompetensi.getOID() != 0) {
                Pstkompetensi pstkompetensi = new Pstkompetensi(entkompetensi.getOID());
                pstkompetensi.setString(FLD_KOMPETENSI, entkompetensi.getKompetensi());
                pstkompetensi.update();
                return entkompetensi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new Pstkompetensi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((kompetensi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            Pstkompetensi pstkompetensi = new Pstkompetensi(oid);
            pstkompetensi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new Pstkompetensi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(kompetensi entkompetensi) throws DBException {
        try {
            Pstkompetensi pstkompetensi = new Pstkompetensi(0);
            pstkompetensi.setString(FLD_KOMPETENSI, entkompetensi.getKompetensi());
            pstkompetensi.insert();
            entkompetensi.setOID(pstkompetensi.getlong(FLD_ID_KOMPETENSI));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new Pstkompetensi(0), DBException.UNKNOWN);
        }
        return entkompetensi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((kompetensi) entity);
    }

    public static void resultToObject(ResultSet rs, kompetensi entkompetensi) {
        try {
            entkompetensi.setOID(rs.getLong(Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI]));
            entkompetensi.setKompetensi(rs.getString(Pstkompetensi.fieldNames[Pstkompetensi.FLD_KOMPETENSI]));
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
            String sql = "SELECT * FROM " + TBL_KOMPETENSI;
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
                kompetensi entkompetensi = new kompetensi();
                resultToObject(rs, entkompetensi);
                lists.add(entkompetensi);
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
            String sql = "SELECT COUNT(" + Pstkompetensi.fieldNames[Pstkompetensi.FLD_KOMPETENSI] + ") FROM " + TBL_KOMPETENSI;
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
                    kompetensi entkompetensi = (kompetensi) list.get(ls);
                    if (oid == entkompetensi.getOID()) {
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

    public static JSONObject fetchJSON(kompetensi entkompetensi) {
        JSONObject object = new JSONObject();
        try {
            object.put(Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI], "" + entkompetensi.getOID());
            object.put(Pstkompetensi.fieldNames[Pstkompetensi.FLD_KOMPETENSI], "" + entkompetensi.getKompetensi());
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return object;
    }

    public static long syncExc(JSONObject jSONObject) throws DBException {
        long oid = 0;
        try {
            oid = Long.valueOf((String) jSONObject.get(Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI]));
            kompetensi entkompetensi = Pstkompetensi.parseJsonObject(jSONObject);
            oid = entkompetensi.getOID();
            boolean chekOid = Pstkompetensi.checkOID(oid);
            if (chekOid) {
                // Doing update
                oid = Pstkompetensi.updateExc(entkompetensi);
            } else {
                // Doing insert
                oid = Pstkompetensi.insertExc(entkompetensi);
            }
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return oid;
    }

    private static kompetensi parseJsonObject(JSONObject jsonObject) {
        kompetensi entkompetensi = new kompetensi();
        try {
            entkompetensi.setOID(Long.valueOf((String) jsonObject.get(Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI])));
            entkompetensi.setKompetensi((String) jsonObject.get(Pstkompetensi.fieldNames[Pstkompetensi.FLD_KOMPETENSI]));
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return entkompetensi;
    }
    public static boolean checkOID(long idKompetensi ) {
      DBResultSet dbrs = null;
      boolean result = false;
      try{
         String sql = "SELECT * FROM " + TBL_KOMPETENSI
            +" WHERE "+ Pstkompetensi.fieldNames[Pstkompetensi.FLD_ID_KOMPETENSI]
            +" = " + idKompetensi;
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

