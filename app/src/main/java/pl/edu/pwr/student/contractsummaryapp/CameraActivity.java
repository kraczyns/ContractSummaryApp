package pl.edu.pwr.student.contractsummaryapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private Boolean isFirstPage;
    private ImageView imageView;
    private Button endOfDocument;
    private Button nextImage;
    private Button firstPage;
    private ArrayList<Bitmap> document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        this.endOfDocument = (Button) findViewById(R.id.endOfDocumentButton);
        this.nextImage = (Button) findViewById(R.id.nextPageButton);
        this.firstPage = (Button) findViewById(R.id.firstPageButton);
        this.imageView = (ImageView) findViewById(R.id.currentImage);
        this.document = new ArrayList<Bitmap>();

        firstPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstPage = true;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        endOfDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textIntent = new Intent(CameraActivity.this, TextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("EXTRA_PHOTOS", document);
                textIntent.putExtra("EXTRA_PHOTOS", bundle);
                startActivity(textIntent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            if (isFirstPage) {
                imageView.setVisibility(View.VISIBLE);
                firstPage.setVisibility(View.INVISIBLE);
                nextImage.setVisibility(View.VISIBLE);
                endOfDocument.setVisibility(View.VISIBLE);
                isFirstPage = false;
            }
            imageView.setImageBitmap(photo);
            document.add(photo);
        }
    }
}
