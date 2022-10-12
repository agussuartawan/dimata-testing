/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.entity.masterdata;

/**
 *
 * @author keys
 */
import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class AppUser extends Entity {


private long userId = 0;
private String loginId = "";
private String password = "";
private String fullName = "";
private String email = "";
private String description = "";
private Date regDate = null;
private Date updateDate = null;
private int userStatus = 0;
private Date lastLoginDate = null;
private String lastLoginIp = "";
private long idKaryawan = 0;
private String namaKaryawan = "";

public long getUserId(){
return userId;
}

public void setUserId(long userId){
this.userId = userId;
}

public String getLoginId(){
return loginId;
}

public void setLoginId(String loginId){
this.loginId = loginId;
}

public String getPassword(){
return password;
}

public void setPassword(String password){
this.password = password;
}

public String getFullName(){
return fullName;
}

public void setFullName(String fullName){
this.fullName = fullName;
}

public String getEmail(){
return email;
}

public void setEmail(String email){
this.email = email;
}

public String getDescription(){
return description;
}

public void setDescription(String description){
this.description = description;
}

public Date getRegDate(){
return regDate;
}

public void setRegDate(Date regDate){
this.regDate = regDate;
}

public Date getUpdateDate(){
return updateDate;
}

public void setUpdateDate(Date updateDate){
this.updateDate = updateDate;
}

public int getUserStatus(){
return userStatus;
}

public void setUserStatus(int userStatus){
this.userStatus = userStatus;
}

public Date getLastLoginDate(){
return lastLoginDate;
}

public void setLastLoginDate(Date lastLoginDate){
this.lastLoginDate = lastLoginDate;
}

public String getLastLoginIp(){
return lastLoginIp;
}

public void setLastLoginIp(String lastLoginIp){
this.lastLoginIp = lastLoginIp;
}


    /**
     * @return the idKaryawan
     */
    public long getIdKaryawan() {
        return idKaryawan;
    }

    /**
     * @param idKaryawan the idKaryawan to set
     */
    public void setIdKaryawan(long idKaryawan) {
        this.idKaryawan = idKaryawan;
    }
    
        /**
     * @return the namaKaryawan
     */
    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    /**
     * @param namaKaryawan the namaKaryawan to set
     */
    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }



}