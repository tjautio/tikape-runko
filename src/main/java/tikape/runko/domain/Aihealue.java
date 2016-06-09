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
public class Aihealue {
    
    private int alueId;
    private String nimi;

    public Aihealue(int alueId, String nimi) {
        this.alueId = alueId;
        this.nimi = nimi;
    }

    public int getAlueId() {
        return alueId;
    }

    public String getNimi() {
        return nimi;
    }
    
}
