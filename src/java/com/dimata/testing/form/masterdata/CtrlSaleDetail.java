/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.testing.form.masterdata;

/**
 *
 * @author keys
 */
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.testing.entity.masterdata.SaleDetail;
import com.dimata.testing.entity.masterdata.PstSaleDetail;

/*
Description : Controll SaleDetail
Date : Tue Feb 15 2022
Author : Eri
 */
public class CtrlSaleDetail extends Control implements I_Language {

        public static int RSLT_OK = 0;
        public static int RSLT_UNKNOWN_ERROR = 1;
        public static int RSLT_EST_CODE_EXIST = 2;
        public static int RSLT_FORM_INCOMPLETE = 3;
        public static String[][] resultText = {
                        // {"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak
                        // lengkap"},
                        { "Succes", "Can not process", "Estimation code exist", "Data incomplete" }
        };
        private int start;
        private String msgString;
        private SaleDetail entSaleDetail;
        private PstSaleDetail pstSaleDetail;
        private FrmSaleDetail frmSaleDetail;
        int language = LANGUAGE_DEFAULT;

        public CtrlSaleDetail(HttpServletRequest request) {
                msgString = "";
                entSaleDetail = new SaleDetail();
                try {
                        pstSaleDetail = new PstSaleDetail(0);
                } catch (Exception e) {
                        ;
                }
                frmSaleDetail = new FrmSaleDetail(request, entSaleDetail);
        }

        private String getSystemMessage(int msgCode) {
                switch (msgCode) {
                        case I_DBExceptionInfo.MULTIPLE_ID:
                                this.frmSaleDetail.addError(frmSaleDetail.FRM_FIELD_ID,
                                                resultText[language][RSLT_EST_CODE_EXIST]);
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

        public SaleDetail getSaleDetail() {
                return entSaleDetail;
        }

        public FrmSaleDetail getForm() {
                return frmSaleDetail;
        }

        public String getMessage() {
                return msgString;
        }

        public int getStart() {
                return start;
        }

        public int action(int cmd, long oidSaleDetail, long id, String name) {
                msgString = "";
                int excCode = I_DBExceptionInfo.NO_EXCEPTION;
                int rsCode = RSLT_OK;
                switch (cmd) {
                        case Command.ADD:
                                break;

                        case Command.SAVE:
                                if (oidSaleDetail != 0) {
                                        try {
                                                entSaleDetail = PstSaleDetail.fetchExc(oidSaleDetail);
                                        } catch (Exception exc) {
                                        }
                                }

                                frmSaleDetail.requestEntityObject(entSaleDetail);

                                if (frmSaleDetail.errorSize() > 0) {
                                        msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                                        return RSLT_FORM_INCOMPLETE;
                                }

                                if (entSaleDetail.getOID() == 0) {
                                        try {
                                                long oid = pstSaleDetail.insertExc(this.entSaleDetail);
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
                                                long oid = pstSaleDetail.updateExc(this.entSaleDetail);
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
                                if (oidSaleDetail != 0) {
                                        try {
                                                entSaleDetail = PstSaleDetail.fetchExc(oidSaleDetail);
                                        } catch (DBException dbexc) {
                                                excCode = dbexc.getErrorCode();
                                                msgString = getSystemMessage(excCode);
                                        } catch (Exception exc) {
                                                msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                                        }
                                }
                                break;

                        case Command.ASK:
                                if (oidSaleDetail != 0) {
                                        try {
                                                entSaleDetail = PstSaleDetail.fetchExc(oidSaleDetail);
                                        } catch (DBException dbexc) {
                                                excCode = dbexc.getErrorCode();
                                                msgString = getSystemMessage(excCode);
                                        } catch (Exception exc) {
                                                msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                                        }
                                }
                                break;

                        case Command.DELETE:
                                if (oidSaleDetail != 0) {
                                        try {
                                                long oid = PstSaleDetail.deleteExc(oidSaleDetail);
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
