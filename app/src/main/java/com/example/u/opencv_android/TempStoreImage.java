package com.example.u.opencv_android;

        import android.graphics.Bitmap;

        import org.opencv.core.Mat;

public class TempStoreImage {

    private static Bitmap image_choosen;

    static void setResult_image(Bitmap image_choosen){
        TempStoreImage.image_choosen = image_choosen;
    }
    static Bitmap getResult_image(){
        return image_choosen;
    }


}
