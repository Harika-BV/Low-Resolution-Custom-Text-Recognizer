package com.example.u.opencv_android;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ChooseImageActivity extends AppCompatActivity {

    Button chooseImage, next;
    ImageView imageView;
    Bitmap bitmap;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        chooseImage = findViewById(R.id.chooseImage);
        next = findViewById(R.id.next);
        imageView = findViewById(R.id.image);


        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            //START REQUEST PERMISSION
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                        } else {
                            //ELSE BELOW START OPEN PICKER
                            CropImage.startPickImageActivity(ChooseImageActivity.this);
                        }
                    }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseImageActivity.this, MainActivity.class));
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //THIS IS HAPPEN WHEN USER CLICK ALLOW ON PERMISSION
            //START PICK IMAGE ACTIVITY
            CropImage.startPickImageActivity(ChooseImageActivity.this);
        }
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setMultiTouchEnabled(false)
                    //REQUEST COMPRESS SIZE
                    .setInitialCropWindowRectangle(new Rect(0,0,585,645))
                    .setRequestedSize(585, 645)
                    //ASPECT RATIO, DELETE IF YOU NEED CROP ANY SIZE
                   .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //GET CROPPED IMAGE URI AND PASS TO IMAGEVIEW
                imageView.setImageURI(result.getUri());

                imageView.invalidate();
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                TempStoreImage.setResult_image(bitmap);

            }
        }
    }
}
