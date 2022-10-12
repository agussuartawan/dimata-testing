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

public class MapSyaratPendidikanJabatan extends Entity {

private long idMapPendidikanJabatan = 0;
private long idJabatan = 0;
private String pendidikanMin = "";
private String pendidikanRek = "";

public long getIdMapPendidikanJabatan(){
return idMapPendidikanJabatan;
}

public void setIdMapPendidikanJabatan(long idMapPendidikanJabatan){
this.idMapPendidikanJabatan = idMapPendidikanJabatan;
}

public long getIdJabatan(){
return idJabatan;
}

public void setIdJabatan(long idJabatan){
this.idJabatan = idJabatan;
}

public String getPendidikanMin(){
return pendidikanMin;
}

public void setPendidikanMin(String pendidikanMin){
this.pendidikanMin = pendidikanMin;
}

public String getPendidikanRek(){
return pendidikanRek;
}

public void setPendidikanRek(String pendidikanRek){
this.pendidikanRek = pendidikanRek;
}

}
