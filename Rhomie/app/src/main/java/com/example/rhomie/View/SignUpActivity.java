package com.example.rhomie.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rhomie.Controller.ISignUpController;
import com.example.rhomie.Controller.SignUpController;
import com.example.rhomie.R;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {
    private EditText first_name, last_name, id, phone_number, email, password;
    private ISignUpController controllerSignUp;
    private ProgressBar progressBar;
    private Spinner spinner;
    private String selectedText;
    private int user;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        controllerSignUp = new SignUpController(this);

        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.phone_number_array,R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedText = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        first_name = findViewById(R.id.edtxtFirstName);
        last_name = findViewById(R.id.edtxtLastName);
        id = findViewById(R.id.edtxtId);
        phone_number = findViewById(R.id.edtxtPhoneNumber);
        email = findViewById(R.id.edtxtEmail);
        password = findViewById(R.id.edtxtPassword);

        Intent intent = this.getIntent();
        if(intent != null)
            user = intent.getIntExtra("user",0);

        progressBar = findViewById(R.id.progressBar);
    }

    public void sendClick(View view){
        String f_nameS = first_name.getText().toString();
        String l_nameS = last_name.getText().toString();
        String idS = id.getText().toString();
        String phone_numberS = selectedText + phone_number.getText().toString();
        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        //calls to the controller the dos the rest.
        controllerSignUp.OnSignUp(user,f_nameS,l_nameS,idS,phone_numberS,emailS,passwordS);

    }

    @Override
    public void signUpSuccess(int choose, String massage) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();

        if(user == -1)
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
        else if (user == 1) {
            Intent intent = new Intent(SignUpActivity.this, AddItemActivity.class);
            intent.putExtra("choose",true);
            startActivity(intent);
        }
    }

    @Override
    public void signUpError(String massage) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(SignUpActivity.this, massage, Toast.LENGTH_SHORT).show();
    }
}