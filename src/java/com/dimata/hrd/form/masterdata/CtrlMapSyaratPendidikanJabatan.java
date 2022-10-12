/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.form.masterdata;

/**
 *
 * @author VegaNirmala
 */
import com.dimata.hrd.entity.masterdata.MapSyaratPendidikanJabatan;
import com.dimata.hrd.entity.masterdata.PstMapSyaratPendidikanJabatan;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
Description : Controll Mapsyaratpendidikanjabatan
Date : Wed Feb 23 2022
Author : VegaNirmala
*/

public class CtrlMapSyaratPendidikanJabatan extends Control implements I_Language {
public static int RSLT_OK = 0;
public static int RSLT_UNKNOWN_ERROR = 1;
public static int RSLT_EST_CODE_EXIST = 2;
public static int RSLT_FORM_INCOMPLETE = 3;
public static String[][] resultText = {
{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
{"Succes", "Can not process", "Estimation code exist", "Data incomplete"}
};
private int start;
private String msgString;
private MapSyaratPendidikanJabatan entMapsyaratpendidikanjabatan;
private PstMapSyaratPendidikanJabatan pstMapsyaratpendidikanjabatan;
private FrmMapSyaratPendidikanJabatan frmMapsyaratpendidikanjabatan;
int language = LANGUAGE_DEFAULT;

public CtrlMapSyaratPendidikanJabatan(HttpServletRequest request) {
msgString = "";
entMapsyaratpendidikanjabatan = new MapSyaratPendidikanJabatan();
try {
pstMapsyaratpendidikanjabatan = new PstMapSyaratPendidikanJabatan(0);
} catch (Exception e) {;
}
frmMapsyaratpendidikanjabatan = new FrmMapSyaratPendidikanJabatan(request, entMapsyaratpendidikanjabatan);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmMapsyaratpendidikanjabatan.addError(frmMapsyaratpendidikanjabatan.FRM_FIELD_ID_MAP_PENDIDIKAN_JABATAN, resultText[language][RSLT_EST_CODE_EXIST]);
return resultText[language][RSLT_EST_CODE_EXIST];
default:
return resultText[language][RSLT_UNKNOWN_ERROR];
}
}

private int getControlMsgId(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
return RSLT_EST_CODE_EXIST;
default:
return RSLT_UNKNOWN_ERROR;
}
}

public int getLanguage() {
return language;
}

public void setLanguage(int language) {
this.language = language;
}

public MapSyaratPendidikanJabatan getMapsyaratpendidikanjabatan() {
return entMapsyaratpendidikanjabatan;
}

public FrmMapSyaratPendidikanJabatan getForm() {
return frmMapsyaratpendidikanjabatan;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidMapsyaratpendidikanjabatan, long userId, String userName) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidMapsyaratpendidikanjabatan != 0) {
try {
entMapsyaratpendidikanjabatan = PstMapSyaratPendidikanJabatan.fetchExc(oidMapsyaratpendidikanjabatan);
} catch (Exception exc) {
}
}

frmMapsyaratpendidikanjabatan.requestEntityObject(entMapsyaratpendidikanjabatan);

if (frmMapsyaratpendidikanjabatan.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entMapsyaratpendidikanjabatan.getOID() == 0) {
try {
long oid = pstMapsyaratpendidikanjabatan.insertExc(this.entMapsyaratpendidikanjabatan);
msgString = FRMMessage.getMessage(FRMMessage.MSG_SAVED);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
return getControlMsgId(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
return getControlMsgId(I_DBExceptionInfo.UNKNOWN);
}

} else {
try {
long oid = pstMapsyaratpendidikanjabatan.updateExc(this.entMapsyaratpendidikanjabatan);
msgString = FRMMessage.getMessage(FRMMessage.MSG_UPDATED);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}

}
break;

case Command.EDIT:
if (oidMapsyaratpendidikanjabatan != 0) {
try {
entMapsyaratpendidikanjabatan = PstMapSyaratPendidikanJabatan.fetchExc(oidMapsyaratpendidikanjabatan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.ASK:
if (oidMapsyaratpendidikanjabatan != 0) {
try {
entMapsyaratpendidikanjabatan = PstMapSyaratPendidikanJabatan.fetchExc(oidMapsyaratpendidikanjabatan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidMapsyaratpendidikanjabatan != 0) {
try {
long oid = PstMapSyaratPendidikanJabatan.deleteExc(oidMapsyaratpendidikanjabatan);
if (oid != 0) {
msgString = FRMMessage.getMessage(FRMMessage.MSG_DELETED);
excCode = RSLT_OK;
} else {
msgString = FRMMessage.getMessage(FRMMessage.ERR_DELETED);
excCode = RSLT_FORM_INCOMPLETE;
}
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

default:

}
return rsCode;
}
}
