package com.br.opet.openet.activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.br.opet.openet.R;
import com.br.opet.openet.listener.CourseServiceResponseListener;
import com.br.opet.openet.listener.UserServiceResponseListener;
import com.br.opet.openet.model.CourseModel;
import com.br.opet.openet.model.UserModel;
import com.br.opet.openet.model.dto.RequestUserAuthDTO;
import com.br.opet.openet.model.dto.UserRegisterDTO;
import com.br.opet.openet.service.impl.CourseServiceImpl;
import com.br.opet.openet.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends NoBarActivity implements View.OnClickListener {

    private EditText name, userName, email, password, confirmPassword;
    private Button register;
    private EditText nascimento;
    private Spinner curso;
    private ArrayList<CourseModel> cursos;

    private UserServiceImpl userService;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        instanciateScreenObjects();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dtNascimentoEditText:
                showDialog(DATE_DIALOG_ID);
                break;
            default:
                if (validateFields()) {
                    String[] splits = nascimento.getText().toString().split("/");
                    UserRegisterDTO requestUserAuth = new UserRegisterDTO(
                            name.getText().toString(),
                            userName.getText().toString(),
                            password.getText().toString(),
                            email.getText().toString(),
                            cursos.stream().filter(c -> c.getCourse().equals(curso.getSelectedItem().toString())).findFirst().get().getId(),
                            splits[2] + "-" + splits[1] + "-" + splits[0]
                    );
                    
                    try {
                        userService.createUser(this, requestUserAuth, new UserServiceResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(RegisterActivity.this, "Não foi possível realizar o cadastro.", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(UserModel userModelResponse) {
                                Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
            if (password.getText().toString().trim().length() < 6) {
                password.setError("A senha deve ter mais que 6 caracteres");
                valid = false;
            }
        }

        if (confirmPassword.getText().toString().trim().isEmpty()) {
            confirmPassword.setError("Favor informar uma senha.");
            valid = false;
        } else {
            if (!confirmPassword.getText().toString().trim().equals(password.getText().toString().trim())) {
                confirmPassword.setError("A senha deve ter mais que 6 caracteres");
                valid = false;
            }
        }

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
                String data = dayOfMonth + "/" + (monthOfYear < 9 ? "0" : "") + (monthOfYear + 1) + "/" + year;
                nascimento.setText(data);
            };

    private void instanciateScreenObjects() {
        name = findViewById(R.id.firstNameEditText);
        userName = findViewById(R.id.userNameEditText);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.cpasswordEditText);

        register = findViewById(R.id.registerButton);
        register.setOnClickListener(this);
        nascimento = findViewById(R.id.dtNascimentoEditText);
        nascimento.setOnClickListener(this);

        curso = findViewById(R.id.coursesSpinner);
        userService = new UserServiceImpl();
        loadCourses();
    }

    private void loadCourses() {
        try {
            CourseServiceImpl service = new CourseServiceImpl();
            service.listCourses(this, new CourseServiceResponseListener() {
                @Override
                public void onError(String message) {
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(ArrayList<CourseModel> courses) {
                    cursos = courses;
                    ArrayAdapter<CourseModel> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, cursos);
                    curso.setAdapter(adapter);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}