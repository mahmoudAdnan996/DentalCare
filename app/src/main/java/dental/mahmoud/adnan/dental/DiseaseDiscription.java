package dental.mahmoud.adnan.dental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiseaseDiscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_discription_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
