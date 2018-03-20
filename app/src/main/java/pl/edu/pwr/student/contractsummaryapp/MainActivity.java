package pl.edu.pwr.student.contractsummaryapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button nextButton = (Button) findViewById(R.id.buttonNext);
        final CheckBox docCheckBox = (CheckBox) findViewById(R.id.checkBoxDoc);
        final CheckBox photoCheckBox = (CheckBox) findViewById(R.id.checkBoxPhoto);
        final Context context = this;
        final Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (docCheckBox.isChecked()) {

                }
                else if (photoCheckBox.isChecked()) {
                    startActivity(intent);
                }
                else if (!docCheckBox.isChecked() && !photoCheckBox.isChecked()) {
                    Toast.makeText(context, "Wybierz typ pliku", Toast.LENGTH_LONG);
                }
            }
        });
    }

}
