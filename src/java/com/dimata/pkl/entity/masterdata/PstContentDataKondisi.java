/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.pkl.entity.masterdata;

import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

/**
 *
 * @author Dewa
 */
public class PstContentDataKondisi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_KONDISI = "dslik_cd_kondisi";
    public static final int FLD_KONDISI_OID = 0;
    public static final int FLD_NAMA_KONDISI = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "KONDISI_OID",
        "NAMA_KONDISI",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataKondisi() {
    }

    public PstContentDataKondisi(int i) throws DBException {
        super(new PstContentDataKondisi());
    }

    public PstContentDataKondisi(String sOid) throws DBException {
        super(new PstContentDataKondisi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataKondisi(long lOid) throws DBException {
        super(new PstContentDataKondisi(0));
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
        return TBL_CONTENT_DATA_KONDISI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataKondisi().getClass().getName();
    }

    public static ContentDataKondisi fetchExc(long oid) throws DBException {
        try {
            ContentDataKondisi entContentDataKondisi = new ContentDataKondisi();
            PstContentDataKondisi pstContentDataKondisi = new PstContentDataKondisi(oid);
            entContentDataKondisi.setOID(oid);
            entContentDataKondisi.setNamaKondisi(pstContentDataKondisi.getString(FLD_NAMA_KONDISI));
            entContentDataKondisi.setKodeCoreBanking(pstContentDataKondisi.getString(FLD_KODE_CORE_BANKING));
            entContentDataKondisi.setKodeOjk(pstContentDataKondisi.getString(FLD_KODE_OJK));
            return entContentDataKondisi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKondisi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataKondisi entContentDataKondisi = fetchExc(entity.getOID());
        entity = (Entity) entContentDataKondisi;
        return entContentDataKondisi.getOID();
    }

    public static synchronized long updateExc(ContentDataKondisi entContentDataKondisi) throws DBException {
        try {
            if (entContentDataKondisi.getOID() != 0) {
                PstContentDataKondisi pstContentDataKondisi = new PstContentDataKondisi(entContentDataKondisi.getOID());
                pstContentDataKondisi.setString(FLD_NAMA_KONDISI, entContentDataKondisi.getNamaKondisi());
                pstContentDataKondisi.setString(FLD_KODE_CORE_BANKING, entContentDataKondisi.getKodeCoreBanking());
                pstContentDataKondisi.setString(FLD_KODE_OJK, entContentDataKondisi.getKodeOjk());
                pstContentDataKondisi.update();
                return entContentDataKondisi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKondisi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataKondisi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataKondisi pstContentDataKondisi = new PstContentDataKondisi(oid);
            pstContentDataKondisi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKondisi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataKondisi entContentDataKondisi) throws DBException {
        try {
            PstContentDataKondisi pstContentDataKondisi = new PstContentDataKondisi(0);
            pstContentDataKondisi.setString(FLD_NAMA_KONDISI, entContentDataKondisi.getNamaKondisi());
            pstContentDataKondisi.setString(FLD_KODE_CORE_BANKING, entContentDataKondisi.getKodeCoreBanking());
            pstContentDataKondisi.setString(FLD_KODE_OJK, entContentDataKondisi.getKodeOjk());
            pstContentDataKondisi.insert();
            entContentDataKondisi.setOID(pstContentDataKondisi.getlong(FLD_KONDISI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKondisi(0), DBException.UNKNOWN);
        }
        return entContentDataKondisi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataKondisi) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataKondisi entContentDataKondisi) {
        try {
            entContentDataKondisi.setOID(rs.getLong(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KONDISI_OID]));
            entContentDataKondisi.setNamaKondisi(rs.getString(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_NAMA_KONDISI]));
            entContentDataKondisi.setKodeCoreBanking(rs.getString(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KODE_CORE_BANKING]));
            entContentDataKondisi.setKodeOjk(rs.getString(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    
    public static void resultToObjectTanpaOid(ResultSet rs, ContentDataKondisi entContentDataKondisi) {
        try {
            entContentDataKondisi.setNamaKondisi(rs.getString(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_NAMA_KONDISI]));
            entContentDataKondisi.setKodeOjk(rs.getString(PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KONDISI;
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
                ContentDataKondisi entContentDataKondisi = new ContentDataKondisi();
                resultToObject(rs, entContentDataKondisi);
                lists.add(entContentDataKondisi);
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
    
    
    public static Vector listTanpaOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT NAMA_KONDISI, KODE_OJK  FROM " + TBL_CONTENT_DATA_KONDISI;
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
                ContentDataKondisi entContentDataKondisi = new ContentDataKondisi();
                resultToObjectTanpaOid(rs, entContentDataKondisi);
                lists.add(entContentDataKondisi);
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

    public static boolean checkOID(long entContentDataKondisiId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KONDISI + " WHERE "
                    + PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KONDISI_OID] + " = " + entContentDataKondisiId;
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }

    public static int getCount(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(" + PstContentDataKondisi.fieldNames[PstContentDataKondisi.FLD_KONDISI_OID] + ") FROM " + TBL_CONTENT_DATA_KONDISI;
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
                    ContentDataKondisi entContentDataKondisi = (ContentDataKondisi) list.get(ls);
                    if (oid == entContentDataKondisi.getOID()) {
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
}
