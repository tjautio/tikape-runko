/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

/**
 *
 * @author Daniel
 */
public class Ketju {
    
    private int ketjuId;
    private String otsikko;
    private int alueId;
    

    public Ketju(int ketjuId, String otsikko, int alueId) {
        this.otsikko = otsikko;
        this.alueId = alueId;
        this.ketjuId = ketjuId;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public int getAlueId() {
        return alueId;
    }

    public int getKetjuId() {
        return ketjuId;
    }
    
    
}

