package yazlab;

import javax.swing.JButton;


public class Butonlar extends JButton {
    private int row,col,altin;
    private boolean basildimi,gizlimi;

    public boolean isGizlimi() {
        return gizlimi;
    }

    public void setGizlimi(boolean gizlimi) {
        this.gizlimi = gizlimi;
    }
    public Butonlar(int row, int col){
        this.row = row;
        this.col = col;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }
    public int getCol(){
       return col;
    }
    public void setCol(int col){
        this.col = col;
    }
    public int getAltin(){
        return altin;
    }
    public void setAltin(int altin){
        this.altin = altin;
    }
    public boolean getBasildimi(){
        return basildimi;
    }
    public void setBasildimi(boolean basildimi){
        this.basildimi = basildimi;
    }
    
}