package dental.mahmoud.adnan.dental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    DatabaseAdapter adapter;
    EditText login_username, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        adapter = new DatabaseAdapter(this);
        adapter = adapter.open();

        login_username = (EditText)findViewById(R.id.Login_username);
        login_password = (EditText)findViewById(R.id.Login_password);


    }

    public void RegisterActivity(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    public void LoginButton(View view) {
        String storedPassword=adapter.getSinlgeEntry(login_username.getText().toString());
        if (login_username.getText().toString().equals("admin") && login_password.getText().toString().equals("admin123")){

            Intent intent1 = new Intent(Login.this, Admin.class);
            startActivity(intent1);
        }
        else if(login_password.getText().toString().equals(storedPassword)) {
                Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                Intent in = new Intent(Login.this, Diagnose.class);
                startActivity(in);
        }
        else
        {
                Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }

    }

}
