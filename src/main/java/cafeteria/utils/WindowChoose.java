/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.utils;

import java.awt.Dimension;
import javax.swing.JFileChooser;

/**
 *
 * @author SuongNguyen
 */
public class WindowChoose extends JFileChooser {

    public static String path;
    public static String nameFile;
    private static JFileChooser file = new JFileChooser();
    private static Dimension size = new Dimension(700, 500);

    public static boolean openSave() {
        file.setPreferredSize(size);
        int a = file.showOpenDialog(null);
        if (a == JFileChooser.APPROVE_OPTION) {
            path = file.getSelectedFile().getAbsolutePath();
            nameFile = file.getSelectedFile().getName();
            return true;
        }
        return false;
    }

    public static boolean openChoose() {
        file.setPreferredSize(size);
        int a = file.showOpenDialog(null);
        if (a == JFileChooser.APPROVE_OPTION) {
            path = file.getSelectedFile().getAbsolutePath();
            nameFile = file.getSelectedFile().getName();
            return true;
        }
        return false;
    }
}
