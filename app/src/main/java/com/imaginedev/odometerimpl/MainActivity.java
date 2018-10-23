package com.imaginedev.odometerimpl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.imaginedev.odometerview.OdometerView;

/**
 * @author batman  https://github.com/imagineDev
 */
public class MainActivity extends AppCompatActivity {

    private OdometerView odometer;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        odometer = findViewById(R.id.odometer);
        input = findViewById(R.id.input);

    }

    public void onBtnPressed(View view) {

        String text = input.getText().toString();

        if (text.isEmpty()) {
            odometer.setValue(0, true);
        } else {

            try {

                double value = Double.parseDouble(text);
                odometer.setValue(value, true);

            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "NaN", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem mn = menu.add(0, 0, 0, "Github");
        mn.setIcon(R.drawable.ic_github);
        mn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mn.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/imagineDev"));
                startActivity(intent);

                return false;
            }
        });

        return true;
    }
}
