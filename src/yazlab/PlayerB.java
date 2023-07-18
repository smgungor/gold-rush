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
public class PlayerB extends Oyuncu{
    int x,y=0;
    public PlayerB(int altin, int adimSayisi,int x) {
        super(altin, adimSayisi);
        this.x = x-1;
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
