package dental.mahmoud.adnan.dental;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Diagnose extends AppCompatActivity {

    SymptomsDatabaseAdapter myDB;
    ArrayList<String> symptomList = new ArrayList<>();
    ArrayList<String> Tooth_abscess = new ArrayList<>();
    ArrayList<String> Dental_plaque = new ArrayList<>();
    final ArrayList<String> newList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnose_activity);


        ListView diagnose_listview = (ListView)findViewById(R.id.Diagnose_listview);
        diagnose_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myDB = new SymptomsDatabaseAdapter(this);

        newList.add("Pain when chewing");
        newList.add("Sensitivity of the teeth to heat cold or pressure");
        newList.add("Visible boil in the gums near a tooth");
        newList.add("Pus drainage in the mouth");
        newList.add("Foul taste in mouth and bad breath");
        newList.add("Readness and swelling of the gums,jaw or face");
        newList.add("Difficulty fully opening the mouth or swallowing");
        newList.add("Swollen lymph nodes in the neck");
        newList.add("Demineralization of the tooth enamel");
        newList.add("Leads to teeth decay and dental cavities");
        newList.add("Irritation of the gum around the teeth");
        newList.add("Periodontal disease and tooth loss");
        newList.add("Tooth plaque becomes mineralized and hardens");
        newList.add("Include bad breath and oral trash");

        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "Dentist must enter symptom first",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                newList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_checked,newList);
                diagnose_listview.setAdapter(listAdapter);
            }
        }

        // tooth abscess arraylist
        Tooth_abscess.add("Pain when chewing");
        Tooth_abscess.add("Sensitivity of the teeth to heat cold or pressure");
        Tooth_abscess.add("Visible boil in the gums near a tooth");
        Tooth_abscess.add("Pus drainage in the mouth");
        Tooth_abscess.add("Foul taste in mouth and bad breath");
        Tooth_abscess.add("Readness and swelling of the gums,jaw or face");
        Tooth_abscess.add("Difficulty fully opening the mouth or swallowing");
        Tooth_abscess.add("Swollen lymph nodes in the neck");

        // dental plaque arraylist
        Dental_plaque.add("Demineralization of the tooth enamel");
        Dental_plaque.add("Leads to teeth decay and dental cavities");
        Dental_plaque.add("Irritation of the gum around the teeth");
        Dental_plaque.add("Periodontal disease and tooth loss");
        Dental_plaque.add("Tooth plaque becomes mineralized and hardens");
        Dental_plaque.add("Include bad breath and oral trash");


//        final ArrayList<String> theList = new ArrayList<>();

        diagnose_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selected_symptom = parent.getItemAtPosition(position).toString();
                if (symptomList.contains(selected_symptom)){
                    symptomList.remove(selected_symptom);
                }else {
                    symptomList.add(selected_symptom);
                }
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alertdialoge = new AlertDialog.Builder(Diagnose.this);
                alertdialoge.setTitle("Predicted Disease..");

                String symptoms = "";
                for (String symptom : symptomList){
                    symptoms += symptom + "," + "\n";
                }
                if (Tooth_abscess.containsAll(symptomList)){
                    alertdialoge.setMessage("Your Disease is Tooth abscess");
                }
                else if (Dental_plaque.containsAll(symptomList)){
                    alertdialoge.setMessage("Your Disease is Dental plaque");
                }
                else {
                    alertdialoge.setMessage("may be another disease");
                }

//                String [] splitSymptoms = symptoms.split(",");


                alertdialoge.setNegativeButton("Description", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Diagnose.this, DiseaseDiscription.class);
                        startActivity(intent);
                    }
                });
                alertdialoge.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "You saved disease", Toast.LENGTH_LONG).show();
                    }
                });
                alertdialoge.create();
                alertdialoge.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_diagnose, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Diagnose_logout:
                Intent intent1 = new Intent(Diagnose.this, Login.class);
                startActivity(intent1);
                finish();
                break;
        }
        return true;
    }

}
