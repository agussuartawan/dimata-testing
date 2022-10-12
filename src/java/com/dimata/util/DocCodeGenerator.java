/* Generated by Together */
/* author : eka */

/***********************************
a code generator that receive document type, document date and counter parameter
will return string of 2 digit year + 2 digit month + "-" + doc code from interface + "-" + counter
return exp : "0302-PO-001"
************************************/

package com.dimata.util;

import java.util.*;
import com.dimata.qdep.entity.*;

public class DocCodeGenerator {

    public static String getDocumentCode(int docType, Date docDate, int counter){
		String strCode = "";
		try{

	        I_PstDocType i_pstDocType = (I_PstDocType)Class.forName(I_DocType.DOCTYPE_CLASSNAME).newInstance();

            String documentCode = i_pstDocType.getDocCode(docType);
        	strCode = getYearMonth(docDate, TYPE_GET_YEAR)+
            	""+getYearMonth(docDate, TYPE_GET_MONTH)+
                "-"+documentCode+
                "-"+getCounter(counter);

        }catch(Exception e){
			System.out.println("Err : "+e.toString());
        }finally{
        	 
        }
        return strCode;
    }

    public static String getDocumentCode(String appCode, int docType, Date docDate, int counter){
		String strCode = "";
		try{

	        I_PstDocType i_pstDocType = (I_PstDocType)Class.forName(I_DocType.DOCTYPE_CLASSNAME).newInstance();

            String documentCode = i_pstDocType.getDocCode(docType);
        	strCode = appCode+"-"+getYearMonth(docDate, TYPE_GET_YEAR)+
            	""+getYearMonth(docDate, TYPE_GET_MONTH)+
                "-"+documentCode+
                "-"+getCounter(counter);

        }catch(Exception e){
			System.out.println("Err : "+e.toString());
        }finally{
        	 
        }
        return strCode;
    }

    public static final int TYPE_GET_YEAR = 0;
    public static final int TYPE_GET_MONTH = 1;

	public static String getYearMonth(Date date, int getType){
        String str = "";
    	try{
    		switch(getType){
    			case TYPE_GET_YEAR :
                    	str = ""+date.getYear();
    					str = str.substring(str.length()-2,str.length());
                	break;

                case TYPE_GET_MONTH :
                    	str = ""+(date.getMonth()+1);
                        if(str.length()!=2)
    						str = "0"+str;
					break;

                default:
    		}

    	}catch(Exception e){
        	System.out.println("Err : "+e.toString());
    	}finally{
        	
    	}
        return str;
    }


    public static String getCounter(int counter){
        String strCounter = "";
        String str = String.valueOf(counter);
		switch(str.length()){
	        case 1 :
				strCounter = "00"+counter;
            	break;
	        case 2 :
	            strCounter = "0"+counter;
            	break;
	        case 3 :
                strCounter = ""+counter;
            	break;
            default:
            	strCounter = ""+counter;
        }
        return strCounter;
    }
}