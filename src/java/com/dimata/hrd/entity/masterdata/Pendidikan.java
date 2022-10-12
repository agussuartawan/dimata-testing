/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.hrd.entity.masterdata;

/**
 *
 * @author VegaNirmala
 */
import com.dimata.qdep.entity.Entity;

public class Pendidikan extends Entity {

private long idPendidikan = 0;
private String pendidikan = "";
private int level = 0;

public long getIdPendidikan(){
return idPendidikan;
}

public void setIdPendidikan(long idPendidikan){
this.idPendidikan = idPendidikan;
}

public String getPendidikan(){
return pendidikan;
}

public void setPendidikan(String pendidikan){
this.pendidikan = pendidikan;
}

public int getLevel(){
return level;
}

public void setLevel(int level){
this.level = level;
}

}
