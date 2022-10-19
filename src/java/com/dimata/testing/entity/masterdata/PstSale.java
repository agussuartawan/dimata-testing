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

public class PstSale extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_NAME = "sales";
    public static final int FLD_ID = 0;
    public static final int FLD_CUSTOMER_ID = 1;
    public static final int FLD_CODE = 2;
    public static final int FLD_DATE = 3;
    public static final int FLD_GRAND_TOTAL = 4;
    public static final int FLD_CREATED_AT = 5;
    public static final int FLD_UPDATED_AT = 6;

    public static String[] fieldNames = {
            "id",
            "customer_id",
            "code",
            "inv_date",
            "grand_total",
            "created_at",
            "updated_at",
    };

    public static int[] fieldTypes = {
            TYPE_LONG + TYPE_PK + TYPE_ID + TYPE_AI,
            TYPE_LONG,
            TYPE_STRING,
            TYPE_DATE,
            TYPE_FLOAT,
            TYPE_DATE,
            TYPE_DATE, };

    public PstSale() {
    }

    public PstSale(int i) throws DBException {
        super(new PstSale());
    }

    public PstSale(String sOid) throws DBException {
        super(new PstSale(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstSale(long lOid) throws DBException {
        super(new PstSale(0));
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
        return new PstSale().getClass().getName();
    }

    public static Sale fetchExc(long oid) throws DBException {
        try {
            Sale entSale = new Sale();
            PstSale pstSale = new PstSale(oid);
            entSale.setOID(oid);
            entSale.setCode(pstSale.getString(FLD_CODE));
            entSale.setCustomerId(pstSale.getLong(FLD_CUSTOMER_ID));
            entSale.setInvDate(pstSale.getString(FLD_DATE));
            entSale.setGrandTotal(pstSale.getfloat(FLD_GRAND_TOTAL));
            entSale.setCreatedAt(pstSale.getDate(FLD_CREATED_AT));
            entSale.setUpdatedAt(pstSale.getDate(FLD_UPDATED_AT));
            return entSale;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSale(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Sale entSale = fetchExc(entity.getOID());
        entity = (Entity) entSale;
        return entSale.getOID();
    }

    public static synchronized long updateExc(Sale entSale) throws DBException {
        try {
            if (entSale.getOID() != 0) {
                Date date = new Date();
                Date updatedDate = new SimpleDateFormat("yyyy-MM-dd").parse(entSale.getInvDate());
                
                PstSale pstSale = new PstSale(entSale.getOID());
                pstSale.setString(FLD_CODE, entSale.getCode());
                pstSale.setLong(FLD_CUSTOMER_ID, entSale.getCustomerId());
                pstSale.setDate(FLD_DATE, updatedDate);
                pstSale.setFloat(FLD_GRAND_TOTAL, entSale.getGrandTotal());
                // pstSale.setDate(FLD_CREATED_AT, entSale.getCreatedAt());
                pstSale.setDate(FLD_UPDATED_AT, date);
                pstSale.update();
                return entSale.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSale(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Sale) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            Vector listSaleDetails = new Vector();
            String query = "sale_id = '" + oid + "'";
            try {
                listSaleDetails = PstSaleDetail.list(0, 0, query, "");
                for(int i = 0; i < listSaleDetails.size(); i++){
                    SaleDetail objSaleDetail = (SaleDetail) listSaleDetails.get(i);
                    PstSaleDetail pstSaleDetail = new PstSaleDetail(objSaleDetail.getOID());
                    pstSaleDetail.delete();
                }
            } catch (Exception exc) {
                System.out.println("Error: "+exc);
            }
            
            PstSale pstSale = new PstSale(oid);
            pstSale.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSale(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Sale entSale) throws DBException {
        try {
            Date date = new Date();
            PstSale pstSale = new PstSale(0);
            Date insertDate = new SimpleDateFormat("yyyy-MM-dd").parse(entSale.getInvDate());
            
            pstSale.setString(FLD_CODE, entSale.getCode());
            pstSale.setLong(FLD_CUSTOMER_ID, entSale.getCustomerId());
            pstSale.setDate(FLD_DATE, insertDate);
            pstSale.setFloat(FLD_GRAND_TOTAL, 0);
            pstSale.setDate(FLD_CREATED_AT, date);
            pstSale.setDate(FLD_UPDATED_AT, date);
            pstSale.insert();
            entSale.setOID(pstSale.getlong(FLD_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstSale(0), DBException.UNKNOWN);
        }
        return entSale.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Sale) entity);
    }

    public static void resultToObject(ResultSet rs, Sale entSale) {
        try {
            entSale.setOID(rs.getLong(PstSale.fieldNames[PstSale.FLD_ID]));
            entSale.setCode(rs.getString(PstSale.fieldNames[PstSale.FLD_CODE]));
            entSale.setCustomerId(rs.getLong(PstSale.fieldNames[PstSale.FLD_CUSTOMER_ID]));
            entSale.setInvDate(rs.getString(PstSale.fieldNames[PstSale.FLD_DATE]));
            entSale.setGrandTotal(rs.getFloat(PstSale.fieldNames[PstSale.FLD_GRAND_TOTAL]));
            entSale.setCreatedAt(rs.getDate(PstSale.fieldNames[PstSale.FLD_CREATED_AT]));
            entSale.setUpdatedAt(rs.getDate(PstSale.fieldNames[PstSale.FLD_UPDATED_AT]));
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
                Sale entSale = new Sale();
                resultToObject(rs, entSale);
                lists.add(entSale);
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

    public static Vector listWithJoinCustomer(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT sales.*, customers.`name` AS customer_name, customers.`address` AS customer_address, customers.phone AS customer_phone FROM sales  \n" +
                    "INNER JOIN customers \n" +
                    "ON customers.`id` = sales.`customer_id` ";
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
                Sale entSale = new Sale();
                entSale.setOID(rs.getLong(PstSale.fieldNames[PstSale.FLD_ID]));
                entSale.setCode(rs.getString(PstSale.fieldNames[PstSale.FLD_CODE]));
                entSale.setCustomerName(rs.getString("customer_name"));
                entSale.setCustomerAddress(rs.getString("customer_address"));
                entSale.setCustomerPhone(rs.getString("customer_phone"));
                entSale.setInvDate(rs.getString(PstSale.fieldNames[PstSale.FLD_DATE]));
                entSale.setGrandTotal(rs.getFloat(PstSale.fieldNames[PstSale.FLD_GRAND_TOTAL]));
                lists.add(entSale);
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

    public static Vector listCustomer(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM customers ";
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
                entCustomer.setOID(rs.getLong("id"));
                entCustomer.setName(rs.getString("name"));
                entCustomer.setPhone(rs.getString("phone"));
                entCustomer.setAddress(rs.getString("address"));
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
            String sql = "SELECT COUNT(" + PstSale.fieldNames[PstSale.FLD_ID] + ") FROM " + TBL_NAME;
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
                    Sale entSale = (Sale) list.get(ls);
                    if (oid == entSale.getOID()) {
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

    public static JSONObject fetchJSON(Sale entSale) {
        JSONObject object = new JSONObject();
        try {
            object.put(PstSale.fieldNames[PstSale.FLD_ID], "" + entSale.getOID());
            object.put(PstSale.fieldNames[PstSale.FLD_CODE], "" + entSale.getCode());
            object.put(PstSale.fieldNames[PstSale.FLD_CUSTOMER_ID], "" + entSale.getCustomerId());
            object.put(PstSale.fieldNames[PstSale.FLD_DATE], "" + entSale.getInvDate());
            object.put(PstSale.fieldNames[PstSale.FLD_GRAND_TOTAL], "" + entSale.getGrandTotal());
            object.put(PstSale.fieldNames[PstSale.FLD_CREATED_AT],
                    (entSale.getCreatedAt() != null)
                            ? Formater.formatDate(entSale.getCreatedAt(), "yyyy-MM-dd HH:mm:ss")
                            : "0000-00-00 00:00:00");
            object.put(PstSale.fieldNames[PstSale.FLD_UPDATED_AT],
                    (entSale.getUpdatedAt() != null)
                            ? Formater.formatDate(entSale.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss")
                            : "0000-00-00 00:00:00");
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return object;
    }

    public static long syncExc(JSONObject jSONObject) throws DBException {
        long oid = 0;
        try {
            oid = Long.valueOf((String) jSONObject.get(PstSale.fieldNames[PstSale.FLD_ID]));
            Sale entSale = PstSale.parseJsonObject(jSONObject);
            oid = entSale.getOID();
            boolean chekOid = PstSale.checkOID(oid);
            if (chekOid) {
                // Doing update
                oid = PstSale.updateExc(entSale);
            } else {
                // Doing insert
                oid = PstSale.insertExc(entSale);
            }
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return oid;
    }

    private static Sale parseJsonObject(JSONObject jsonObject) {
        Sale entSale = new Sale();
        try {
            entSale.setOID(Long.valueOf((String) jsonObject.get(PstSale.fieldNames[PstSale.FLD_ID])));
            entSale.setCode((String) jsonObject.get(PstSale.fieldNames[PstSale.FLD_CODE]));
            entSale.setCustomerId((Long) jsonObject.get(PstSale.fieldNames[PstSale.FLD_CUSTOMER_ID]));
            entSale.setGrandTotal((float) jsonObject.get(PstSale.fieldNames[PstSale.FLD_GRAND_TOTAL]));
            entSale.setInvDate((String) jsonObject.get(PstSale.fieldNames[PstSale.FLD_DATE]));
            entSale.setCreatedAt(Formater.formatDate(
                    (String) jsonObject.get(PstSale.fieldNames[PstSale.FLD_CREATED_AT]), "yyyy-MM-dd HH:mm:ss"));
            entSale.setUpdatedAt(Formater.formatDate(
                    (String) jsonObject.get(PstSale.fieldNames[PstSale.FLD_UPDATED_AT]), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return entSale;
    }

    public static boolean checkOID(long id) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_NAME
                    + " WHERE " + PstSale.fieldNames[PstSale.FLD_ID]
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
