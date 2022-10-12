/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.form.masterdata;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.form.Control;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.system.I_DBExceptionInfo;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import static com.dimata.util.lang.I_Language.LANGUAGE_DEFAULT;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author VegaNirmala
 */
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.hrd.entity.masterdata.MapPendidikan;
import com.dimata.hrd.entity.masterdata.PstMapPendidikan;

/*
Description : Controll Mappendidikan
Date : Tue Feb 22 2022
Author : Vega Nirmala
*/

public class CtrlMapPendidikan extends Control implements I_Language {
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
private MapPendidikan entMappendidikan;
private PstMapPendidikan pstMappendidikan;
private FrmMapPendidikan frmMappendidikan;
int language = LANGUAGE_DEFAULT;

public CtrlMapPendidikan(HttpServletRequest request) {
msgString = "";
entMappendidikan = new MapPendidikan();
try {
pstMappendidikan = new PstMapPendidikan(0);
} catch (Exception e) {;
}
frmMappendidikan = new FrmMapPendidikan(request, entMappendidikan);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmMappendidikan.addError(frmMappendidikan.FRM_FIELD_ID_MAP_PENDIDIKAN, resultText[language][RSLT_EST_CODE_EXIST]);
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

public MapPendidikan getMappendidikan() {
return entMappendidikan;
}

public FrmMapPendidikan getForm() {
return frmMappendidikan;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidMappendidikan, long userId, String userName) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidMappendidikan != 0) {
try {
entMappendidikan = PstMapPendidikan.fetchExc(oidMappendidikan);
} catch (Exception exc) {
}
}

frmMappendidikan.requestEntityObject(entMappendidikan);

if (frmMappendidikan.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entMappendidikan.getOID() == 0) {
try {
long oid = pstMappendidikan.insertExc(this.entMappendidikan);
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
long oid = pstMappendidikan.updateExc(this.entMappendidikan);
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
if (oidMappendidikan != 0) {
try {
entMappendidikan = PstMapPendidikan.fetchExc(oidMappendidikan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.ASK:
if (oidMappendidikan != 0) {
try {
entMappendidikan = PstMapPendidikan.fetchExc(oidMappendidikan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidMappendidikan != 0) {
try {
long oid = PstMapPendidikan.deleteExc(oidMappendidikan);
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