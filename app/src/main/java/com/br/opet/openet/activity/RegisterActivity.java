package com.br.opet.openet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.opet.openet.R;
import com.br.opet.openet.model.UserModel;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends NoBarActivity implements View.OnClickListener {

    private EditText name, lastName, userName, email, password, confirmPassword;
    private Button register;
    private EditText nascimento;
    private Date dtNascimento;
    private Spinner curso;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        instanciateScreenObjects();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dtNascimentoEditText:
                showDialog(DATE_DIALOG_ID);
                break;
            default:
                validateFields();
                break;
        }
    }

    private Boolean validateFields() {
        boolean valid = true;

        if (name.getText().toString().trim().isEmpty()) {
            name.setError("Favor informar um nome");
            valid = false;
        } else {
            if (name.getText().toString().trim().length() < 3) {
                name.setError("O nome deve ter mais que 3 caracteres");
                valid = false;
            }
        }

        if (lastName.getText().toString().trim().isEmpty()) {
            lastName.setError("Favor informar um sobrenome");
            valid = false;
        } else {
            if (lastName.getText().toString().trim().length() < 3) {
                lastName.setError("O sobrenome deve ter mais que 3 caracteres");
                valid = false;
            }
        }

        if (userName.getText().toString().trim().isEmpty()) {
            userName.setError("Favor informar um nome de usuário");
            valid = false;
        } else {
            if (userName.getText().toString().trim().length() < 3) {
                userName.setError("O usuário deve ter mais que 3 caracteres");
                valid = false;
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Favor informar um email válido");
            valid = false;
        }

        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Favor informar uma senha.");
            valid = false;
        } else {
            if (password.getText().toString().trim().length() > 6) {
                password.setError("A senha deve ter mais que 6 caracteres");
                valid = false;
            }
        }

        if (confirmPassword.getText().toString().trim().isEmpty()) {
            confirmPassword.setError("Favor informar uma senha.");
            valid = false;
        } else {
            if (confirmPassword.getText().toString().trim().equals(password.getText().toString().trim())) {
                confirmPassword.setError("A senha deve ter mais que 6 caracteres");
                valid = false;
            }
        }

        System.out.println(curso.getSelectedItem().toString());

        return valid;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, ano, mes,
                        dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            (view, year, monthOfYear, dayOfMonth) -> {
                String data = dayOfMonth + "/" + (monthOfYear < 10 ? "0" : "") + (monthOfYear + 1) + "/" + year;
                nascimento.setText(data);
            };

    private void instanciateScreenObjects() {
        name = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        userName = findViewById(R.id.userNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.cpasswordEditText);

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);
        nascimento = findViewById(R.id.dtNascimentoEditText);
        nascimento.setOnClickListener(this);

        curso = findViewById(R.id.coursesSpinner);
    }

}