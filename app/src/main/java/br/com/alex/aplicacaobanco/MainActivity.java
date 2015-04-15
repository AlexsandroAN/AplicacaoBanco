package br.com.alex.aplicacaobanco;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class MainActivity extends ActionBarActivity {

    EditText etNome, etMatricula;
    Button btCadastro, btConsulta, btVoltar, btProxReg, btRegAnt, btMenuPrincipal, btGravar;
    TextView tvNome, tvMatricula;
    SQLiteDatabase bancoDados = null; //banco de dados
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        abreouCriaBanco();
    }

    public void chamaCadastro() {
        setContentView(R.layout.cadastro);
        inicializaoObjetos();
        listeners();
        //etNome.requestFocus();
    }

    public void chamaConsulta() {

       // if (contadorRegistros == 0) {
        //    mensagemExibir("Aviso", "Não possui registro gravado");
        //    chamaMenuPrincipal();
       //     return;
       // }
      //  posicaoRegistro = 1;
      //  setContentView(R.layout.consulta);

        inicializaoObjetos();
        listeners();
      //  regAuxiliar = primeiroRegistro;

    //    mostrarDados();
    }


    public void abreouCriaBanco() {
        try {
            // Cria ou abre o banco de dados
            bancoDados = openOrCreateDatabase("bancoEstoque", MODE_WORLD_READABLE, null);
            String sql = "CREATE TABLE IF NOT EXISTS pessoas " +
                    "(id INTEGER PRIMARY KEY, nome TEXT, matricula TEXT);";
            bancoDados.execSQL(sql);

            mensagemExibir("Banco", "Banco de dados criado com sucesso!");


        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao abrir ou criar Banco:" + erro.getMessage());
        }
    }

    public void inicializaoObjetos() {
        try {
            //  Objetos do menu principal
            btCadastro = (Button) findViewById(R.id.btCadastroFuncionario);
            btConsulta = (Button) findViewById(R.id.btConsultaFuncionario);
            // Objetos da consulta
            btVoltar = (Button) findViewById(R.id.btVoltar);
            btProxReg = (Button) findViewById(R.id.btProximoRegistro);
            btRegAnt = (Button) findViewById(R.id.btRegistroAnterior);
            //Objetos do meu cadastro
            btMenuPrincipal = (Button) findViewById(R.id.btMenuPrincipal);
            btGravar = (Button) findViewById(R.id.btGravar);
            //
            tvNome = (TextView) findViewById(R.id.tvNome);
            tvMatricula = (TextView) findViewById(R.id.tvMatricula);

            etNome = (EditText) findViewById(R.id.nome);
            etMatricula = (EditText) findViewById(R.id.matricula);
        } catch (Exception ero) {
            mensagemExibir("Erro", "Erro na inicializacao dos objrtos");
        }
    }
    public void listeners() {
        try {
            btCadastro.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    chamaCadastro(); //chama a tela de cadastro ao clicar

                }
            });

            btConsulta.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                  //  chamaConsulta(); //chama a tela de consulta ao clicar
                }
            });

        } catch (Exception erro) {
        }
        try {

            btVoltar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   // chamaMenuPrincipal();
                }
            });

            btProxReg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //mostrarProximoRegistro();
                }
            });
            btRegAnt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   // mostrarRegistroAnterior();
                }
            });
        } catch (Exception erro) {
        }

        try {
            btMenuPrincipal.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //chamaMenuPrincipal();
                }
            });

            btGravar.setOnClickListener(new View.OnClickListener() {
                //videoaulaneri@gmail.com  www.informaticon.com.br  NERIZON DA GAITA
                @Override
                public void onClick(View v) {
                   // gravarRegistro();
                   // chamaMenuPrincipal();
                }
            });
        } catch (Exception erro) {
        }
    }

    public void mensagemExibir(String titulo, String texto) {
        AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
