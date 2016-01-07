package br.com.alex.aplicacaobanco;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.alex.aplicacaobanco.util.Util;

public class MainActivity extends ActionBarActivity {

    EditText etNome, etMatricula;
    Button btCadastro, btConsulta, btVoltar, btProxReg, btRegAnt, btMenuPrincipal, btGravar, btExcluir;
    TextView tvNome, tvMatricula;
    String tvId;
    SQLiteDatabase bancoDados = null; //banco de dados
    int campoId, campoNome, campoMatricula;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///setContentView(R.layout.main);
        abreouCriaBanco();
        chamaMenuPrincipal();
    }

    public void chamaMenuPrincipal() {
        setContentView(R.layout.main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        //getSupportActionBar().setLogo(R.drawable.brasao);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
       // getSupportActionBar().setIcon(R.drawable.brasao);

        getSupportActionBar().setTitle("SIGDAE Mobile");

        Util.showMsgToast(this, "Pagina Inicial do Sistema");

      //  mensagemExibir("Titulo", "Alerta de Mensagem");

        fechaBanco();
        inicializaoObjetos();
        listeners();
    }

    public void chamaCadastro() {
        setContentView(R.layout.cadastro);
        inicializaoObjetos();
        listeners();
        etNome.requestFocus();
    }

    public void chamaConsulta() {

        if (buscaDados()) {// e o mesmo que if (buscarDados() == tru) {

            setContentView(R.layout.consulta);
            inicializaoObjetos();
            listeners();
            mostrarDados();
        } else {
            mensagemExibir("Aviso", "Não existe Funcionário cadastrado!");
            chamaMenuPrincipal();
            return;
        }
    }

    private boolean buscaDados() {
        try {
            cursor = bancoDados.query("funcionarios", //String table,
                    new String[]{"id", "nome", "matricula"}, //String[] columns,
                    null, //String selection,
                    null, //String[] selectionArgs,
                    null, // String groupBy,
                    null, //String having,
                    null); //String orderBy));

            campoId = cursor.getColumnIndex("id");
            campoNome = cursor.getColumnIndex("nome");
            campoMatricula = cursor.getColumnIndex("matricula");

            int numeroRegistro = cursor.getCount();

            if (numeroRegistro != 0) {
                // no java puro resultsert.first();
                cursor.moveToFirst(); // posiciona o primeito registro
                return true;
            } else {
                return false;
            }
        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao buscar dados do Banco: " + erro.getMessage());
            return false;
        }
    }

    public void abreouCriaBanco() {
        try {
            String nomeBanco = "bancoEstoque";
            // Cria ou abre o banco de dados
            bancoDados = openOrCreateDatabase(nomeBanco, MODE_WORLD_READABLE, null);
            String sql = "CREATE TABLE IF NOT EXISTS funcionarios " +
                    "(id INTEGER PRIMARY KEY, nome TEXT, matricula TEXT);";
            bancoDados.execSQL(sql);
        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao abrir ou criar o Banco: " + erro.getMessage());
        }
    }

    public void fechaBanco() {
        try {
            bancoDados.close(); // fecha banco de dados

        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao fechar o Banco: " + erro.getMessage());
        }
    }

    public void mostrarDados() {
        //tvNome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        //tvMatricula.setText(cursor.getString(cursor.getColumnIndex("matricula")));
        // ou
        tvId = cursor.getString(campoId);
        tvNome.setText(cursor.getString(campoNome));
        tvMatricula.setText(cursor.getString(campoMatricula));
    }

    public void mostrarRegistroAnterior() {
        try {
            cursor.moveToPrevious();
            mostrarDados();
        } catch (Exception erro) {
            mensagemExibir("Navegação", "Você já estar no primeiro Funcionário!");
        }
    }

    public void mostrarProximoRegistro() {
        try {
            cursor.moveToNext();
            mostrarDados();
        } catch (Exception erro) {
            mensagemExibir("Navegação", "Você já estar no último Funcionário!");
        }
    }

    public void salvarRegistro() {
        try {
            String sql = "INSERT INTO funcionarios (nome, matricula) VALUES ('"
                    + etNome.getText().toString() + "','"
                    + etMatricula.getText().toString() + "')";

            bancoDados.execSQL(sql);

        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao gravar dados no Banco: " + erro.getMessage());
        }
    }

    public void deleteRegistro() {
        try {
            abreouCriaBanco();
            String sql = "DELETE FROM funcionarios WHERE id = '" + tvId + "'";
            bancoDados.execSQL(sql);
            bancoDados.close();
            mensagemExibir("Aviso", "Funcionário [ " + tvNome.getText().toString() + " ] excluido com sucesso!");

        } catch (Exception erro) {
            mensagemExibir("Erro Banco", "Erro ao deletar dados no Banco: " + erro.getMessage());
        }
    }

    public void inicializaoObjetos() {
        try {
            //  Objetos do menu principal
            btCadastro = (Button) findViewById(R.id.btCadastroFuncionario);
            btConsulta = (Button) findViewById(R.id.btConsultaFuncionario);
            // Objetos da consulta
        } catch (Exception erro) {
        }
        try {
            btVoltar = (Button) findViewById(R.id.btVoltar);
            btProxReg = (Button) findViewById(R.id.btProximoRegistro);
            btRegAnt = (Button) findViewById(R.id.btRegistroAnterior);
            btExcluir = (Button) findViewById(R.id.btExcluir);
            //Objetos do meu cadastro
            btMenuPrincipal = (Button) findViewById(R.id.btMenuPrincipal);
            btGravar = (Button) findViewById(R.id.btGravar);
        } catch (Exception erro) {
        }
        try {
            //tvId = (TextView) findViewById(R.id.tvId);

            tvNome = (TextView) findViewById(R.id.tvNome);
            tvMatricula = (TextView) findViewById(R.id.tvMatricula);
            etNome = (EditText) findViewById(R.id.nome);
            etMatricula = (EditText) findViewById(R.id.matricula);
        } catch (Exception erro) {
            mensagemExibir("Erro", "Erro na inicializacao dos objrtos");
        }
    }

    public void listeners() {
        try {
            btCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abreouCriaBanco();
                    chamaCadastro(); //chama a tela de cadastro ao clicar
                }
            });

            btConsulta.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    abreouCriaBanco();
                    chamaConsulta(); //chama a tela de consulta ao clicar
                }
            });

        } catch (Exception erro) {

        }

        try {

            btVoltar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    cursor.close();
                    chamaMenuPrincipal();
                }
            });

            btRegAnt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mostrarRegistroAnterior();
                }
            });

            btProxReg.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mostrarProximoRegistro();
                }
            });

        } catch (Exception erro) {
        }

        try {
            btMenuPrincipal.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    chamaMenuPrincipal();
                }
            });

            btGravar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        if (etNome.getText().toString().equals("")) {
                            mensagemExibir("Aviso", "Nome do Funcionário e obrigatório!");
                            return;
                        } else {
                            salvarRegistro();
                            mensagemExibir("Aviso", "Funcionário [ " + etNome.getText().toString() + " ] salvo com sucesso!");
                            etNome.setText(null);
                            etMatricula.setText(null);
                            etNome.requestFocus();
                     //chamaMenuPrincipal();
                        }
                    } catch (Exception erro) {
                        mensagemExibir("Erro Banco", "Erro ao gravar dados no Banco: " + erro.getMessage());
                    }
                }
            });
        } catch (Exception erro) {
        }

        try {
            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //deleteRegistro(tvId.getText().toString());
                    deleteRegistro();
                    chamaMenuPrincipal();
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
