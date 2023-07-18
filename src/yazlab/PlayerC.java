/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab;

/**
 *
 * @author ONUR
 */
public class PlayerC extends Oyuncu{
    private int x=0,y;
    public PlayerC(int altin, int adimSayisi,int y) {
        super(altin, adimSayisi);
        this.y = y-1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    
}
