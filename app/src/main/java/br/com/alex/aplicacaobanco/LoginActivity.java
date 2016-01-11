package br.com.alex.aplicacaobanco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alex.aplicacaobanco.util.Util;


public class LoginActivity extends AppCompatActivity{

    private EditText edtLogin;
    private EditText edtSenha;
    private Button btnLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                boolean isValido = validarCamposLogin(login, senha);
                if (isValido) {
                  //  Util.showMsgToast(LoginActivity.this, "Por favor preencha todos os ");
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public boolean validarCamposLogin(String login, String senha) {

        boolean resultado = true;
        if (login == null || "".equals(login)) {
            edtLogin.setError("Campo Obrigatório");
            return false;
        }

        if (senha == null || "".equals(senha)) {
            edtSenha.setError("Campo Obrigatório");
            return false;
        }

        if(resultado) {
            if (!login.equals("admin") || !senha.equals("admin")) {
                Util.showMsgToast(LoginActivity.this,"Login e Usuário invalidos!" );
                return false;
            } else {

            }
        }

        return resultado;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
