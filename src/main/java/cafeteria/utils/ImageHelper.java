/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafeteria.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

public class ImageHelper {
    
    public static ImageIcon getImage(String nameImage, int width, int height) {
        try {
            ImageIcon im = new ImageIcon(ImageHelper.class.getResource("/images/product/"+nameImage));
            Image i = im.getImage().getScaledInstance(width, height, 0);
            return new ImageIcon(i);
        } catch(Exception ex) {
            return null;
        }
    }
    
    public static void copy(String path) throws IOException, URISyntaxException {
        String destinationPath = ImageHelper.class.getResource("/images/product/").toURI().getPath();
        File from = new File(path);
        File to = new File(destinationPath+"/"+from.getName());
        if (to.exists()) return;
        Files.copy(from.toPath(), to.toPath());
    }
    
    public static void delete(String nameImage) throws IOException, URISyntaxException {
        File file = Paths.get(ImageHelper.class.getResource("/images/product/"+nameImage).toURI()).toFile();;
        if (!file.exists()) return;
        Files.delete(file.toPath());
    }
}
