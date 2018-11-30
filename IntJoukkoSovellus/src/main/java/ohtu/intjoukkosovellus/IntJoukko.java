
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] intTaulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        intTaulukko = new int[KAPASITEETTI];
        tayta();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        intTaulukko = new int[kapasiteetti];
        tayta();
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        intTaulukko = new int[kapasiteetti];
        tayta();
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public void tayta() {
        for (int i = 0; i < intTaulukko.length; i++) {
            intTaulukko[i] = 0;
        }  
    }
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            intTaulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % intTaulukko.length == 0) {
                int[] taulukkoOld = intTaulukko;
                kopioiVanhanAlkiot(taulukkoOld);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == intTaulukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = luvunIndeksi(luku);
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                intTaulukko[j] = intTaulukko[j + 1];
                intTaulukko[j + 1] = intTaulukko[j];
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }
    
    private int luvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == intTaulukko[i]) {
                intTaulukko[i] = 0;
                return i;
            }
        }
        return -1;
    }

    private void kopioiVanhanAlkiot(int[] vanha) {
        intTaulukko = new int[alkioidenLkm + kasvatuskoko];
        for (int i = 0; i < vanha.length; i++) {
            intTaulukko[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + intTaulukko[0] + "}";
            default:
                return useanAlkionToString();
        }
    }
    
    public String useanAlkionToString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += intTaulukko[i];
            tuotos += ", ";
        }
        tuotos += intTaulukko[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = intTaulukko[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            for (int j = 0; j < b.mahtavuus(); j++) {
                if (a.toIntArray()[i] == b.toIntArray()[j]) {
                    y.lisaa(b.toIntArray()[j]);
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            z.lisaa(a.toIntArray()[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            z.poista(i);
        }
        return z;
    }
        
}