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
public class Viesti {
    
    private String viesti;
    private int viestiId;
    private int ketjuId;
    private String lahettaja;
    private String paivamaara;

    public Viesti(String viesti, int viestiId, int ketjuId, String lahettaja, String paivamaara) {
        this.viesti = viesti;
        this.viestiId = viestiId;
        this.ketjuId = ketjuId;
        this.lahettaja = lahettaja;
        this.paivamaara = paivamaara;
    }
    
    
 

    public String getViesti() {
        return viesti;
    }

    public String getLahettaja() {
        return lahettaja;
    }

    public String getPaivamaara() {
        return paivamaara;
    }
    

    
}

