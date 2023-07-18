package yazlab;


public class Oyuncu {
    private int altin;
    private int adimSayisi;

    public int getAltin() {
        return altin;
    }

    public void setAltin(int altin) {
        this.altin = altin;
    }

    public int getAdimSayisi() {
        return adimSayisi;
    }

    public void setAdimSayisi(int adimSayisi) {
        this.adimSayisi = adimSayisi;
    }
    public Oyuncu(int altin,int adimSayisi){
        this.altin = altin;
        this.adimSayisi =adimSayisi;
    }
    
    
}
