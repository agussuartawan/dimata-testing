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
import java.util.Vector;
import org.json.JSONObject;

public class PstProduct extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_PRODUCTS = "products";
    public static final int FLD_ID = 0;
    public static final int FLD_CODE = 1;
    public static final int FLD_NAME = 2;
    public static final int FLD_STOCK = 3;
    public static final int FLD_PRICE = 4;
    public static final int FLD_CREATED_AT = 5;
    public static final int FLD_UPDATED_AT = 6;

    public static String[] fieldNames = {
        "id",
        "code",
        "name",
        "stock",
        "price",
        "created_at",
        "updated_at",
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID + TYPE_AI,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_FLOAT,
        TYPE_DATE,
        TYPE_DATE,};

    public PstProduct() {
    }

    public PstProduct(int i) throws DBException {
        super(new PstProduct());
    }

    public PstProduct(String sOid) throws DBException {
        super(new PstProduct(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstProduct(long lOid) throws DBException {
        super(new PstProduct(0));
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
        return TBL_PRODUCTS;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstProduct().getClass().getName();
    }

    public static Product fetchExc(long oid) throws DBException {
        try {
            Product entProduct = new Product();
            PstProduct pstProduct = new PstProduct(oid);
            entProduct.setOID(oid);
            entProduct.setCode(pstProduct.getString(FLD_CODE));
            entProduct.setName(pstProduct.getString(FLD_NAME));
            entProduct.setStock(pstProduct.getInt(FLD_STOCK));
            entProduct.setPrice(pstProduct.getInt(FLD_PRICE));
            entProduct.setCreatedAt(pstProduct.getDate(FLD_CREATED_AT));
            entProduct.setUpdatedAt(pstProduct.getDate(FLD_UPDATED_AT));
            return entProduct;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstProduct(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Product entProduct = fetchExc(entity.getOID());
        entity = (Entity) entProduct;
        return entProduct.getOID();
    }

    public static synchronized long updateExc(Product entProduct) throws DBException {
        try {
            if (entProduct.getOID() != 0) {
                PstProduct pstProduct = new PstProduct(entProduct.getOID());
                pstProduct.setLong(FLD_ID, entProduct.getId());
                pstProduct.setString(FLD_CODE, entProduct.getCode());
                pstProduct.setString(FLD_NAME, entProduct.getName());
                pstProduct.setInt(FLD_STOCK, (int) entProduct.getStock());
                pstProduct.setFloat(FLD_PRICE, entProduct.getPrice());
                pstProduct.setDate(FLD_CREATED_AT, entProduct.getCreatedAt());
                pstProduct.setDate(FLD_UPDATED_AT, entProduct.getUpdatedAt());
                pstProduct.update();
                return entProduct.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstProduct(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Product) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstProduct pstProduct = new PstProduct(oid);
            pstProduct.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstProduct(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Product entProduct) throws DBException {
        try {
            PstProduct pstProduct = new PstProduct(0);
            pstProduct.setString(FLD_CODE, entProduct.getCode());
            pstProduct.setString(FLD_NAME, entProduct.getName());
            pstProduct.setInt(FLD_STOCK, (int) entProduct.getStock());
            pstProduct.setFloat(FLD_PRICE, entProduct.getPrice());
            pstProduct.setDate(FLD_CREATED_AT, entProduct.getCreatedAt());
            pstProduct.setDate(FLD_UPDATED_AT, entProduct.getUpdatedAt());
            pstProduct.insert();
            entProduct.setOID(pstProduct.getlong(FLD_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstProduct(0), DBException.UNKNOWN);
        }
        return entProduct.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Product) entity);
    }

    public static void resultToObject(ResultSet rs, Product entProduct) {
        try {
            entProduct.setOID(rs.getLong(PstProduct.fieldNames[PstProduct.FLD_ID]));
            entProduct.setCode(rs.getString(PstProduct.fieldNames[PstProduct.FLD_CODE]));
            entProduct.setName(rs.getString(PstProduct.fieldNames[PstProduct.FLD_NAME]));
            entProduct.setStock(rs.getInt(PstProduct.fieldNames[PstProduct.FLD_STOCK]));
            entProduct.setPrice(rs.getFloat(PstProduct.fieldNames[PstProduct.FLD_PRICE]));
            entProduct.setCreatedAt(rs.getDate(PstProduct.fieldNames[PstProduct.FLD_CREATED_AT]));
            entProduct.setUpdatedAt(rs.getDate(PstProduct.fieldNames[PstProduct.FLD_UPDATED_AT]));
        } catch (Exception e) {
        }
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_PRODUCTS;
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
                resultToObject(rs, entProduct);
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

    //  public static Vector listWithJoinSales(int limitStart, int recordToGet, String whereClause, String order) {
    //      Vector lists = new Vector();
    //      DBResultSet dbrs = null;
    //      try {
    //          String sql = "SELECT products.*,sale_detail.* FROM products  \n" +
    //                      "INNER JOIN sale_detail \n" +
    //                      "ON sale_detail.`product_id` = product.`id` " ;
    //           if (whereClause != null && whereClause.length() > 0) {
    //              sql = sql + " WHERE " + whereClause;
    //          }
    //          if (order != null && order.length() > 0) {
    //              sql = sql + " ORDER BY " + order;
    //          }
    //          if (limitStart == 0 && recordToGet == 0) {
    //              sql = sql + "";
    //          } else {
    //              sql = sql + " LIMIT " + limitStart + "," + recordToGet;
    //          }
    //          dbrs = DBHandler.execQueryResult(sql);
    //          ResultSet rs = dbrs.getResultSet();
    //          while (rs.next()) {
    //              Product entProduct = new Product();
    //                  entProduct.setOID(rs.getLong(PstProduct.fieldNames[PstProduct.FLD_ID]));
    //                  entProduct.setCode(rs.getString(PstProduct.fieldNames[PstProduct.FLD_CODE]));
    //                  entProduct.setPassword(rs.getString(PstProduct.fieldNames[PstProduct.FLD_NAME]));
    //                  entProduct.setFullName(rs.getString(PstProduct.fieldNames[PstProduct.FLD_STOCK]));
    //                  entProduct.setEmail(rs.getString(PstProduct.fieldNames[PstProduct.FLD_PRICE]));
    //                  entProduct.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
    //              lists.add(entProduct);
    //          }
    //          rs.close();
    //          return lists;
    //      } catch (Exception e) {
    //          System.out.println(e);
    //      } finally {
    //          DBResultSet.close(dbrs);
    //      }
    //      return new Vector();
    //  }
    //  public static Vector listKaryawan(int limitStart, int recordToGet, String whereClause, String order) {
    //      Vector lists = new Vector();
    //      DBResultSet dbrs = null;
    //      try {
    //          String sql = "SELECT * FROM karyawan " ;
    //          if (whereClause != null && whereClause.length() > 0) {
    //              sql = sql + " WHERE " + whereClause;
    //          }
    //          if (order != null && order.length() > 0) {
    //              sql = sql + " ORDER BY " + order;
    //          }
    //          if (limitStart == 0 && recordToGet == 0) {
    //              sql = sql + "";
    //          } else {
    //              sql = sql + " LIMIT " + limitStart + "," + recordToGet;
    //          }
    //          dbrs = DBHandler.execQueryResult(sql);
    //          ResultSet rs = dbrs.getResultSet();
    //          while (rs.next()) {
    //              Karyawan entKaryawan= new Karyawan();
    //              entKaryawan.setOID(rs.getLong("ID_KARYAWAN"));
    //              entKaryawan.setNama(rs.getString("NAMA"));
    //              entKaryawan.setUmur(rs.getInt("UMUR"));
    //              entKaryawan.setAlamat(rs.getString("ALAMAT"));
    //              lists.add(entKaryawan);
    //          }
    //          rs.close();
    //          return lists;
    //      } catch (Exception e) {
    //          System.out.println(e);
    //      } finally {
    //          DBResultSet.close(dbrs);
    //      }
    //      return new Vector();
    //  }
    public static int getCount(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(" + PstProduct.fieldNames[PstProduct.FLD_ID] + ") FROM " + TBL_PRODUCTS;
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
                    Product entProduct = (Product) list.get(ls);
                    if (oid == entProduct.getOID()) {
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

    public static JSONObject fetchJSON(Product entProduct) {
        JSONObject object = new JSONObject();
        try {
            object.put(PstProduct.fieldNames[PstProduct.FLD_ID], "" + entProduct.getOID());
            object.put(PstProduct.fieldNames[PstProduct.FLD_CODE], "" + entProduct.getCode());
            object.put(PstProduct.fieldNames[PstProduct.FLD_NAME], "" + entProduct.getName());
            object.put(PstProduct.fieldNames[PstProduct.FLD_STOCK], "" + entProduct.getStock());
            object.put(PstProduct.fieldNames[PstProduct.FLD_PRICE], "" + entProduct.getPrice());
            object.put(PstProduct.fieldNames[PstProduct.FLD_CREATED_AT], (entProduct.getCreatedAt() != null) ? Formater.formatDate(entProduct.getCreatedAt(), "yyyy-MM-dd HH:mm:ss") : "0000-00-00 00:00:00");
            object.put(PstProduct.fieldNames[PstProduct.FLD_UPDATED_AT], (entProduct.getUpdatedAt() != null) ? Formater.formatDate(entProduct.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss") : "0000-00-00 00:00:00");
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return object;
    }

    public static long syncExc(JSONObject jSONObject) throws DBException {
        long oid = 0;
        try {
            oid = Long.valueOf((String) jSONObject.get(PstProduct.fieldNames[PstProduct.FLD_ID]));
            Product entProduct = PstProduct.parseJsonObject(jSONObject);
            oid = entProduct.getOID();
            boolean chekOid = PstProduct.checkOID(oid);
            if (chekOid) {
                // Doing update
                oid = PstProduct.updateExc(entProduct);
            } else {
                // Doing insert
                oid = PstProduct.insertExc(entProduct);
            }
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return oid;
    }

    private static Product parseJsonObject(JSONObject jsonObject) {
        Product entProduct = new Product();
        try {
            entProduct.setOID(Long.valueOf((String) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_ID])));
            entProduct.setName((String) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_NAME]));
            entProduct.setStock((Integer) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_STOCK]));
            entProduct.setPrice((float) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_PRICE]));
            entProduct.setCreatedAt(Formater.formatDate((String) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_CREATED_AT]), "yyyy-MM-dd HH:mm:ss"));
            entProduct.setUpdatedAt(Formater.formatDate((String) jsonObject.get(PstProduct.fieldNames[PstProduct.FLD_UPDATED_AT]), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception exc) {
            System.out.println("Err :" + exc);
        }
        return entProduct;
    }

    public static boolean checkOID(long id) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PRODUCTS
                    + " WHERE " + PstProduct.fieldNames[PstProduct.FLD_ID]
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
