package dental.mahmoud.adnan.dental;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    SymptomsDatabaseAdapter myDB;
    EditText symptom_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);


        symptom_edit = (EditText)findViewById(R.id.Admin_txtSymptoms);

        myDB = new SymptomsDatabaseAdapter(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String symptoms = symptom_edit.getText().toString();
                if (symptom_edit.length()!=0){

                    myDB.addData(symptoms);
                    symptom_edit.setText("");
                    Toast.makeText(getBaseContext(), "Data Enterd Successfully", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "Empty Text", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent1 = new Intent(Admin.this, Diagnose.class);
        startActivity(intent1);
        finish();
        return true;
    }
}
