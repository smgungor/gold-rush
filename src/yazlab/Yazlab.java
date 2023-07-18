
package yazlab;

import java.io.IOException;
import java.util.Scanner;


public class Yazlab {

    public static void main(String[] args) throws InterruptedException, IOException {
        GirisEkrani giris = new GirisEkrani();
        giris.setVisible(true);

        
        while (true) {
            System.out.println("");
            if(giris.isCheck() == false){
                System.out.println("");
                break;
            }
        }

        OyunEkrani oyun = new OyunEkrani(giris.getX_ekseni(), giris.getY_ekseni(), giris.getAltinSayisi(), giris.getAdimSayisi());
       /* int x=20,y=20,altin=200,ayar,don=0,adim=3;
        GirisEkrani giris = new GirisEkrani();
        Scanner scan = new Scanner(System.in);
        System.out.println("Varsayılan ayarlada değişiklik yapmak ister misiniz(1-evet 2-hayır):");
        ayar = scan.nextInt();
        if(ayar==2){
           /* A oyuncu_a = new A(altin,adim);
            B oyuncu_b = new B(altin,adim,x);
            C oyuncu_c = new C(altin,adim,y);
            D oyuncu_d = new D(altin,adim,x,y);
            OyunEkrani oyun = new OyunEkrani(20,20,altin,adim);
        }
        else if(ayar==1){
            while(don<4){
            System.out.println("Menu:\n"
                    + "1-Oyun ekrani(x,y)\n"
                    + "2-Baslangic altin miktarı\n"
                    + "3-Adım sayisi\n"
                    + "4-Oyunu Başlatmak için");
            don = scan.nextInt();
            if(don==1){
                x = scan.nextInt();
                y = scan.nextInt();
            }
            else if(don==2){
                altin = scan.nextInt();
            }
            else if (don==3){
                adim = scan.nextInt();
            }
            }
           /* A oyuncu_a = new A(altin,adim);
            B oyuncu_b = new B(altin,adim,x);
            C oyuncu_c = new C(altin,adim,y);
            D oyuncu_d = new D(altin,adim,x,y);
            OyunEkrani oyun = new OyunEkrani(x,y,altin,adim);
        }*/
        
    
        
    }
    
}
