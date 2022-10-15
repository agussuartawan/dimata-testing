/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.testing.entity.masterdata;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import org.json.JSONObject;
import java.util.Date;

public class PstCustomer extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_NAME = "customers";
    public static final int FLD_ID = 0;
    public static final int FLD_NAME = 1;
    public static final int FLD_PHONE = 2;
    public static final int FLD_ADDRESS = 3;
    public static final int FLD_CREATED_AT = 4;
    public static final int FLD_UPDATED_AT = 5;

    public static String[] fieldNames = {
            "id",
            "name",
            "phone",
            "address",
            "created_at",
            "updated_at",
    };

    public static int[] fieldTypes = {
            TYPE_LONG + TYPE_PK + TYPE_ID + TYPE_AI,
            TYPE_STRING,
            TYPE_STRING,
            TYPE_STRING,
            TYPE_DATE,
            TYPE_DATE, };

    public PstCustomer() {
    }

    public PstCustomer(int i) throws DBException {
        super(new PstCustomer());
    }

    public PstCustomer(String sOid) throws DBException {
        super(new PstCustomer(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstCustomer(long lOid) throws DBException {
        super(new PstCustomer(0));
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
        return TBL_NAME;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstCustomer().getClass().getName();
    }

    public static Customer fetchExc(long oid) throws DBException {
        try {
            Customer entCustomer = new Customer();
            PstCustomer PstCustomer = new PstCustomer(oid);
            entCustomer.setOID(oid);
            entCustomer.setName(PstCustomer.getString(FLD_NAME));
            entCustomer.setPhone(PstCustomer.getString(FLD_PHONE));
            entCustomer.setAddress(PstCustomer.getString(FLD_ADDRESS));
            entCustomer.setCreatedAt(PstCustomer.getDate(FLD_CREATED_AT));
            entCustomer.setUpdatedAt(PstCustomer.getDate(FLD_UPDATED_AT));
            return entCustomer;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCustomer(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Customer entCustomer = fetchExc(entity.getOID());
        entity = (Entity) entCustomer;
        return entCustomer.getOID();
    }

    public static synchronized long updateExc(Customer entCustomer) throws DBException {
        try {
            if (entCustomer.getOID() != 0) {
                Date date = new Date();
                PstCustomer PstCustomer = new PstCustomer(entCustomer.getOID());
                PstCustomer.setString(FLD_NAME, entCustomer.getName());
                PstCustomer.setString(FLD_PHONE, entCustomer.getPhone());
                PstCustomer.setString(FLD_ADDRESS, entCustomer.getAddress());
                PstCustomer.setDate(FLD_UPDATED_AT, date);
                PstCustomer.update();
                return entCustomer.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCustomer(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Customer) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstCustomer PstCustomer = new PstCustomer(oid);
            PstCustomer.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCustomer(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Customer entCustomer) throws DBException {
        try {
            Date date = new Date();
            PstCustomer PstCustomer = new PstCustomer(0);

            PstCustomer.setString(FLD_NAME, entCustomer.getName());
            PstCustomer.setString(FLD_PHONE, entCustomer.getPhone());
            PstCustomer.setString(FLD_ADDRESS, entCustomer.getAddress());
            PstCustomer.setDate(FLD_CREATED_AT, date);
            PstCustomer.setDate(FLD_UPDATED_AT, date);
            PstCustomer.insert();
            entCustomer.setOID(PstCustomer.getlong(FLD_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstCustomer(0), DBException.UNKNOWN);
        }
        return entCustomer.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Customer) entity);
    }

    public static void resultToObject(ResultSet rs, Customer entCustomer) {
        try {
            entCustomer.setOID(rs.getLong(PstCustomer.fieldNames[PstCustomer.FLD_ID]));
            entCustomer.setName(rs.getString(PstCustomer.fieldNames[PstCustomer.FLD_NAME]));
            entCustomer.setPhone(rs.getString(PstCustomer.fieldNames[PstCustomer.FLD_PHONE]));
            entCustomer.setAddress(rs.getString(PstCustomer.fieldNames[PstCustomer.FLD_ADDRESS]));
            entCustomer.setCreatedAt(rs.getDate(PstCustomer.fieldNames[PstCustomer.FLD_CREATED_AT]));
            entCustomer.setUpdatedAt(rs.getDate(PstCustomer.fieldNames[PstCustomer.FLD_UPDATED_AT]));
        } catch (Exception e) {
        }
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_NAME;
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
                Customer entCustomer = new Customer();
                resultToObject(rs, entCustomer);
                lists.add(entCustomer);
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
            String sql = "SELECT COUNT(" + PstCustomer.fieldNames[PstCustomer.FLD_ID] + ") FROM " + TBL_NAME;
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
                    Customer entCustomer = (Customer) list.get(ls);
                    if (oid == entCustomer.getOID()) {
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
    // Add by Eri Yudi 2020-07-01
    // Method for API

    public static JSONObject fetchJSON(Customer entCustomer) {
        JSONObject object = new JSONObject();
        try {
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_ID], "" + entCustomer.getOID());
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_NAME], "" + entCustomer.getName());
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_PHONE], "" + entCustomer.getPhone());
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_ADDRESS], "" + entCustomer.getAddress());
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_CREATED_AT],
                    (entCustomer.getCreatedAt() != null)
                            ? Formater.formatDate(entCustomer.getCreatedAt(), "yyyy-MM-dd HH:mm:ss")
                            : "0000-00-00 00:00:00");
            object.put(PstCustomer.fieldNames[PstCustomer.FLD_UPDATED_AT],
                    (entCustomer.getUpdatedAt() != null)
                            ? Formater.formatDate(entCustomer.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss")
                            : "0000-00-00 00:00:00");
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return object;
    }

    public static long syncExc(JSONObject jSONObject) throws DBException {
        long oid = 0;
        try {
            oid = Long.valueOf((String) jSONObject.get(PstCustomer.fieldNames[PstCustomer.FLD_ID]));
            Customer entCustomer = PstCustomer.parseJsonObject(jSONObject);
            oid = entCustomer.getOID();
            boolean chekOid = PstCustomer.checkOID(oid);
            if (chekOid) {
                // Doing update
                oid = PstCustomer.updateExc(entCustomer);
            } else {
                // Doing insert
                oid = PstCustomer.insertExc(entCustomer);
            }
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return oid;
    }

    private static Customer parseJsonObject(JSONObject jsonObject) {
        Customer entCustomer = new Customer();
        try {
            entCustomer.setOID(Long.valueOf((String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_ID])));
            entCustomer.setName((String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_NAME]));
            entCustomer.setPhone((String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_NAME]));
            entCustomer.setAddress((String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_NAME]));
            entCustomer.setCreatedAt(Formater.formatDate(
                    (String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_CREATED_AT]),
                    "yyyy-MM-dd HH:mm:ss"));
            entCustomer.setUpdatedAt(Formater.formatDate(
                    (String) jsonObject.get(PstCustomer.fieldNames[PstCustomer.FLD_UPDATED_AT]),
                    "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return entCustomer;
    }

    public static boolean checkOID(long id) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_NAME
                    + " WHERE " + PstCustomer.fieldNames[PstCustomer.FLD_ID]
                    + " = " + id;
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        } finally {
            DBResultSet.close(dbrs);
        }
        return result;
    }

}
