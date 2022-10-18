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

public class PstSaleDetail extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

        public static final String TBL_NAME = "sale_detail";
        public static final int FLD_ID = 0;
        public static final int FLD_SALE_ID = 1;
        public static final int FLD_PRODUCT_ID = 2;
        public static final int FLD_QTY = 3;
        public static final int FLD_PRICE = 4;
        public static final int FLD_SUBTOTAL = 5;

        public static String[] fieldNames = {
                        "id",
                        "sale_id",
                        "product_id",
                        "qty",
                        "price",
                        "subtotal",
        };

        public static int[] fieldTypes = {
                        TYPE_LONG + TYPE_PK + TYPE_ID + TYPE_AI,
                        TYPE_LONG,
                        TYPE_LONG,
                        TYPE_INT,
                        TYPE_FLOAT,
                        TYPE_FLOAT,
        };

        public PstSaleDetail() {
        }

        public PstSaleDetail(int i) throws DBException {
                super(new PstSaleDetail());
        }

        public PstSaleDetail(String sOid) throws DBException {
                super(new PstSaleDetail(0));
                if (!locate(sOid)) {
                        throw new DBException(this, DBException.RECORD_NOT_FOUND);
                } else {
                        return;
                }
        }

        public PstSaleDetail(long lOid) throws DBException {
                super(new PstSaleDetail(0));
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
                return new PstSaleDetail().getClass().getName();
        }

        public static SaleDetail fetchExc(long oid) throws DBException {
                try {
                        SaleDetail entSaleDetail = new SaleDetail();
                        PstSaleDetail pstSaleDetail = new PstSaleDetail(oid);
                        entSaleDetail.setSaleId(oid);
                        entSaleDetail.setProductId(pstSaleDetail.getLong(FLD_PRODUCT_ID));
                        entSaleDetail.setQty(pstSaleDetail.getInt(FLD_QTY));
                        entSaleDetail.setPrice(pstSaleDetail.getfloat(FLD_PRICE));
                        entSaleDetail.setSubtotal(pstSaleDetail.getfloat(FLD_SUBTOTAL));
                        return entSaleDetail;
                } catch (DBException dbe) {
                        throw dbe;
                } catch (Exception e) {
                        throw new DBException(new PstSaleDetail(0), DBException.UNKNOWN);
                }
        }

        public long fetchExc(Entity entity) throws Exception {
                SaleDetail entSaleDetail = fetchExc(entity.getOID());
                entity = (Entity) entSaleDetail;
                return entSaleDetail.getOID();
        }

        public static synchronized long updateExc(SaleDetail entSaleDetail) throws DBException {
                try {
                        if (entSaleDetail.getOID() != 0) {
                                PstSaleDetail pstSaleDetail = new PstSaleDetail(entSaleDetail.getOID());
                                pstSaleDetail.setLong(FLD_SALE_ID, entSaleDetail.getSaleId());
                                pstSaleDetail.setLong(FLD_PRODUCT_ID, entSaleDetail.getProductId());
                                pstSaleDetail.setInt(FLD_QTY, entSaleDetail.getQty());
                                pstSaleDetail.setFloat(FLD_PRICE, entSaleDetail.getPrice());
                                pstSaleDetail.setFloat(FLD_SUBTOTAL, entSaleDetail.getSubtotal());
                                pstSaleDetail.update();
                                return entSaleDetail.getOID();
                        }
                } catch (DBException dbe) {
                        throw dbe;
                } catch (Exception e) {
                        throw new DBException(new PstSaleDetail(0), DBException.UNKNOWN);
                }
                return 0;
        }

        public long updateExc(Entity entity) throws Exception {
                return updateExc((SaleDetail) entity);
        }

        public static synchronized long deleteExc(long oid) throws DBException {
                try {
                        PstSaleDetail pstSaleDetail = new PstSaleDetail(oid);
                        pstSaleDetail.delete();
                } catch (DBException dbe) {
                        throw dbe;
                } catch (Exception e) {
                        throw new DBException(new PstSaleDetail(0), DBException.UNKNOWN);
                }
                return oid;
        }

        public long deleteExc(Entity entity) throws Exception {
                if (entity == null) {
                        throw new DBException(this, DBException.RECORD_NOT_FOUND);
                }
                return deleteExc(entity.getOID());
        }

        public static synchronized long insertExc(SaleDetail entSaleDetail) throws DBException {
                try {
                        PstSaleDetail pstSaleDetail = new PstSaleDetail(0);
                        
                        int qty = entSaleDetail.getQty();
                        float price = entSaleDetail.getPrice();
                        float subtotal = qty * price;
                        float grand_total = 0;
                        String query = "id = '" + entSaleDetail.getSaleId() + "'";
                        Vector listSales = new Vector();
                        listSales = PstSale.list(0, 0, query, "");
                        
                        for(int i = 0; i < listSales.size(); i++){
                            Sale objSale = (Sale) listSales.get(i);
                            grand_total = subtotal + objSale.getGrandTotal();
                        }
                        
                        PstSale pstSale = new PstSale(entSaleDetail.getSaleId());
                        pstSale.setFloat(4, grand_total);
                        pstSale.update();

                        pstSaleDetail.setLong(FLD_SALE_ID, entSaleDetail.getSaleId());
                        pstSaleDetail.setLong(FLD_PRODUCT_ID, entSaleDetail.getProductId());
                        pstSaleDetail.setInt(FLD_QTY, entSaleDetail.getQty());
                        pstSaleDetail.setFloat(FLD_PRICE, entSaleDetail.getPrice());
                        pstSaleDetail.setFloat(FLD_SUBTOTAL, subtotal);
                        pstSaleDetail.insert();
                        
                        entSaleDetail.setOID(pstSaleDetail.getlong(FLD_ID));
                } catch (DBException dbe) {
                        throw dbe;
                } catch (Exception e) {
                        throw new DBException(new PstSaleDetail(0), DBException.UNKNOWN);
                }
                return entSaleDetail.getOID();
        }

        public long insertExc(Entity entity) throws Exception {
                return insertExc((SaleDetail) entity);
        }

        public static void resultToObject(ResultSet rs, SaleDetail entSaleDetail) {
                try {
                        entSaleDetail.setOID(rs.getLong(PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID]));
                        entSaleDetail.setSaleId(rs.getLong(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SALE_ID]));
                        entSaleDetail.setProductId(rs.getLong(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRODUCT_ID]));
                        entSaleDetail.setQty(rs.getInt(PstSaleDetail.fieldNames[PstSaleDetail.FLD_QTY]));
                        entSaleDetail.setPrice(rs.getFloat(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRICE]));
                        entSaleDetail.setSubtotal(rs.getFloat(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SUBTOTAL]));
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
                                SaleDetail entSaleDetail = new SaleDetail();
                                resultToObject(rs, entSaleDetail);
                                lists.add(entSaleDetail);
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

        public static Vector listWithJoinProduct(int limitStart, int recordToGet, String whereClause, String order) {
                Vector lists = new Vector();
                DBResultSet dbrs = null;
                try {
                        String sql = "SELECT sale_detail.*, products.`name` AS product_name FROM sale_detail  \n"
                                        +
                                        "INNER JOIN products \n" +
                                        "ON products.`id` = sale_detail.`product_id` ";
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
                                SaleDetail entSaleDetail = new SaleDetail();
                                entSaleDetail.setOID(rs.getLong(PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID]));
                                // entSaleDetail.setSaleId(rs.getLong(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SALE_ID]));
//                                entSaleDetail.setProductCode(rs.getString("product_code"));
                                entSaleDetail.setProductName(rs.getString("product_name"));
                                entSaleDetail.setQty(rs.getInt(PstSaleDetail.fieldNames[PstSaleDetail.FLD_QTY]));
                                entSaleDetail.setPrice(rs.getFloat(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRICE]));
                                entSaleDetail.setSubtotal(
                                                rs.getFloat(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SUBTOTAL]));
                                lists.add(entSaleDetail);
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

        public static Vector listProduct(int limitStart, int recordToGet, String whereClause, String order) {
                Vector lists = new Vector();
                DBResultSet dbrs = null;
                try {
                        String sql = "SELECT * FROM products ";
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
                                Product entProduct = new Product();
                                entProduct.setOID(rs.getLong("id"));
                                entProduct.setCode(rs.getString("code"));
                                entProduct.setName(rs.getString("name"));
                                entProduct.setStock(rs.getInt("stock"));
                                entProduct.setPrice(rs.getFloat("price"));
                                lists.add(entProduct);
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
                        String sql = "SELECT COUNT(" + PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID] + ") FROM "
                                        + TBL_NAME;
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
                                        SaleDetail entSaleDetail = (SaleDetail) list.get(ls);
                                        if (oid == entSaleDetail.getOID()) {
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

        public static JSONObject fetchJSON(SaleDetail entSaleDetail) {
                JSONObject object = new JSONObject();
                try {
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID], "" + entSaleDetail.getOID());
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SALE_ID], "" + entSaleDetail.getSaleId());
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRODUCT_ID],
                                        "" + entSaleDetail.getProductId());
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_QTY], "" + entSaleDetail.getQty());
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRICE], "" + entSaleDetail.getPrice());
                        object.put(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SUBTOTAL],
                                        "" + entSaleDetail.getSubtotal());
                } catch (Exception exc) {
                        System.out.println("Err :" + exc);
                }
                return object;
        }

        public static long syncExc(JSONObject jSONObject) throws DBException {
                long oid = 0;
                try {
                        oid = Long.valueOf((String) jSONObject.get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID]));
                        SaleDetail entSaleDetail = PstSaleDetail.parseJsonObject(jSONObject);
                        oid = entSaleDetail.getOID();
                        boolean chekOid = PstSaleDetail.checkOID(oid);
                        if (chekOid) {
                                // Doing update
                                oid = PstSaleDetail.updateExc(entSaleDetail);
                        } else {
                                // Doing insert
                                oid = PstSaleDetail.insertExc(entSaleDetail);
                        }
                } catch (Exception exc) {
                        System.out.println("Err :" + exc);
                }
                return oid;
        }

        private static SaleDetail parseJsonObject(JSONObject jsonObject) {
                SaleDetail entSaleDetail = new SaleDetail();
                try {
                        entSaleDetail.setOID(Long.valueOf(
                                        (String) jsonObject.get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID])));
                        entSaleDetail.setSaleId(
                                        (Long) jsonObject.get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SALE_ID]));
                        entSaleDetail.setProductId(
                                        (Long) jsonObject.get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRODUCT_ID]));
                        entSaleDetail.setQty((int) jsonObject
                                        .get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_QTY]));
                        entSaleDetail.setPrice((float) jsonObject
                                        .get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_PRICE]));
                        entSaleDetail.setSubtotal((float) jsonObject
                                        .get(PstSaleDetail.fieldNames[PstSaleDetail.FLD_SUBTOTAL]));

                } catch (Exception exc) {
                        System.out.println("Err :" + exc);
                }
                return entSaleDetail;
        }

        public static boolean checkOID(long id) {
                DBResultSet dbrs = null;
                boolean result = false;
                try {
                        String sql = "SELECT * FROM " + TBL_NAME
                                        + " WHERE " + PstSaleDetail.fieldNames[PstSaleDetail.FLD_ID]
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
