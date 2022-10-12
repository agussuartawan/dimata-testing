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
import com.dimata.hrd.entity.masterdata.Pendidikan;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
//import com.dimata.harisma.entity.masterdata.*;
import com.dimata.hrd.entity.masterdata.PstPendidikan;

/*
Description : Controll Pendidikan
Date : Mon Feb 21 2022
Author : Vega Nirmala
*/

public class CtrlPendidikan extends Control implements I_Language {
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
private Pendidikan entPendidikan;
private PstPendidikan pstPendidikan;
private FrmPendidikan frmPendidikan;
int language = LANGUAGE_DEFAULT;

public CtrlPendidikan(HttpServletRequest request) {
msgString = "";
entPendidikan = new Pendidikan();
try {
pstPendidikan = new PstPendidikan(0);
} catch (Exception e) {;
}
frmPendidikan = new FrmPendidikan(request, entPendidikan);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmPendidikan.addError(frmPendidikan.FRM_FIELD_ID_PENDIDIKAN, resultText[language][RSLT_EST_CODE_EXIST]);
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

public Pendidikan getPendidikan() {
return entPendidikan;
}

public FrmPendidikan getForm() {
return frmPendidikan;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidPendidikan, long userId, String userName) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidPendidikan != 0) {
try {
entPendidikan = PstPendidikan.fetchExc(oidPendidikan);
} catch (Exception exc) {
}
}

frmPendidikan.requestEntityObject(entPendidikan);

if (frmPendidikan.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entPendidikan.getOID() == 0) {
try {
long oid = pstPendidikan.insertExc(this.entPendidikan);
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
long oid = pstPendidikan.updateExc(this.entPendidikan);
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
if (oidPendidikan != 0) {
try {
entPendidikan = PstPendidikan.fetchExc(oidPendidikan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.ASK:
if (oidPendidikan != 0) {
try {
entPendidikan = PstPendidikan.fetchExc(oidPendidikan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidPendidikan != 0) {
try {
long oid = PstPendidikan.deleteExc(oidPendidikan);
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
