/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class DT_Dinh implements Serializable{

    public DT_Dinh() {
    }
    int x;
    int y;
    int stt;
    int[] lk;
    public DT_Dinh(int x, int y, int stt, int[] lk) {
        this.x = x;
        this.y = y;
        this.stt = stt;
        this.lk = lk;
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

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int[] getLk() {
        return lk;
    }

    public void setLk(int[] lk) {
        this.lk = lk;
    }

    
}
