package kernel.utils;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class UIAdjistUtils {
    public static void adjustImageBorder(ImageView image, Rectangle border) {

//        System.out.println(image.getImage().getHeight());
        Double height = image.getFitHeight();
        Double width = image.getFitWidth();
        border.setWidth(width + 15);
        border.setHeight(height + 15);
//        System.out.println("更新border的大小" + "fitHeight = " + image.getFitHeight() + " fitWidth = " + image.getFitWidth());
    }
}
