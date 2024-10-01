package MovieReview.GUI;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageRenderer {
    
    public static ImageIcon renderImage(String Path, int Height, int Width)
    {
        Image image = new ImageIcon(Path).getImage();
        Image newimg = image.getScaledInstance(Height, Width,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

}
