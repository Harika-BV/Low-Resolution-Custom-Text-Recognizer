package com.example.u.opencv_android;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MainActivity extends AppCompatActivity {

    Bitmap[] bitmap = new Bitmap[55];
    private static final String TAG ="MainActivity" ;
    int row_start, row_end, col_start, col_end;
    Button process;
    Mat img, img_result, opening, closing,or_image, img_copy, img_bitmap;
    ImageView imageView;
    private MyTestOCR mTessOCR;
    String[] extract_text = new String[55];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();

        process = findViewById(R.id.process);
        imageView = findViewById(R.id.image);

        img_bitmap = new Mat();
        opening = new Mat();
        closing = new Mat();
        or_image = new Mat();
        img_result = new Mat();
        img = new Mat();
        img_copy = new Mat();


        Utils.bitmapToMat(TempStoreImage.getResult_image(), img);
        img_copy = img.clone();
        Log.d("SIZE", img_copy.width() +"-"+img_copy.height());


        mTessOCR = new MyTestOCR(MainActivity.this);

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=1;i<=54;i++){
                    initialize_coordinates(i);
                    int interpolation = Imgproc.INTER_AREA;
                    Imgproc.resize(img, img, new Size(500,500), interpolation);
                    Mat imag_crop = img_copy.submat(row_start,row_end,col_start,col_end);
                    Imgproc.resize(imag_crop, imag_crop, new Size(300,300), interpolation);
                    bitmap[i] = Bitmap.createBitmap(300, 300,Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(imag_crop,bitmap[i]);
                }
                for (int i=1; i<=54;i++){
                    Utils.bitmapToMat(bitmap[i], img_bitmap);
                    image_enhancement(img_bitmap, i);
                }
                writetoexcel(extract_text);
            }
        });
    }

    public void initialize_coordinates(int i){
         if (i==1){
            row_start   = 70;
            row_end     = 140;
            col_start   = 180;
            col_end     = 230;
        }
        else if (i==2){
            row_start   = 70;
            row_end     = 140;
            col_start   = 230;
            col_end     = 285;
        }
        else if (i==3){
            row_start   = 70;
            row_end     = 140;
            col_start   = 302;
            col_end     = 350;
        }
        else if (i==4){
            row_start   = 70;
            row_end     = 140;
            col_start   = 360;
            col_end     = 410;
        }

        else if (i==5){
            row_start   = 140;
            row_end     = 210;
            col_start   = 120;
            col_end     = 180;
        }
        else if (i==6){
            row_start   = 140;
            row_end     = 210;
            col_start   = 180;
            col_end     = 230;
        }
        else if (i==7){
            row_start   = 140;
            row_end     = 210;
            col_start   = 230;
            col_end     = 290;
        }
        else if (i==8){
            row_start   = 140;
            row_end     = 210;
            col_start   = 302;
            col_end     = 350;
        }
        else if (i==9){
            row_start   = 140;
            row_end     = 210;
            col_start   = 360;
            col_end     = 410;
        }
        else if (i==10){
            row_start   = 140;
            row_end     = 210;
            col_start   = 410;
            col_end     = 470;
        }

        else if (i==11){
            row_start   = 210;
            row_end     = 270;
            col_start   = 60;
            col_end     = 120;
        }

        else if (i==12){
            row_start   = 210;
            row_end     = 270;
            col_start   = 120;
            col_end     = 180;
        }

        else if (i==13){
            row_start   = 210;
            row_end     = 270;
            col_start   = 180;
            col_end     = 230;
        }

        else if (i==14){
            row_start   = 210;
            row_end     = 270;
            col_start   = 230;
            col_end     = 290;
        }

        else if (i==15){
            row_start   = 210;
            row_end     = 270;
            col_start   = 302;
            col_end     = 350;
        }

        else if (i==16){
            row_start   = 210;
            row_end     = 270;
            col_start   = 360;
            col_end     = 410;
        }

        else if (i==17){
            row_start   = 210;
            row_end     = 270;
            col_start   = 410;
            col_end     = 470;
        }

        else if (i==18){
            row_start   = 210;
            row_end     = 270;
            col_start   = 470;
            col_end     = 530;
        }

        else if (i==19){
            row_start   = 280;
            row_end     = 350;
            col_start   = 60;
            col_end     = 120;
        }

        else if (i==20){
            row_start   = 280;
            row_end     = 350;
            col_start   = 120;
            col_end     = 180;
        }

        else if (i==21){
            row_start   = 280;
            row_end     = 350;
            col_start   = 180;
            col_end     = 240;
        }

        else if (i==22){
            row_start   = 280;
            row_end     = 350;
            col_start   = 230;
            col_end     = 290;
        }

        else if (i==23){
            row_start   = 280;
            row_end     = 350;
            col_start   = 303;
            col_end     = 350;
        }

        else if (i==24){
            row_start   = 280;
            row_end     = 350;
            col_start   = 360;
            col_end     = 410;
        }

        else if (i==25){
            row_start   = 280;
            row_end     = 350;
            col_start   = 410;
            col_end     = 460;
        }

        else if (i==26){
            row_start   = 280;
            row_end     = 350;
            col_start   = 470;
            col_end     = 530;
        }

        else if (i==27){
            row_start   = 280;
            row_end     = 350;
            col_start   = 530;
            col_end     = 585;
        }

        else if (i==28){
            row_start   = 325;
            row_end     = 365;
            col_start   = 60;
            col_end     = 120;
        }

        else if (i==29){
            row_start   = 325;
            row_end     = 365;
            col_start   = 120;
            col_end     = 180;
        }

        else if (i==30){
            row_start   = 325;
            row_end     = 365;
            col_start   = 180;
            col_end     = 240;
        }

        else if (i==31){
            row_start   = 325;
            row_end     = 365;
            col_start   = 230;
            col_end     = 290;
        }

        else if (i==32){
            row_start   = 325;
            row_end     = 365;
            col_start   = 303;
            col_end     = 350;
        }

        else if (i==33){
            row_start   = 325;
            row_end     = 365;
            col_start   = 360;
            col_end     = 410;
        }

        else if (i==34){
            row_start   = 325;
            row_end     = 365;
            col_start   = 410;
            col_end     = 460;
        }

        else if (i==35){
            row_start   = 325;
            row_end     = 365;
            col_start   = 470;
            col_end     = 530;
        }

        else if (i==36){
            row_start   = 325;
            row_end     = 365;
            col_start   = 530;
            col_end     = 585;
        }

        else if (i==37){
            row_start   = 380;
            row_end     = 430;
            col_start   = 60;
            col_end     = 120;
        }

        else if (i==38){
            row_start   = 380;
            row_end     = 430;
            col_start   = 120;
            col_end     = 180;
        }

        else if (i==39){
            row_start   = 380;
            row_end     = 430;
            col_start   = 180;
            col_end     = 230;
        }

        else if (i==40){
            row_start   = 380;
            row_end     = 430;
            col_start   = 230;
            col_end     = 290;
        }

        else if (i==41){
            row_start   = 380;
            row_end     = 430;
            col_start   = 302;
            col_end     = 350;
        }

        else if (i==42){
            row_start   = 380;
            row_end     = 430;
            col_start   = 360;
            col_end     = 410;
        }

        else if (i==43){
            row_start   = 380;
            row_end     = 430;
            col_start   = 410;
            col_end     = 470;
        }

        else if (i==44){
            row_start   = 380;
            row_end     = 430;
            col_start   = 470;
            col_end     = 530;
        }
        else if (i==45){
            row_start   = 140;
            row_end     = 210;
            col_start   = 120;
            col_end     = 180;
        }
        else if (i==46){
            row_start   = 140;
            row_end     = 210;
            col_start   = 180;
            col_end     = 230;
        }
        else if (i==47){
            row_start   = 140;
            row_end     = 210;
            col_start   = 230;
            col_end     = 290;
        }
        else if (i==48){
            row_start   = 140;
            row_end     = 210;
            col_start   = 302;
            col_end     = 350;
        }
        else if (i==49){
            row_start   = 140;
            row_end     = 210;
            col_start   = 360;
            col_end     = 410;
        }
        else if (i==50){
            row_start   = 140;
            row_end     = 210;
            col_start   = 410;
            col_end     = 470;
        }
        else if (i==51){
            row_start   = 70;
            row_end     = 140;
            col_start   = 180;
            col_end     = 230;
        }
        else if (i==52){
            row_start   = 70;
            row_end     = 140;
            col_start   = 230;
            col_end     = 285;
        }
        else if (i==53){
            row_start   = 70;
            row_end     = 140;
            col_start   = 302;
            col_end     = 350;
        }
        else if (i==54){
            row_start   = 510;
            row_end     = 565;
            col_start   = 360;
            col_end     = 410;
        }
    }


    public void image_enhancement(Mat img, int i){
        //Resize

        int interpolation = Imgproc.INTER_LINEAR;
        Imgproc.resize(img, img, new Size(500,500), interpolation);

        //Color to Gray scale
        Imgproc.cvtColor(img, img_result, Imgproc.COLOR_RGB2GRAY);
        img = img_result.clone();
        //Adaptive thresholding
        Imgproc.GaussianBlur(img_result,img_result, new Size(5,5), 0);
        Imgproc.adaptiveThreshold(img_result, img_result, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 31,2);

        //Morphology
        Mat kernel = new Mat(new Size(10, 10), CvType.CV_8UC1, new Scalar(250));
        Imgproc.morphologyEx(img_result, opening, Imgproc.MORPH_OPEN, kernel);
        Imgproc.morphologyEx(opening, closing, Imgproc.MORPH_CLOSE, kernel);

        //Bitwise_OR
        Core.bitwise_or(img, closing, or_image);

        Bitmap img_bitmap = Bitmap.createBitmap(closing.cols(), closing.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(closing, img_bitmap);
        imageView.setImageBitmap(img_bitmap);


        String temp = mTessOCR.getOCRResult(img_bitmap);
        Log.d("TEXT", "text- "+temp);
        extract_text[i] = temp;
    }

    public void writetoexcel(String[] text){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            return;
        }

        File sd = Environment.getExternalStorageDirectory();
        String csvFile = "myData.xls";

        File directory = new File(sd.getAbsolutePath());
        //create directory if not exist
        if (!directory.isDirectory()) {
            directory.mkdirs();
        }
        try {

            //file path
            File file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(file, wbSettings);
            WritableSheet sheet = workbook.createSheet("userList", 0);

            for (int i=0;i<55;i++) {
                sheet.addCell(new Label(0, i, Integer.toString(i)));
                sheet.addCell(new Label(1, i, text[i]));
            }

            workbook.write();
            workbook.close();
            Toast.makeText(getApplication(),
                    "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();



        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
