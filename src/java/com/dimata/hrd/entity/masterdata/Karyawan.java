package com.dimata.hrd.entity.masterdata;


import com.dimata.qdep.entity.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author keys
 */
public class Karyawan  extends Entity {
    private long idKaryawan = 0;
    private String nama = "";
    private int umur = 0;
    private String alamat = "";
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
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the umur
     */
    public int getUmur() {
        return umur;
    }

    /**
     * @param umur the umur to set
     */
    public void setUmur(int umur) {
        this.umur = umur;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
}
