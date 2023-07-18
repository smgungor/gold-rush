/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class OyunEkrani{
    
    ImageIcon coin= new ImageIcon("src//resimler//coin.png");
    ImageIcon coin5= new ImageIcon("src//resimler//coin5.png");
    ImageIcon coin10= new ImageIcon("src//resimler//coin10.png");
    ImageIcon coin15= new ImageIcon("src//resimler//coin15.png");
    ImageIcon coin20= new ImageIcon("src//resimler//coin20.png");
    ImageIcon A= new ImageIcon("src//resimler//A.png");
    ImageIcon B= new ImageIcon("src//resimler//B.png");
    ImageIcon C= new ImageIcon("src//resimler//C.png");
    ImageIcon D= new ImageIcon("src//resimler//D.png");
    ImageIcon g = new ImageIcon("src//resimler//coing.png");
    GirisEkrani gir = new GirisEkrani();
    JFrame frame;
    Butonlar[][] board;
    int row_tut,col_tut;
    PlayerA oyuncu_a; 
    PlayerB oyuncu_b; 
    PlayerC oyuncu_c; 
    PlayerD oyuncu_d;
    private int baslangic;
    private int altinSayisi;
    private int adim;
    private int tut_x = 0,tut_y=0;
    private int tut_xb = 0, tut_yb = 0;
    private int tut_xc = 0 , tut_yc = 0;
    private int tut_xd = 0, tut_yd =0;
    private int a_tur_sayac = 0, b_tur_sayac = 0, c_tur_sayac = 0, d_tur_sayac=0;
    private int a_atilan_adim=0,b_atilan_adim=0,c_atilan_adim=0,d_atilan_adim=0;
    private int a_harcanan_altin=0,b_harcanan_altin=0,c_harcanan_altin=0,d_harcanan_altin=0;
    
    File filea;
    File fileb;
    File filec;
    File filed;
    
    
    FileWriter fWritera;
    BufferedWriter bWritera;
    FileWriter fWriterb;
    BufferedWriter bWriterb;
    FileWriter fWriterc;
    BufferedWriter bWriterc;
    FileWriter fWriterd;
    BufferedWriter bWriterd;
    public OyunEkrani(int satir,int sutun,int altin, int adim) throws InterruptedException, IOException{
        filea = new File("A haraket.txt");
        if(!filea.exists()){
            filea.createNewFile();
        }
        fileb = new File("B haraket.txt");
        if(!fileb.exists()){
            fileb.createNewFile();
        }
        filec = new File("C haraket.txt");
        if(!filec.exists()){
            filec.createNewFile();
        }
        filed = new File("D haraket.txt");
        if(!filed.exists()){
            filed.createNewFile();
        }
        fWritera = new FileWriter(filea,false);
        bWritera =  new BufferedWriter(fWritera);
        
        fWriterb = new FileWriter(fileb,false);
        bWriterb = new BufferedWriter(fWriterb);
        
        fWriterc = new FileWriter(filec,false);
        bWriterc = new BufferedWriter(fWriterc);
        
        fWriterd = new FileWriter(filed,false);
        bWriterd = new BufferedWriter(fWriterd);
        
        baslangic = altin;
        oyuncu_a = new PlayerA(altin,adim);
        oyuncu_b = new PlayerB(altin,adim,satir);
        oyuncu_c = new PlayerC(altin,adim,sutun);
        oyuncu_d = new PlayerD(altin,adim,satir,sutun);
        board = new Butonlar[satir][sutun];
        frame = new JFrame("Oyun");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(satir,sutun));
        this.adim = adim;
        for(int row = 0; row <board.length; row++){
            for(int col = 0 ; col<board[0].length; col++){
                Butonlar b = new Butonlar(row, col);
                frame.add(b);
                board[row][col]=b;
                col_tut=col+1;
            }
            row_tut=row+1;
        }
        altinKoy(); 
        print();
        gizliAltinKoy();
        print2();
        
        A_baslangic();
        B_baslangic();
        C_baslangic();
        D_baslangic();
        
        
        
        //oyun();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        oyun();
        
    }
    public void altinKoy(){      
        int i=0;
        for(;i<row_tut;i++){
            for(int j =0;j<col_tut;j++){
                board[i][j].setAltin(0);
                board[i][j].setBasildimi(false);
                board[i][j].setGizlimi(false);
            }
        }
        i=0;
        altinSayisi = ((row_tut*col_tut)*gir.getAltin_koy())/100;
        int altinSayisi2 = altinSayisi - (altinSayisi*gir.getGizli_koy())/100;
        while(i<altinSayisi2){
            int rastgele_row = (int) (Math.random() * board.length);
            int rastgele_col = (int) (Math.random() * board[0].length);
            while(board[rastgele_row][rastgele_col].getAltin()>0 || (rastgele_row == 0 && rastgele_col == 0) ||(rastgele_row == board.length-1 && rastgele_col == 0) || (rastgele_row == board.length-1 && rastgele_col == board[0].length-1) || (rastgele_row == 0 && rastgele_col == board[0].length-1)){
                rastgele_row = (int) (Math.random() * board.length);
                rastgele_col = (int) (Math.random() * board[0].length);
            }
            int rastgele_miktar = (int) (Math.random() * 4);
            int rastgele_amiktar = (rastgele_miktar*5)+5; 
            board[rastgele_row][rastgele_col].setAltin(rastgele_amiktar);
            board[rastgele_row][rastgele_col].setBasildimi(true);
            i++;
        }
    }
    
    public void gizliAltinKoy(){      
        int i=0;
        int gizliAltinSayisi = ((altinSayisi)*gir.getGizli_koy())/100;
        while(i<gizliAltinSayisi){
            int rastgele_row = (int) (Math.random() * board.length);
            int rastgele_col = (int) (Math.random() * board[0].length);
            while(board[rastgele_row][rastgele_col].getAltin()>0 || (rastgele_row == 0 && rastgele_col == 0) ||(rastgele_row == board.length-1 && rastgele_col == 0) || (rastgele_row == board.length-1 && rastgele_col == board[0].length-1) || (rastgele_row == 0 && rastgele_col == board[0].length-1)){
                rastgele_row = (int) (Math.random() * board.length);
                rastgele_col = (int) (Math.random() * board[0].length);
            }
            int rastgele_miktar = (int) (Math.random() * 4);
            int rastgele_amiktar = (rastgele_miktar*5)+5; 
            board[rastgele_row][rastgele_col].setAltin(rastgele_amiktar);
            board[rastgele_row][rastgele_col].setGizlimi(true);
            i++;
        }
    }
    
    
    public void print(){
        for(int row = 0; row <board.length; row++){
            for(int col = 0 ; col<board[0].length; col++){
               if(board[row][col].getAltin()>0){
                   if(board[row][col].getAltin() == 5){
                       board[row][col].setIcon(coin5);
                   }
                   else if(board[row][col].getAltin() == 10){
                       board[row][col].setIcon(coin10);
                   }
                   else if(board[row][col].getAltin() == 15){
                       board[row][col].setIcon(coin15);
                   }
                   else if(board[row][col].getAltin() == 20){
                       board[row][col].setIcon(coin20);
                   }
                   
               }
            }
        }
    }
    public void print2(){
        for(int row = 0; row <board.length; row++){
            for(int col = 0 ; col<board[0].length; col++){
               if(board[row][col].getAltin()>0 && !board[row][col].getBasildimi()){
                   if(board[row][col].getAltin() == 5){
                       board[row][col].setIcon(g);
                   }
                   else if(board[row][col].getAltin() == 10){
                       board[row][col].setIcon(g);
                   }
                   else if(board[row][col].getAltin() == 15){
                       board[row][col].setIcon(g);
                   }
                   else if(board[row][col].getAltin() == 20){
                       board[row][col].setIcon(g);
                   }
               }
            }
        }
    }
    public void A_baslangic(){
        board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(A);
    }
    public void B_baslangic(){
        board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(B);
    }
    public void C_baslangic(){
        board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(C);
    }
    public void D_baslangic(){
        board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(D);
    }
    
    public void yoket(){
        board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(null);
        if(board[oyuncu_a.getX()][oyuncu_a.getY()].isGizlimi()){
            
            if(board[oyuncu_a.getX()][oyuncu_a.getY()].getAltin() == 5){
                       board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_a.getX()][oyuncu_a.getY()].getAltin() == 10){
                       board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_a.getX()][oyuncu_a.getY()].getAltin() == 15){
                       board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_a.getX()][oyuncu_a.getY()].getAltin() == 20){
                       board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(coin20);
                   }
            board[oyuncu_a.getX()][oyuncu_a.getY()].setBasildimi(true);
            board[oyuncu_a.getX()][oyuncu_a.getY()].setGizlimi(false);
        }
        
        
        if(board[oyuncu_a.getX()][oyuncu_a.getY()].equals(board[oyuncu_b.getX()][oyuncu_b.getY()])){
            board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(B);
        }
        if(board[oyuncu_a.getX()][oyuncu_a.getY()].equals(board[oyuncu_c.getX()][oyuncu_c.getY()])){
            board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(C);
        }
        if(board[oyuncu_a.getX()][oyuncu_a.getY()].equals(board[oyuncu_d.getX()][oyuncu_d.getY()])){
            board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(D);
        }
    }
    public void yoketb(){
        board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(null);
        if(board[oyuncu_b.getX()][oyuncu_b.getY()].isGizlimi()){
            if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 5){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 10){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 15){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 20){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin20);
                   }
            board[oyuncu_b.getX()][oyuncu_b.getY()].setBasildimi(true);
            board[oyuncu_b.getX()][oyuncu_b.getY()].setGizlimi(false);
        }
        if(board[oyuncu_b.getX()][oyuncu_b.getY()].getBasildimi()){
            if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 5){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 10){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 15){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_b.getX()][oyuncu_b.getY()].getAltin() == 20){
                       board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(coin20);
                   }
        }
        
        
        if(board[oyuncu_b.getX()][oyuncu_b.getY()].equals(board[oyuncu_a.getX()][oyuncu_a.getY()])){
            board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(A);
        }
        if(board[oyuncu_b.getX()][oyuncu_b.getY()].equals(board[oyuncu_c.getX()][oyuncu_c.getY()])){
            board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(C);
        }
        if(board[oyuncu_b.getX()][oyuncu_b.getY()].equals(board[oyuncu_d.getX()][oyuncu_d.getY()])){
            board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(D);
        }
    }
    public void yoketc(){
        board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(null);
        if(board[oyuncu_c.getX()][oyuncu_c.getY()].isGizlimi()){
            if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 5){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 10){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 15){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 20){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin20);
                   }
            board[oyuncu_c.getX()][oyuncu_c.getY()].setBasildimi(true);
            board[oyuncu_c.getX()][oyuncu_c.getY()].setGizlimi(false);
        }
        if(board[oyuncu_c.getX()][oyuncu_c.getY()].getBasildimi()){
            if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 5){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 10){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 15){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_c.getX()][oyuncu_c.getY()].getAltin() == 20){
                       board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(coin20);
                   }
        }
        
        if(board[oyuncu_c.getX()][oyuncu_c.getY()].equals(board[oyuncu_a.getX()][oyuncu_a.getY()])){
            board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(A);
        }
        if(board[oyuncu_c.getX()][oyuncu_c.getY()].equals(board[oyuncu_b.getX()][oyuncu_b.getY()])){
            board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(B);
        }
        if(board[oyuncu_c.getX()][oyuncu_c.getY()].equals(board[oyuncu_d.getX()][oyuncu_d.getY()])){
            board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(D);
        }
    }
    
    public void yoketd(){
        board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(null);
        if(board[oyuncu_d.getX()][oyuncu_d.getY()].isGizlimi()){
            if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 5){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 10){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 15){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 20){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin20);
                   }
            board[oyuncu_d.getX()][oyuncu_d.getY()].setBasildimi(true);
            board[oyuncu_d.getX()][oyuncu_d.getY()].setGizlimi(false);
        }
        if(board[oyuncu_d.getX()][oyuncu_d.getY()].getBasildimi()){
            if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 5){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin5);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 10){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin10);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 15){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin15);
                   }
                   else if(board[oyuncu_d.getX()][oyuncu_d.getY()].getAltin() == 20){
                       board[oyuncu_d.getX()][oyuncu_d.getY()].setIcon(coin20);
                   }
        }
        
        if(board[oyuncu_d.getX()][oyuncu_d.getY()].equals(board[oyuncu_a.getX()][oyuncu_a.getY()])){
            board[oyuncu_a.getX()][oyuncu_a.getY()].setIcon(A);
        }
        if(board[oyuncu_d.getX()][oyuncu_d.getY()].equals(board[oyuncu_b.getX()][oyuncu_b.getY()])){
            board[oyuncu_b.getX()][oyuncu_b.getY()].setIcon(B);
        }
        if(board[oyuncu_d.getX()][oyuncu_d.getY()].equals(board[oyuncu_c.getX()][oyuncu_c.getY()])){
            board[oyuncu_c.getX()][oyuncu_c.getY()].setIcon(C);
        }
    }
    
    public void A_haraket() throws InterruptedException, IOException{
        
        int toplam = 0, temp=999, adimsayar=0, tur=0;
        if((oyuncu_a.getX() == tut_x && oyuncu_a.getY() == tut_y) || !board[tut_x][tut_y].getBasildimi()){
            oyuncu_a.setAltin(oyuncu_a.getAltin()- gir.getA_hedef());
            a_harcanan_altin += gir.getA_hedef();
            for(int row = 0; row <board.length; row++){
                for(int col = 0 ; col<board[0].length; col++){
                    if(board[row][col].getBasildimi()){
                       toplam = Math.abs(oyuncu_a.getX()-row)+Math.abs(oyuncu_a.getY()-col);
                        if(temp>toplam){
                            temp = toplam;
                            tut_x = row;
                            tut_y = col;
                            a_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                       }
                    }
                }
            }
        }
        oyuncu_a.setAltin(oyuncu_a.getAltin()-gir.getA_adim());
        a_harcanan_altin += gir.getA_adim();
        while(adimsayar<adim){
            
            if(oyuncu_a.getX() != tut_x){
                TimeUnit.SECONDS.sleep(1);
                yoket();
                if(oyuncu_a.getX()<tut_x){
                    oyuncu_a.setX(oyuncu_a.getX()+1);
                }
                else{
                    oyuncu_a.setX(oyuncu_a.getX()-1);
                }
                bWritera.write("x:"+String.valueOf(oyuncu_a.getX())+" y:"+String.valueOf(oyuncu_a.getY())+"\n");
                A_baslangic();               
                adimsayar++;
            }
            else if(oyuncu_a.getY() != tut_y && oyuncu_a.getX() == tut_x){
                TimeUnit.SECONDS.sleep(1);
                yoket();
                if(oyuncu_a.getY()<tut_y){
                    oyuncu_a.setY(oyuncu_a.getY()+1);
                }
                else{
                    oyuncu_a.setY(oyuncu_a.getY()-1);
                }
                bWritera.write("x:"+String.valueOf(oyuncu_a.getX())+" y:"+String.valueOf(oyuncu_a.getY())+"\n");
                A_baslangic();              
                adimsayar++;
            }
            else{
                
                break;
            }  
            a_atilan_adim++;
        }
        
        a_tur_sayac--;
        
        if(oyuncu_a.getX() == tut_x && oyuncu_a.getY() == tut_y){
            oyuncu_a.setAltin(oyuncu_a.getAltin()+board[tut_x][tut_y].getAltin());
            
            A_baslangic();
            board[tut_x][tut_y].setBasildimi(false);
            
        }
        System.out.println("x:"+tut_x+" y:"+tut_y);
        System.out.println("x:"+oyuncu_a.getX()+" y:"+oyuncu_a.getY());
        
        temp = 999;
        toplam = 0;
        
        if((oyuncu_a.getX() == tut_x && oyuncu_a.getY() == tut_y) || !board[tut_x][tut_y].getBasildimi()){
            oyuncu_a.setAltin(oyuncu_a.getAltin()-gir.getA_hedef());
            a_harcanan_altin += gir.getA_hedef();
            for(int row = 0; row <board.length; row++){
                for(int col = 0 ; col<board[0].length; col++){
                    if(board[row][col].getBasildimi()){
                       toplam = Math.abs(oyuncu_a.getX()-row)+Math.abs(oyuncu_a.getY()-col);
                        if(temp>toplam){
                            temp = toplam;
                            tut_x = row;
                            tut_y = col;
                            a_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                       }
                    }
                }
            }
        }
        
    }
    
    
    public void B_haraket() throws InterruptedException, IOException{
        int toplam=0,tur=0,gain=0,temp=-999,adimsayar = 0;
        
        if((oyuncu_b.getX() == tut_xb && oyuncu_b.getY() == tut_yb) || !board[tut_xb][tut_yb].getBasildimi()){
            
            oyuncu_b.setAltin(oyuncu_b.getAltin()-gir.getB_hedef());
            b_harcanan_altin += gir.getB_hedef();
            for(int row = 0; row <board.length; row++){
                for(int col = 0 ; col<board[0].length; col++){
                    if(board[row][col].getBasildimi()){
                        toplam = Math.abs(oyuncu_b.getX()-row)+Math.abs(oyuncu_b.getY()-col);
                        tur = (int) Math.ceil(toplam/(double)adim);
                        gain = board[row][col].getAltin() - tur*5;
                        if(temp<gain){
                            temp = gain;
                            tut_xb = row;
                            tut_yb = col;
                            b_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                        }
                    }
                }
            }            
        }
        
        oyuncu_b.setAltin(oyuncu_b.getAltin()-gir.getB_adim());
        b_harcanan_altin += gir.getB_adim();
        while(adimsayar<adim){
            
            if(oyuncu_b.getX() != tut_xb){
                TimeUnit.SECONDS.sleep(1);
                yoketb();
                if(oyuncu_b.getX()<tut_xb){
                    oyuncu_b.setX(oyuncu_b.getX()+1);
                }
                else{
                    oyuncu_b.setX(oyuncu_b.getX()-1);
                }
                bWriterb.write("x:"+String.valueOf(oyuncu_b.getX())+" y:"+String.valueOf(oyuncu_b.getY())+"\n");
                B_baslangic();               
                adimsayar++;
            }
            else if(oyuncu_b.getY() != tut_yb && oyuncu_b.getX() == tut_xb){
                TimeUnit.SECONDS.sleep(1);
                yoketb();
                if(oyuncu_b.getY()<tut_yb){
                    oyuncu_b.setY(oyuncu_b.getY()+1);
                }
                else{
                    oyuncu_b.setY(oyuncu_b.getY()-1);
                }
                bWriterb.write("x:"+String.valueOf(oyuncu_b.getX())+" y:"+String.valueOf(oyuncu_b.getY())+"\n");
                B_baslangic();              
                adimsayar++;
            }
            else{
                
                break;
            }
            b_atilan_adim++;
        }
        
        b_tur_sayac--;
        
        if(oyuncu_b.getX() == tut_xb && oyuncu_b.getY() == tut_yb){
            
            oyuncu_b.setAltin(oyuncu_b.getAltin() + board[tut_xb][tut_yb].getAltin());            
            B_baslangic();           
            board[tut_xb][tut_yb].setBasildimi(false);
            
        }
       
        System.out.println("x:"+tut_xb+" y:"+tut_yb);
        System.out.println("x:"+oyuncu_b.getX()+" y:"+oyuncu_b.getY());
        
        toplam=0;
        tur=0;
        gain=0;
        temp=-999;
        
        
        if((oyuncu_b.getX() == tut_xb && oyuncu_b.getY() == tut_yb) || !board[tut_xb][tut_yb].getBasildimi()){
            
            oyuncu_b.setAltin(oyuncu_b.getAltin()-gir.getB_hedef());
            b_harcanan_altin += gir.getB_hedef();
            for(int row = 0; row <board.length; row++){
                for(int col = 0 ; col<board[0].length; col++){
                    if(board[row][col].getBasildimi()){
                        toplam = Math.abs(oyuncu_b.getX()-row)+Math.abs(oyuncu_b.getY()-col);
                        tur = (int) Math.ceil(toplam/(double)adim);
                        gain = board[row][col].getAltin() - tur*5;
                        if(temp<gain){
                            temp = gain;
                            tut_xb = row;
                            tut_yb = col;
                            b_tur_sayac = (int) Math.ceil(toplam/(double)adim);
                        }
                    }
                }
            }            
        }
    }
    
    public void C_haraket() throws InterruptedException, IOException{
        
        int sayac=0,toplam =0, toplam2=0, temp=-999,temp2 = 999,gain=0,tur=0, tut_gizlix=0, tut_gizliy=0,adimsayar=0;
        
        if((oyuncu_c.getX() == tut_xc && oyuncu_c.getY() == tut_yc) || !board[tut_xc][tut_yc].getBasildimi()){
            while(sayac<2){
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].isGizlimi()){
                            toplam2 = Math.abs(oyuncu_c.getX()-row)+Math.abs(oyuncu_c.getY()-col);
                            if(temp2>toplam2){
                                temp2 = toplam2;
                                tut_gizlix = row;
                                tut_gizliy = col;
                            }
                        }
                    }
                }
                
                int sayacc = 0;                
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].isGizlimi()){
                            sayacc++;
                        }
                    }
                }                
                if(sayacc>=1){
                    board[tut_gizlix][tut_gizliy].setGizlimi(false);
                    board[tut_gizlix][tut_gizliy].setBasildimi(true);
                    
                    if(board[tut_gizlix][tut_gizliy].getAltin() == 5){
                       board[tut_gizlix][tut_gizliy].setIcon(coin5);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 10){
                       board[tut_gizlix][tut_gizliy].setIcon(coin10);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 15){
                       board[tut_gizlix][tut_gizliy].setIcon(coin15);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 20){
                       board[tut_gizlix][tut_gizliy].setIcon(coin20);
                   }
                }                
                sayac++;
                temp2 = 999;
            }
            
            oyuncu_c.setAltin(oyuncu_c.getAltin()-gir.getC_hedef());
            c_harcanan_altin += gir.getC_hedef();
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                           toplam = Math.abs(oyuncu_c.getX()-row)+Math.abs(oyuncu_c.getY()-col);
                           tur = (int) Math.ceil(toplam/(double)adim);
                           gain = board[row][col].getAltin() - tur*5;
                           if(temp<gain){
                                temp = gain;
                                tut_xc = row;
                                tut_yc = col;
                                c_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                           }
                        }
                    }
            }
        }
        
        oyuncu_c.setAltin(oyuncu_c.getAltin()-gir.getC_adim());
        c_harcanan_altin += gir.getC_adim();
        while(adimsayar<adim){
            
            if(oyuncu_c.getX() != tut_xc){
                TimeUnit.SECONDS.sleep(1);
                yoketc();
                if(oyuncu_c.getX()<tut_xc){
                    oyuncu_c.setX(oyuncu_c.getX()+1);
                }
                else{
                    oyuncu_c.setX(oyuncu_c.getX()-1);
                }
                bWriterc.write("x:"+String.valueOf(oyuncu_c.getX())+" y:"+String.valueOf(oyuncu_c.getY())+"\n");
                C_baslangic();               
                adimsayar++;
            }
            else if(oyuncu_c.getY() != tut_yc && oyuncu_c.getX() == tut_xc){
                TimeUnit.SECONDS.sleep(1);
                yoketc();
                if(oyuncu_c.getY()<tut_yc){
                    oyuncu_c.setY(oyuncu_c.getY()+1);
                }
                else{
                    oyuncu_c.setY(oyuncu_c.getY()-1);
                }
                bWriterc.write("x:"+String.valueOf(oyuncu_c.getX())+" y:"+String.valueOf(oyuncu_c.getY())+"\n");
                C_baslangic();              
                adimsayar++;
            }
            else{
                
                break;
            }
            c_atilan_adim++;
        }
        
        c_tur_sayac--;
        
        if(oyuncu_c.getX() == tut_xc && oyuncu_c.getY() == tut_yc){
            oyuncu_c.setAltin(oyuncu_c.getAltin()+board[tut_xc][tut_yc].getAltin());
            
            B_baslangic();
            
            board[tut_xc][tut_yc].setBasildimi(false);
            
        }
       
        System.out.println("x:"+tut_xc+" y:"+tut_yc);
        System.out.println("x:"+oyuncu_c.getX()+" y:"+oyuncu_c.getY());
        
        sayac=0;toplam =0; toplam2=0; temp=-999;temp2 = 999; gain=0; tur=0; tut_gizlix=0; tut_gizliy=0;
        
        if((oyuncu_c.getX() == tut_xc && oyuncu_c.getY() == tut_yc) || !board[tut_xc][tut_yc].getBasildimi()){
            while(sayac<2){
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].isGizlimi()){
                            toplam2 = Math.abs(oyuncu_c.getX()-row)+Math.abs(oyuncu_c.getY()-col);
                            if(temp2>toplam2){
                                temp2 = toplam2;
                                tut_gizlix = row;
                                tut_gizliy = col;
                            }
                        }
                    }
                }
                
                int sayacc = 0; 
                
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].isGizlimi()){
                            sayacc++;
                        }
                    }
                }      
                
                if(sayacc>=1){
                    board[tut_gizlix][tut_gizliy].setGizlimi(false);
                    board[tut_gizlix][tut_gizliy].setBasildimi(true);
                    if(board[tut_gizlix][tut_gizliy].getAltin() == 5){
                       board[tut_gizlix][tut_gizliy].setIcon(coin5);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 10){
                       board[tut_gizlix][tut_gizliy].setIcon(coin10);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 15){
                       board[tut_gizlix][tut_gizliy].setIcon(coin15);
                   }
                   else if(board[tut_gizlix][tut_gizliy].getAltin() == 20){
                       board[tut_gizlix][tut_gizliy].setIcon(coin20);
                   }
                }     
                
                sayac++;
                temp2 = 999;
                
            }
            
            oyuncu_c.setAltin(oyuncu_c.getAltin()-gir.getC_hedef());
            c_harcanan_altin += gir.getC_hedef();
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                           toplam = Math.abs(oyuncu_c.getX()-row)+Math.abs(oyuncu_c.getY()-col);
                           tur = (int) Math.ceil(toplam/(double)adim);
                           gain = board[row][col].getAltin() - tur*5;
                           if(temp<gain){
                                temp = gain;
                                tut_xc = row;
                                tut_yc = col;
                                c_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                           }
                        }
                    }
            }
        }
        
    }
    
    public void D_haraket() throws InterruptedException, IOException{
        int hepsi=0,d_tur_sayac2=0;
        int toplam=0, tur=0, gain=0, temp=-999, adimsayar=0;
        boolean a = false;
        boolean b = false;
        boolean c = false;
        if((oyuncu_d.getX() == tut_xd && oyuncu_d.getY() == tut_yd) || !board[tut_xd][tut_yd].getBasildimi()){
            
            oyuncu_d.setAltin(oyuncu_d.getAltin()-gir.getD_hedef());
            d_harcanan_altin += gir.getD_hedef();
            for(int row = 0; row <board.length; row++){
                for(int col = 0 ; col<board[0].length; col++){
                    if(board[row][col].getBasildimi()){
                        
                        if(tut_x == row && tut_y == col){
                            toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                            tur = (int) Math.ceil(toplam/(double)adim);
                            gain = board[row][col].getAltin() - tur*5;
                            if(temp<gain){
                                d_tur_sayac2 = (int) Math.ceil(toplam/(double)adim);
                            
                            if(a_tur_sayac<d_tur_sayac2){
                                hepsi++;
                                continue;
                            }
                            else{
                                temp = gain;
                                d_tur_sayac = (int) Math.ceil(toplam/(double)adim);
                                tut_xd = row;
                                tut_yd = col;
                                continue;
                            }
                            }
                        }
                        else if(tut_xb == row && tut_yb == col){
                            toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                            tur = (int) Math.ceil(toplam/(double)adim);
                            gain = board[row][col].getAltin() - tur*5;
                            if(temp<gain){
                                d_tur_sayac2 = (int) Math.ceil(toplam/(double)adim);
                            
                            if(b_tur_sayac<d_tur_sayac2){
                                hepsi++;
                                continue;
                            }
                            else{
                                temp = gain;
                                d_tur_sayac = (int) Math.ceil(toplam/(double)adim);
                                tut_xd = row;
                                tut_yd = col;
                                continue;
                            }
                            }
                        }
                        else if(tut_xc == row && tut_yc == col){
                            toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                            tur = (int) Math.ceil(toplam/(double)adim);
                            gain = board[row][col].getAltin() - tur*5;
                            if(temp<gain){
                                d_tur_sayac2 = (int) Math.ceil(toplam/(double)adim);
                            
                            if(c_tur_sayac<d_tur_sayac2){
                                hepsi++;
                                continue;
                            }
                            else{
                                temp = gain;
                                d_tur_sayac = (int) Math.ceil(toplam/(double)adim);
                                tut_xd = row;
                                tut_yd = col;
                                continue;
                            }
                            }
                        }
                        
                        toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                        tur = (int) Math.ceil(toplam/(double)adim);
                        gain = board[row][col].getAltin() - tur*5;
                        if(temp<gain){
                            temp = gain;
                            tut_xd = row;
                            tut_yd = col;
                            d_tur_sayac = (int) Math.ceil(toplam/(double)adim);
                        }
                    }
                }
            }
            
            
            
        }
        
       /* if(tut_xd == tut_x && tut_yd == tut_y){
                a = false;
                if(a_tur_sayac <= d_tur_sayac){
                    oyuncu_d.setAltin(oyuncu_d.getAltin()-gir.getD_hedef());
                    d_harcanan_altin += gir.getD_hedef();
                    toplam=0; tur=0; gain=0; temp=0;
                    for(int row = 0; row <board.length; row++){
                        for(int col = 0 ; col<board[0].length; col++){
                            if(board[row][col].getBasildimi()){
                                toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                                tur = (int) Math.ceil(toplam/(double)adim);
                                gain = board[row][col].getAltin() - tur;
                                if(temp<gain && !(tut_x == tut_xd && tut_y == tut_yd)){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    a = true;
                                }
                            }
                        }
                    }
                }
            }
        
        if(tut_xd == tut_xb && tut_yd == tut_yb){
            b = false;
            if(b_tur_sayac <= d_tur_sayac){
                if(!a){
                    oyuncu_d.setAltin(oyuncu_d.getAltin()-gir.getD_hedef());
                    d_harcanan_altin += gir.getD_hedef();
                }
                toplam=0; tur=0; gain=0; temp=0;
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                            toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                            tur = (int) Math.ceil(toplam/(double)adim);
                            gain = board[row][col].getAltin() - tur;
                            if(a == true){
                                if(temp<gain && !((tut_xb == tut_xd && tut_yb == tut_yd) || (tut_x == tut_xd && tut_y == tut_yd))){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    b = true;
                                }
                            }
                            else{
                                if(temp<gain && !(tut_xb == tut_xd && tut_yb == tut_yd)){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    b = true;
                                }
                            }
                            
                        }
                    }
                }
                
            }
        }
        
        if(tut_xd == tut_xc && tut_yd == tut_yc){
            c = false;
            if(c_tur_sayac <= d_tur_sayac){
                if(!a || !b){
                    oyuncu_d.setAltin(oyuncu_d.getAltin()-gir.getD_hedef());
                    d_harcanan_altin += gir.getD_hedef();
                }
                toplam=0; tur=0; gain=0; temp=0;
                for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        
                        if(board[row][col].getBasildimi()){
                            toplam = Math.abs(oyuncu_d.getX()-row)+Math.abs(oyuncu_d.getY()-col);
                            tur = (int) Math.ceil(toplam/(double)adim);
                            gain = board[row][col].getAltin() - tur;
                            if(a == true && b == true){
                                if(temp<gain && !((tut_xc == tut_xd && tut_yc == tut_yd) || (tut_xb == tut_xd && tut_yb == tut_yd) || (tut_x == tut_xd && tut_y == tut_yd))){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    c = true;
                                }
                            }
                            else if(a == true){
                                if(temp<gain && !((tut_xc == tut_xd && tut_yc == tut_yd) || (tut_x == tut_xd && tut_y == tut_yd))){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    c = true;
                                }
                            }
                            else if(b == true){
                                if(temp<gain && !((tut_xc == tut_xd && tut_yc == tut_yd) || (tut_xb == tut_xd && tut_yb == tut_yd))){
                                    temp = gain;
                                    tut_xd = row;
                                    tut_yd = col;
                                    d_tur_sayac =(int) Math.ceil(toplam/(double)adim);
                                    c = true;
                                }
                            }
                                                     
                        }
                        
                    }
                }
                
            }
        }*/
        
        
        
        if(hepsi!=3){
        oyuncu_d.setAltin(oyuncu_d.getAltin()-gir.getD_adim());
        d_harcanan_altin += gir.getD_adim();
        d_tur_sayac--;
        
        
        while(adimsayar<adim){
            
            if(oyuncu_d.getX() != tut_xd){
                TimeUnit.SECONDS.sleep(1);
                yoketd();
                if(oyuncu_d.getX()<tut_xd){
                    oyuncu_d.setX(oyuncu_d.getX()+1);
                }
                else{
                    oyuncu_d.setX(oyuncu_d.getX()-1);
                }
                bWriterd.write("x:"+String.valueOf(oyuncu_d.getX())+" y:"+String.valueOf(oyuncu_d.getY())+"\n");
                D_baslangic();               
                adimsayar++;
            }
            else if(oyuncu_d.getY() != tut_yd && oyuncu_d.getX() == tut_xd){
                TimeUnit.SECONDS.sleep(1);
                yoketd();
                if(oyuncu_d.getY()<tut_yd){
                    oyuncu_d.setY(oyuncu_d.getY()+1);
                }
                else{
                    oyuncu_d.setY(oyuncu_d.getY()-1);
                }
                bWriterd.write("x:"+String.valueOf(oyuncu_d.getX())+" y:"+String.valueOf(oyuncu_d.getY())+"\n");
                D_baslangic();              
                adimsayar++;
            }
            else{
                
                break;
            }
            d_atilan_adim++;
        }
    }
        
        if(oyuncu_d.getX() == tut_xd && oyuncu_d.getY() == tut_yd){
            
            oyuncu_d.setAltin(oyuncu_d.getAltin() + board[tut_xd][tut_yd].getAltin());            
            D_baslangic();           
            board[tut_xd][tut_yd].setBasildimi(false);
            
        }
        
        System.out.println("x:"+tut_xd+" y:"+tut_yd);
        System.out.println("x:"+oyuncu_d.getX()+" y:"+oyuncu_d.getY());
    }
    
    public void oyun() throws InterruptedException, IOException{    
        int a;
        String mesaj = "KAZANAN:";
        while(true){
            a=0;
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                            a=1;
                        }
                    }
            }
            if(a==0){
                bWritera.close();
                bWriterb.close();
                bWriterc.close();
                bWriterd.close();
                System.out.println("OYUN BITTI");
                
                SonucEkrani ekran = new SonucEkrani(a_atilan_adim,b_atilan_adim,c_atilan_adim,d_atilan_adim,a_harcanan_altin,b_harcanan_altin,c_harcanan_altin,d_harcanan_altin,
                oyuncu_a.getAltin(),oyuncu_b.getAltin(),oyuncu_c.getAltin(),oyuncu_d.getAltin(),baslangic);
                frame.setVisible(false);
                ekran.setVisible(true);
                ekran.setAlwaysOnTop(true);
                if(oyuncu_a.getAltin()>=oyuncu_b.getAltin() && oyuncu_a.getAltin()>=oyuncu_c.getAltin() && oyuncu_a.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" A"+":"+oyuncu_a.getAltin();
                }
                else if(oyuncu_b.getAltin()>=oyuncu_a.getAltin() && oyuncu_b.getAltin()>=oyuncu_c.getAltin() && oyuncu_b.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" B"+":"+oyuncu_b.getAltin();
                }
                else if(oyuncu_c.getAltin()>=oyuncu_a.getAltin() && oyuncu_c.getAltin()>=oyuncu_b.getAltin() && oyuncu_c.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" C"+":"+oyuncu_c.getAltin();
                }
                else if(oyuncu_d.getAltin()>=oyuncu_a.getAltin() && oyuncu_d.getAltin()>=oyuncu_c.getAltin() && oyuncu_d.getAltin()>=oyuncu_b.getAltin()){
                    mesaj+=",D"+":"+oyuncu_c.getAltin();
                }
                JOptionPane.showMessageDialog(frame, mesaj);
                TimeUnit.SECONDS.sleep(100);
                System.exit(0);
            }
            
            if(oyuncu_a.getAltin()>0){
                A_haraket();
                System.out.println(oyuncu_a.getAltin());     
            }
            
            a=0;
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                            a=1;
                        }
                    }
            }
            
            if(a==0){
                bWritera.close();
                bWriterb.close();
                bWriterc.close();
                bWriterd.close();
                System.out.println("OYUN BITTI");
                
                SonucEkrani ekran = new SonucEkrani(a_atilan_adim,b_atilan_adim,c_atilan_adim,d_atilan_adim,a_harcanan_altin,b_harcanan_altin,c_harcanan_altin,d_harcanan_altin,
                oyuncu_a.getAltin(),oyuncu_b.getAltin(),oyuncu_c.getAltin(),oyuncu_d.getAltin(),baslangic);
                frame.setVisible(false);
                ekran.setVisible(true);
                ekran.setAlwaysOnTop(true);
                if(oyuncu_a.getAltin()>=oyuncu_b.getAltin() && oyuncu_a.getAltin()>=oyuncu_c.getAltin() && oyuncu_a.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" A"+":"+oyuncu_a.getAltin();
                }
                else if(oyuncu_b.getAltin()>=oyuncu_a.getAltin() && oyuncu_b.getAltin()>=oyuncu_c.getAltin() && oyuncu_b.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" B"+":"+oyuncu_b.getAltin();
                }
                else if(oyuncu_c.getAltin()>=oyuncu_a.getAltin() && oyuncu_c.getAltin()>=oyuncu_b.getAltin() && oyuncu_c.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" C"+":"+oyuncu_c.getAltin();
                }
                else if(oyuncu_d.getAltin()>=oyuncu_a.getAltin() && oyuncu_d.getAltin()>=oyuncu_c.getAltin() && oyuncu_d.getAltin()>=oyuncu_b.getAltin()){
                    mesaj+=",D"+":"+oyuncu_c.getAltin();
                }
                JOptionPane.showMessageDialog(frame, mesaj);
                TimeUnit.SECONDS.sleep(100);
                System.exit(0);
            }
            
            if(oyuncu_b.getAltin()>0){
                B_haraket();
                System.out.println(oyuncu_b.getAltin());
            }
            a=0;
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                            a=1;
                        }
                    }
            }
            
            
            if(a==0){
                bWritera.close();
                bWriterb.close();
                bWriterc.close();
                bWriterd.close();
                System.out.println("OYUN BITTI");
                
                SonucEkrani ekran = new SonucEkrani(a_atilan_adim,b_atilan_adim,c_atilan_adim,d_atilan_adim,a_harcanan_altin,b_harcanan_altin,c_harcanan_altin,d_harcanan_altin,
                oyuncu_a.getAltin(),oyuncu_b.getAltin(),oyuncu_c.getAltin(),oyuncu_d.getAltin(),baslangic);
                frame.setVisible(false);
                ekran.setVisible(true);
                ekran.setAlwaysOnTop(true);
                if(oyuncu_a.getAltin()>=oyuncu_b.getAltin() && oyuncu_a.getAltin()>=oyuncu_c.getAltin() && oyuncu_a.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" A"+":"+oyuncu_a.getAltin();
                }
                else if(oyuncu_b.getAltin()>=oyuncu_a.getAltin() && oyuncu_b.getAltin()>=oyuncu_c.getAltin() && oyuncu_b.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" B"+":"+oyuncu_b.getAltin();
                }
                else if(oyuncu_c.getAltin()>=oyuncu_a.getAltin() && oyuncu_c.getAltin()>=oyuncu_b.getAltin() && oyuncu_c.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" C"+":"+oyuncu_c.getAltin();
                }
                else if(oyuncu_d.getAltin()>=oyuncu_a.getAltin() && oyuncu_d.getAltin()>=oyuncu_c.getAltin() && oyuncu_d.getAltin()>=oyuncu_b.getAltin()){
                    mesaj+=",D"+":"+oyuncu_c.getAltin();
                }
                JOptionPane.showMessageDialog(frame, mesaj);
                TimeUnit.SECONDS.sleep(100);
                System.exit(0);
            }
            
            if(oyuncu_c.getAltin()>0){
                C_haraket();
                System.out.println(oyuncu_c.getAltin());
            }  
            a=0;
            for(int row = 0; row <board.length; row++){
                    for(int col = 0 ; col<board[0].length; col++){
                        if(board[row][col].getBasildimi()){
                            a=1;
                        }
                    }
            }
            
            if(a==0){
                bWritera.close();
                bWriterb.close();
                bWriterc.close();
                bWriterd.close();
                System.out.println("OYUN BITTI");
                
                SonucEkrani ekran = new SonucEkrani(a_atilan_adim,b_atilan_adim,c_atilan_adim,d_atilan_adim,a_harcanan_altin,b_harcanan_altin,c_harcanan_altin,d_harcanan_altin,
                oyuncu_a.getAltin(),oyuncu_b.getAltin(),oyuncu_c.getAltin(),oyuncu_d.getAltin(),baslangic);
                frame.setVisible(false);
                ekran.setVisible(true);
                ekran.setAlwaysOnTop(true);
                if(oyuncu_a.getAltin()>=oyuncu_b.getAltin() && oyuncu_a.getAltin()>=oyuncu_c.getAltin() && oyuncu_a.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" A"+":"+oyuncu_a.getAltin();
                }
                else if(oyuncu_b.getAltin()>=oyuncu_a.getAltin() && oyuncu_b.getAltin()>=oyuncu_c.getAltin() && oyuncu_b.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" B"+":"+oyuncu_b.getAltin();
                }
                else if(oyuncu_c.getAltin()>=oyuncu_a.getAltin() && oyuncu_c.getAltin()>=oyuncu_b.getAltin() && oyuncu_c.getAltin()>=oyuncu_d.getAltin()){
                    mesaj+=" C"+":"+oyuncu_c.getAltin();
                }
                else if(oyuncu_d.getAltin()>=oyuncu_a.getAltin() && oyuncu_d.getAltin()>=oyuncu_c.getAltin() && oyuncu_d.getAltin()>=oyuncu_b.getAltin()){
                    mesaj+=",D"+":"+oyuncu_c.getAltin();
                }
                JOptionPane.showMessageDialog(frame, mesaj);
                TimeUnit.SECONDS.sleep(100);
                System.exit(0);
            }
            
            if(oyuncu_d.getAltin()>0){
                D_haraket();
                System.out.println(oyuncu_d.getAltin());
            }
            
            
        }
    }
}
