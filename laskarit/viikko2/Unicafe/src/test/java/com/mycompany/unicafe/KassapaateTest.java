
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassapäätteenRahamääräJaMyytyjenLounaidenMääräAlussaOikea() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoKunRahatRiittavatEdullinen() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoKunRahatRiittavatJaPalautetaanVaihtorahatEdullinen() {
        assertEquals(760, paate.syoEdullisesti(1000));
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoKunRahatEivatRiitaJaMyytyjenLounaidenMaaraEiMuutuEdullinen() {
        assertEquals(230, paate.syoEdullisesti(230));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoKunRahatRiittavatMaukas() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoKunRahatRiittavatJaPalautetaanVaihtorahatMaukas() {
        assertEquals(600, paate.syoMaukkaasti(1000));
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoKunRahatEivatRiitaJaMyytyjenLounaidenMaaraEiMuutuMaukas() {
        assertEquals(390, paate.syoMaukkaasti(390));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void molempienLounastyyppienMyyntiMuuttaaMyytyjenMaaraa() {
        paate.syoEdullisesti(240);
        paate.syoMaukkaasti(400);
        assertEquals(2, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiKunKortillaOnTarpeeksiRahaaEdullinen() {
        paate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaOnnistuessaanTrue() {
        assertTrue(paate.syoEdullisesti(kortti) == true);
    }
    
    @Test
    public void korttiostoEiToimiKunKortillaEiOleTarpeeksiRahaaEdullinen() {
        Maksukortti vajaaKortti = new Maksukortti(230);
        paate.syoEdullisesti(vajaaKortti);
        assertEquals(230, vajaaKortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaEpaonnistuessaFalse() {
        Maksukortti vajaaKortti = new Maksukortti(230);
        assertTrue(paate.syoEdullisesti(vajaaKortti) == false);
    }
    
    @Test
    public void korttiostoToimiiKunKortillaOnTarpeeksiRahaaMaukas() {
        paate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiToimiKunKortillaEiOleTarpeeksiRahaaMaukas() {
        Maksukortti vajaaKortti = new Maksukortti(390);
        paate.syoMaukkaasti(vajaaKortti);
        assertEquals(390, vajaaKortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaOnnistuessaanTrueMaukas() {
        assertTrue(paate.syoMaukkaasti(kortti) == true);
    }
    
    @Test
    public void korttiostoPalauttaaEpaonnistuessaFalseMaukas() {
        Maksukortti vajaaKortti = new Maksukortti(390);
        assertTrue(paate.syoMaukkaasti(vajaaKortti) == false);
    }
    
    @Test
    public void kortilleLataaminenKasvattaaKassanSummaa() {
        paate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, paate.kassassaRahaa());
    }
    
    @Test
    public void negatiivinenSummaLadattaessaEiMuutaKassaa() {
        paate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }

}
