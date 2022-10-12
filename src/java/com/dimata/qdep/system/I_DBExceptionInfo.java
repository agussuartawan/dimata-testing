/* Generated by Together */

package com.dimata.qdep.system;

public interface I_DBExceptionInfo {
    
    public int NO_EXCEPTION = 0;
    public int UNKNOWN = 1;
    public int SQL_ERROR = 2;
    public int CONCURRENCY_VIOLATION = 3; 
    public int INDEX_OUT_OF_RANGE = 4;
    public int MULTIPLE_ID = 5;
    public int INVALID_KEY = 6;
    public int NOT_OPEN = 7;
    public int INVALID_DATE = 8;
    public int FIXED_COLUMN = 9;
    public int NO_CONNECTION = 10;
    public int CONVERSION_ERROR = 11;
    public int DRIVER_NOT_FOUND = 12;
    public int INVALID_CLASS = 13;
    public int RECORD_NOT_FOUND = 14;
    public int CONFIG_ERROR = 15;
    public int INVALID_NUMBER = 16;
    public int DEL_RESTRICTED = 17;
    public int FAIL_GENERATE_OID = 18;
    public static int COLUMN_NOT_FOUND = 19; 


    
    public String[] errorMsg = {
        "No Exception", "Unknown error occured.", "SQL error: ", "Record was modified by another user. Cannot update or delete.", "Table or field index out of range.", "Cannot have more than one IDENTITY column per table.", "Invalid number of key values.", "Table not open.", "Date conversion error.", "Cannot modify IDENTITY or TIMESTAMP columns.", "Connection not open.",
        "Data type coversion error.", "JDBC driver not found.", "Class for detail objects not found. Check DBInfo class.", "Record not found.", "Error reading configuration file.",
        "Invalid number format.",
        "Can not delete these data, another data referenced",
        "Failed generation object ID" , "Column Not Found"

    };

}