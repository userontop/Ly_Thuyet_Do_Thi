/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class paintOval implements Runnable {

    Graphics g;

    boolean song_Song = false;
    boolean Khuyen_co = false;

    int[] x = new int[100];
    int[] y = new int[100];

    int temp_FillChar = 0;
    int temp_n = 0;
    int temp_Canh = 0;
    int resize = 40;
    int char_resize = 0;
    int temp_So_Canh = 0;

    int So_Bac[] = new int[100];
    int[][] lk_Point = new int[100][100];
    int sox_lk = 0;
    int soy_lk = 0;
    int temp_lk = 0;

    int temp_Khuyen = 0;
    int son_lk = 0;
    int temp_Toa_Do_Canh = 0;

    int temp_pointx;
    int temp_pointy;

    public paintOval(Graphics g) {
        this.g = g;
    }

    //---------------------------------------------run
    @Override
    public void run() {
//        g.setColor(Color.black);
//        if(temp_Canh == 0){
//            Bong(x[temp_FillChar], y[temp_FillChar]);
//            fillChar(x[temp_FillChar], y[temp_FillChar]);
//        }

    }
    //-----------------------------------------------------------------method

    void Bong(int x, int y, Color c) {
        g.setColor(c);
        g.fillOval(x - resize / 2, y - resize / 2, resize, resize);
        son_lk = temp_FillChar;

    }

    void Resize_boll() {
        for (int i = 0; i <= temp_FillChar; i++) {
            g.setColor(Color.black);
            Bong(x[i], y[i], Color.black);
            fillChar(x[i], y[i], char_resize);
            char_resize++;
        }
        char_resize = 0;
    }

    void del_boll(int temp_del_boll) {
        for (int i = 0; i <= temp_FillChar; i++) {
            g.setColor(new Color(204, 204, 204));
            g.fillOval(x[i] - temp_del_boll / 2, y[i] - temp_del_boll / 2, temp_del_boll, temp_del_boll);
        }
    }

    //----------------------------------------------------------tu dong noi - vo huong
    void Line(int n, int x[], int y[]) {

        g.setColor(Color.black);
        for (int j = 1; j < n; j++) {
            g.drawLine(x[j - 1], y[j - 1], x[j], y[j]);
        }
        try {
            g.drawLine(x[0], y[0], x[n - 1], y[n - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "ban chua vẽ");

        }
        if (n > temp_n && temp_n != 0) {
            g.setColor(new Color(204, 204, 204));
            g.drawLine(x[0], y[0], x[temp_n - 1], y[temp_n - 1]);
        }

    }

    //------------------------------------------------vo huong canh dinh
    void AutoLine(int n, int x[], int y[]) {
        if (n >= 1) {
            temp_n = n;
            g.setColor(Color.black);
            g.drawLine(x[n - 1], y[n - 1], x[n], y[n]);
            temp_So_Canh++;
        }
    }

    Polygon plg = new Polygon();
    int temp_pointx_sau = 0;
    int temp_pointy_sau = 0;
    boolean co_ss = false;
    int so_canh_SS = 0;
    int temp_i = 0;

    void Ve_Canh(int n, int x_temp, int y_temp, int x[], int y[]) {
        if (temp_Canh == 0) {
            Resize_boll();
        }
        temp_Canh++;
        temp_lk++;
        if (temp_Toa_Do_Canh > 8) {
            temp_Toa_Do_Canh = - 8;
        }
        boolean chon = false;

        for (int i = 0; i <= n; i++) {
            if ((x_temp <= x[i] - resize / 2 + resize && x_temp >= x[i] - resize / 2 - resize) && (y_temp <= y[i] - resize / 2 + resize && y_temp >= y[i] - resize / 2 - resize)) {
                chon = chon || true;
                temp_i = i;
            }
        }
        if (chon) {
            chon = false;
            if (temp_Canh == 1) {
                temp_pointx = x_temp;
                temp_pointy = y_temp;
            } else if (temp_Canh == 2) {
                temp_pointx_sau = x_temp;
                temp_pointy_sau = y_temp;
            }
            x_temp = x[temp_i];
            y_temp = y[temp_i];
            if (temp_lk == 1) {
                sox_lk = temp_i;//luu diem thu nhat
            } else if (temp_lk == 2) {
                soy_lk = temp_i;
                if (lk_Point[sox_lk][soy_lk] >= 1 && sox_lk != soy_lk) {
                    lk_Point[sox_lk][soy_lk] = 1;
                    lk_Point[soy_lk][sox_lk] = 1;
                    So_Bac[sox_lk] += 1;
                    So_Bac[soy_lk] += 1;
                    Ve_Canh_SS(temp_pointx, temp_pointy, temp_pointx_sau, temp_pointy_sau);
                    song_Song = true;
                    co_ss = true;
                    so_canh_SS++;
                } else if (lk_Point[sox_lk][soy_lk] < 1 && sox_lk != soy_lk) {
                    lk_Point[sox_lk][soy_lk] = 1;
                    lk_Point[soy_lk][sox_lk] = 1;
                    So_Bac[sox_lk] += 1;
                    So_Bac[soy_lk] += 1;
                }
                if (sox_lk == temp_i) {
                    ve_Khuyen(n, x_temp - 3, y_temp - 3, x, y);
                    temp_Canh = 0;
                    temp_lk = 0;
                    plg = new Polygon();
                }
            }

            if (temp_Canh >= 1) {

                plg.addPoint(x_temp, y_temp);
            }
            if (temp_Canh > 1) {
                g.setColor(Color.black);
                if (co_ss == false) {
                    Draw_Polygon(plg);
                }
                temp_Canh = 0;
                plg = new Polygon();
                temp_lk = 0;
                temp_So_Canh++;
                co_ss = false;
            }
            g.setColor(Color.BLUE);
            g.fillOval(x[temp_i] - resize / 2, y[temp_i] - resize / 2, resize, resize);
            fillChar(x[temp_i], y[temp_i], temp_i);
        } else if (chon == false) {
            temp_Canh = 0;
            plg = new Polygon();
            temp_lk = 0;
            sox_lk = 100;
            temp_pointx = 0;
            temp_pointy = 0;
            g.setColor(Color.black);
            g.fillOval(x[temp_i] - resize / 2, y[temp_i] - resize / 2, resize, resize);
            fillChar(x[temp_i], y[temp_i], temp_i);
            System.out.println("com.mycompany.doan_ltdt.paintOval.Ve_Canh()");
            temp_i = 0;
        }

    }

    ArrayList ds_d = new ArrayList();

    public void Luu_Dt() {
        int n = temp_FillChar + 1;
        int[] temp_LK = new int[n];
        DT_Dinh[] dt = new DT_Dinh[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp_LK[j] = lk_Point[i][j];
            }
            dt[i] = new DT_Dinh(x[i], y[i], i, temp_LK);
            ds_d.add(dt[i]);
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("diem.dat"));
                out.writeObject(ds_d);
                out.flush();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(paintOval.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
//    int temp = 0;
//
//    void Ve_Canh2_ss(int tempx, int tempy){
//        temp++;
//        if(temp == 1){
//            temp_pointx = tempx;
//            temp_pointy = tempy;
//        }
//        if(temp == 2){
//            g.setColor(Color.black);
//            Ve_Canh_SS(tempx, tempy, temp_pointx, temp_pointy);
//        temp = 0;  
//        }
//    }

    void Ve_Canh_SS(int pointx, int pointy, int pointx_sau, int pointy_sau) {
        int startAngle = 0;
        int arcAngle = 0;
        int chieu_Dai_x = 0;
        int chieu_Dai_y;
        int x = 0, y = 0;
        int r;
        // ben phai phia tren x[i]
        if (pointx < pointx_sau && pointy < pointy_sau) {
            chieu_Dai_x = pointx_sau - pointx;
            chieu_Dai_y = pointy_sau - pointy;
            r = (int) Math.sqrt((chieu_Dai_y * chieu_Dai_y) + (chieu_Dai_x * chieu_Dai_x));
            arcAngle = startAngle + 90;
            x = pointx - chieu_Dai_x;
            y = pointy;
        } else if (pointx > pointx_sau && pointy < pointy_sau) {                //phia tren ben trai pointx
            chieu_Dai_x = Math.abs(pointx_sau - pointx);
            chieu_Dai_y = pointy_sau - pointy;
            r = (int) Math.sqrt((chieu_Dai_y * chieu_Dai_y) + (chieu_Dai_x * chieu_Dai_x));
            startAngle = (chieu_Dai_y / r) * 90;
            arcAngle = startAngle - 90;
            x = pointx - chieu_Dai_x * 2;
            y = pointy - chieu_Dai_y;

        } else if (pointx < pointx_sau && pointy > pointy_sau) {                //phia duoi ben trai
            chieu_Dai_x = Math.abs(pointx - pointx_sau);
            chieu_Dai_y = Math.abs(pointy - pointy_sau);
            r = (int) Math.sqrt((chieu_Dai_y * chieu_Dai_y) + (chieu_Dai_x * chieu_Dai_x));
            startAngle = (chieu_Dai_y / r) * 90;
            arcAngle = startAngle - 90;
            x = pointx - chieu_Dai_x;
            y = pointy - chieu_Dai_y * 2;
        } else {                //phi duoi ben phai
            chieu_Dai_x = pointx - pointx_sau;
            chieu_Dai_y = pointy - pointy_sau;
            r = (int) Math.sqrt((chieu_Dai_y * chieu_Dai_y) + (chieu_Dai_x * chieu_Dai_x));
            startAngle = (chieu_Dai_y / r) * 90;
            arcAngle = startAngle + 90;
            x = pointx - chieu_Dai_x * 2;
            y = pointy - chieu_Dai_y;
        }
        if (temp_Canh == 2) {
            g.setColor(Color.black);
            g.drawArc(x, y, chieu_Dai_x * 2, chieu_Dai_y * 2, startAngle, arcAngle);

        }

    }

//    int x_khuyen[] = new int[100];
//    int y_Khuyen[] = new int[100];
//    void Ve(int n, int x_temp, int y_temp, int x[], int y[]) {
//        for (int i = 0; i <= n; i++) {
//            if ((x_temp <= x[i] + 20 && x_temp >= x[i] - 20) && (y_temp <= y[i] + 20 && y_temp >= y[i] - 20)) {
//                x_khuyen[temp_Khungx] = x[i] + 6;
//                y_Khuyen[temp_Khungy] = y[i] + 15;
//                g.setColor(Color.red);
//                g.drawOval(x[i] - 4, y[i] - 4, 24, 24);
//                break;
//            }
//        }
//        if (temp_Khungx > 1) {
//            g.drawArc(n, n, n, temp_n, temp_n, n);
//        }
//        temp_Khungx++;
//        temp_Khungy++;
//    }
    void ve_Khuyen(int n, int x_temp, int y_temp, int x[], int y[]) {
        g.setColor(Color.BLACK);
        for (int i = 0; i <= n; i++) {
            if ((x_temp <= x[i] - resize / 2 + resize && x_temp >= x[i] - resize / 2 - resize) && (y_temp <= y[i] - resize / 2 + resize && y_temp >= y[i] - resize / 2 - resize)) {
                lk_Point[i][i]++;
                So_Bac[i] = So_Bac[i] + 2;
                g.setColor(Color.black);
                if (temp_Khuyen > 0) {
                    x_temp -= resize / 10;
                    y_temp -= resize / 10;
                }
                g.drawArc(x_temp - 5, y_temp - 15, 30 - resize / 2 + resize, 20 - resize / 2 + resize, 0, 360);
                temp_Khuyen++;
                Resize_boll();
                Khuyen_co = true;
                break;
            }
        }

    }

    void Draw_Polygon(Polygon p) {
        g.drawPolygon(plg);
        plg = new Polygon();

    }

    int Tong_bac() {
        int tong = 0;
        for (int i = 0; i <= temp_FillChar; i++) {
            tong += So_Bac[i];
        }
        return tong;
    }

    void AutoLine_vohuong_full(int n, int x[], int y[]) {
        if (n >= 1) {
            g.setColor(Color.black);
            for (int i = 0; i < n; i++) {
                for (int k = i + 1; k <= n; k++) {
                    g.drawLine(x[i] + 6, y[i] + 15, x[k] + 6, y[k] + 15);
//                    g.drawLine(x[k-1] + 6, y[k-1] + 15, x[k] + 6, y[k] + 15);
                }
            }

            g.drawLine(x[0] + 6, y[0] + 15, x[n] + 6, y[n] + 15);
        }
    }

    boolean dem_i(int i, int t_dem[], int temp_t) {
        for (int k = 0; k < temp_t; k++) {
            if (t_dem[k] == i) {
                return false;
            }
        }
        return true;
    }

    //duyet fo thi dfs
    boolean chuaXet[][] = new boolean[100][100];
    int chuoi_DPS[] = new int[100];
    int k = 1;
    int a;

    int chonPoint(int x[], int y[], int x_temp, int y_temp) {
        int pos = 0;
        for (int i = 0; i <= temp_FillChar; i++) {
            if ((x_temp <= x[i] - resize / 2 + resize && x_temp >= x[i] - resize / 2 - resize) && (y_temp <= y[i] - resize / 2 + resize && y_temp >= y[i] - resize / 2 - resize)) {
                pos = i;
                
            }
        }
        return pos;
    }

    void add_chuaXet() {

        for (int i = 0; i <= temp_FillChar; i++) {
            for (int j = 0; j <= temp_FillChar; j++) {
                if (lk_Point[i][j] == 1) {
                    chuaXet[i][j] = true;
                }
            }
        }
    }

    void Dequy(int a) {

        for (int i = 0; i <= temp_FillChar; i++) {
            int h = 1;
            if (chuaXet[a][i]) {
                for (int b = 0; b <= k - 1; b++) {
                    if (chuoi_DPS[b] == (i + 1)) {
                        h = 0;
                    }

                }
                if (h == 1) {

                    chuoi_DPS[k] = i + 1;
                    k++;
                    Dequy(i);
                }
            }
        }
    }

    String DuyetDoThi_DFS(int temp_x, int temp_y) {
        chuoi_DPS[0] = chonPoint(x, y, temp_x, temp_y) + 1;
        a = chonPoint(x, y, temp_x, temp_y);
        String show = "DFS(" + (a + 1) +"):" ;
        add_chuaXet();
        Dequy(a);
        for (int i = 0; i <= k - 1; i++) {
            show += (chuoi_DPS[i]) + " ";
            chuoi_DPS[i] = 0;
        }
        k = 1;
        return show;
    }

    //Duyet Do Thi BFS
    // Số lượng đỉnh của đồ thị
//    int[] chuoi_BFS = new int[100];
    boolean[] chuaXet_dinh;
    Queue<Integer> queue = new ArrayDeque<>();

//    boolean ThamDinh(int p){
//        for(int i = 0; i < 10; i++){
//            if(p == chuoi_BFS[i])
//                return false;
//        }
//        return true;
//    }

    
    String DuyetDoThi_BFS(int temp_x, int temp_y) {
        // DO HOA
        
        
        //THUAT TOAN
        add_chuaXet();
        String show = "";
        int p = chonPoint(x, y, temp_x, temp_y);
        queue.add(p);
        chuaXet_dinh = new boolean[(temp_FillChar + 2)];
        for (int i = 0; i <= temp_FillChar; i++) {
            chuaXet_dinh[i] = true;
        }
        chuaXet_dinh[p] = false;
        show += ("BFS("+(p + 1)+"):" + (p+1) + " ");
        while (!queue.isEmpty()) {
            p = queue.poll();
            for (int i = 0; i <= temp_FillChar; i++) {
                if (chuaXet[p][i]) {
                    if(chuaXet_dinh[i]){
                        queue.add(i);
                        show += (i + 1) + " ";
                        chuaXet_dinh[i] = false;
                    }
                }
            }
        }
        return show;
    }

//    String DuyetDoThi_DFS(int x_temp, int y_temp) {
//        
//        chuoi_DPS[0] = chonPoint(x, y, x_temp, y_temp);
//        int chon = chuoi_DPS[0];
//        int temp_dfs = 0;
//        add_chuaXet();
//        for (int i = 0; i <= temp_FillChar; i++) {
//            for (int j = 0; j <= temp_FillChar; j++) {
//                System.out.println("CHuoichuxet:"+i+j+" =" + chuaXet[j]);
//            }
//        }
//        System.out.println("temp_ke_value: i,j =" +temp_ke);
//        System.out.println("phai bang 1 =" +temp_tro[chuoi_DPS[temp_dfs]]);
//        int t = 0;
//        int temp_t = 0;
//        while(temp_dfs <= temp_ke){
//            for (int j = temp_t ; j <= temp_tro[chuoi_DPS[t]]; j++) {
//                System.out.println("tempchuoi: i,j =" +chuoi_DPS[t]);
//                System.out.println("tem_tro = " + temp_tro[chuoi_DPS[t]]);
//                System.out.println("tem_ke = " + temp_ke);
//                System.out.println("chu"+chuaXet[chuoi_DPS[t]]);
//                if(chuaXet[chuoi_DPS[t]] && kiem_Ke_trung(chuoi_DPS, t, ke[chuoi_DPS[t]][j])) {   
//                    chuaXet[chuoi_DPS[t]] = false;
//                    int a = ke[chuoi_DPS[t]][j];
//                    t++;
//                    chuoi_DPS[t] = a;
//                }
//                System.out.println("j = " + j);
//                System.out.println("temp = " + temp_dfs);
//            }
//            temp_dfs++;
//        }
//        String show = "";
//        for(int i = 0; i <= t; i++){
//            show += (chuoi_DPS[i] + 1) + " ";
//            
//        }
//        return show;
//    }
//    
//    String Chuoi_DSk(){
//                String chuoi_Input = "";
//        for(int i = 0; i <= temp_i; i++){
//            chuoi_Input += (i_dem[ds[i].head.i] + "-" + i_dem[ds[i - 1].head.i - 1]);
//        }
//        return chuoi_Input;
//    }
    
    
    void fill_mau_dinh(int x_temp, int y_temp, Color c){
        boolean chon = false;
        int dem_i = 0;
        for (int i = 0; i <= temp_FillChar; i++) {
            if ((x_temp <= x[i] - resize / 2 + resize && x_temp >= x[i] - resize / 2 - resize) && (y_temp <= y[i] - resize / 2 + resize && y_temp >= y[i] - resize / 2 - resize)) {
                chon = chon || true;
                dem_i = i;
            }
        }
        if(chon){
            g.setColor(c);
            g.fillOval(x[dem_i] - resize / 2, y[dem_i] - resize / 2, resize, resize);
            fillChar(x[dem_i], y[dem_i], dem_i);
        }else{
            g.setColor(Color.black);
            g.fillOval(x[dem_i] - resize / 2, y[dem_i] - resize / 2, resize, resize);
            fillChar(x[dem_i], y[dem_i], dem_i);
        }
    }
    
    void fill_mau_all_dinh(int x_temp, int y_temp){
        for(int i = 0; i <= temp_FillChar; i++){
            g.setColor(Color.black);
            g.fillOval(x[i] - resize / 2, y[i] - resize / 2, resize, resize);
            fillChar(x[i], y[i], i);
        }
    }
    void fillChar(int x, int y, int temp) {
        String str = (temp + 1) + "";
        Font baseFont = g.getFont();
        baseFont = baseFont.deriveFont(16f);
        Font monospaceFont = new Font("Monospaced", Font.PLAIN, 14 + resize / 20);
        g.setFont(monospaceFont);
        g.setColor(Color.white);
        g.drawString(str, x - 3, y + 6);
    }

    void fillChar_canh(int x, int y, int temp, Color c) {
        String str = "e" + (temp + 1) + "";
        Font baseFont = g.getFont();
        baseFont = baseFont.deriveFont(16f);
        Font monospaceFont = new Font("Monospaced", Font.BOLD, 14 + resize / 20);
        g.setFont(monospaceFont);
        g.setColor(c);
        g.drawString(str, x - 3, y + 6);
    }
}
