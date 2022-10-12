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

public class MapPendidikan extends Entity {

private long idMapKaryawan = 0;
private long idKaryawan = 0;
private long idPendidikan = 0;
private int tahunMulai = 0;
private int tahunSelesai = 0;

public long getIdMapKaryawan(){
return idMapKaryawan;
}

public void setIdMapKaryawan(long idMapKaryawan){
this.idMapKaryawan = idMapKaryawan;
}

public long getIdKaryawan(){
return idKaryawan;
}

public void setIdKaryawan(long idKaryawan){
this.idKaryawan = idKaryawan;
}

public long getIdPendidikan(){
return idPendidikan;
}

public void setIdPendidikan(long idPendidikan){
this.idPendidikan = idPendidikan;
}

public int getTahunMulai(){
return tahunMulai;
}

public void setTahunMulai(int tahunMulai){
this.tahunMulai = tahunMulai;
}

public int getTahunSelesai(){
return tahunSelesai;
}

public void setTahunSelesai(int tahunSelesai){
this.tahunSelesai = tahunSelesai;
}

}
