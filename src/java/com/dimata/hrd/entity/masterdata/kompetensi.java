package com.dimata.hrd.entity.masterdata;

import com.dimata.qdep.entity.Entity;

public class kompetensi extends Entity {

    private long idKompetensi = 0;
    private String kompetensi = "";

    public long getIdKompetensi() {
        return idKompetensi;
    }

    public void setIdKompetensi(long idKompetensi) {
        this.idKompetensi = idKompetensi;
    }

    public String getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(String kompetensi) {
        this.kompetensi = kompetensi;
    }

}
