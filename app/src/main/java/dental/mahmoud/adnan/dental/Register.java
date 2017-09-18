package dental.mahmoud.adnan.dental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText Email, Username, Password, RPassword, Age, Number;
    DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter = databaseAdapter.open();

        Email = (EditText)findViewById(R.id.Register_email);
        Username = (EditText)findViewById(R.id.Register_username);
        Password = (EditText)findViewById(R.id.Register_password);
        RPassword = (EditText)findViewById(R.id.Register_retypePass);
        Age = (EditText)findViewById(R.id.Register_age);
        Number = (EditText)findViewById(R.id.Register_tele);


    }

    public void register(View view) {

        String e_mail = Email.getText().toString();
        String name = Username.getText().toString();
        String pass = Password.getText().toString();
        String rpass = RPassword.getText().toString();
        int age = Integer.parseInt(Age.getText().toString());
        int num = Integer.parseInt(Number.getText().toString());
        if(e_mail.equals("")||name.equals("")||pass.equals("")||rpass.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;
        }
        if(!pass.equals(rpass))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            // Save the Data in Database
            databaseAdapter.insertEntry(e_mail, name, pass,age,num);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Register.this, Diagnose.class);
            startActivity(intent);
        }



    }
}
