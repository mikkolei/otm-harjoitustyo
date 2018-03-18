package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void rahatRiittivätMaukkaaseen() {
        assertTrue(kortti.otaRahaa(400) == true);
    }
    
    @Test
    public void rahatRiittivätEdulliseen() {
        assertTrue(kortti.otaRahaa(240) == true);
    }
    
    @Test
    public void rahatEivätRiitäPalauttaaFalse() {
        kortti.otaRahaa(400);
        kortti.otaRahaa(400);
        assertTrue(kortti.otaRahaa(240) == false);
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(1500);
        assertEquals("saldo: 25.0", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.otaRahaa(240);
        assertEquals("saldo: 7.60", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.otaRahaa(400);
        assertEquals("saldo: 6.0", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(400);
        kortti.otaRahaa(400);
        kortti.otaRahaa(240);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(400);
        kortti.otaRahaa(400);
        kortti.otaRahaa(400);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
}
