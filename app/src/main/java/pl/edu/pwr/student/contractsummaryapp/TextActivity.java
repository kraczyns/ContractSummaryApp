package pl.edu.pwr.student.contractsummaryapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        this.textView = (TextView) findViewById(R.id.processedText);

        Intent cameraIntent = getIntent();
        Bundle bundle = cameraIntent.getBundleExtra("EXTRA_PHOTOS");
        ArrayList<Bitmap> document = bundle.getParcelableArrayList("EXTRA_PHOTOS");

        if(!document.isEmpty()) {
            Toast.makeText(this, "Przetworzono " + document.size() + "stron.", Toast.LENGTH_LONG);
            for(Bitmap page : document) {
                TextExtractor textExtractor = new TextExtractor(page);
                try {
                    textExtractor.extractText();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView.append(textExtractor.getRecognizedText());
            }
        }
    }
}
