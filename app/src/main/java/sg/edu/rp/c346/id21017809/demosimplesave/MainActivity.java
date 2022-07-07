package sg.edu.rp.c346.id21017809.demosimplesave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnRestore;
    EditText etText;

    //are you able to see this


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnRestore = findViewById(R.id.btnRestore);
        etText = findViewById(R.id.etText);

//To save the data keyed in by user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedData = etText.getText().toString();

                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("value", savedData);
                prefEdit.commit();
            }
        });
//To restore the data that has been saved by the user
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                String loadedData = prefs.getString("value", "No greetings!");
                etText.setText(loadedData);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        String savedData = etText.getText().toString();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("value", savedData);
        prefEdit.commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String loadedData = prefs.getString("value", "No greetings!");
        etText.setText(loadedData);
//if want to align more to the ws. without it its fine
        Toast.makeText(this, loadedData, Toast.LENGTH_SHORT).show();
    }
}