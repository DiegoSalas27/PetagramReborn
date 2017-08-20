package com.example.android.petagramreborn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.internet.InternetAddress.*;

public class ActivityContacto extends AppCompatActivity {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    String subject, reciep, comentario, correo, contraseña, hotMail, gMail;
    boolean mandarMensaje = false;

    private EditText etFullName, etEmail, etComentario, etAsunto,etCorreo, etContraseñaSolicitante;
    private TextInputLayout inputLayoutFullName, inputLayoutEmail, inputLayoutComentario, inputLayoutAsunto
            , inputLayoutEmailSolicitante, inputLayoutContraseñaSolicitante;
    private Button btnEnviar;
    private Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.mContacto:
                Intent intent = new Intent(this, ActivityContacto.class);
                startActivity(intent); break;
            case R.id.mAcercade:
                Intent intent2 = new Intent(this, ActivityAbout.class);
                startActivity(intent2); break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        toolbar = (Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        initializeWidgets();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();

                subject = etFullName.getText().toString();
                reciep = etEmail.getText().toString();
                comentario = etComentario.getText().toString();
                correo = etCorreo.getText().toString();
                contraseña = etContraseñaSolicitante.getText().toString();
                hotMail = "hotmail";
                gMail = "gmail";




                if(mandarMensaje) {

                    Properties props = new Properties();
                    if(correo.contains(gMail)) {

                        props.put("mail.smtp.host", "smtp.googlemail.com");
                        props.put("mail.smtp.socketFactory.port", "465");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");

                    } else if(correo.contains(hotMail)){

                        props.put("mail.smtp.host", "smtp.live.com");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");

                    }

                    try{
                    session = Session.getDefaultInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(correo, contraseña);
                        }
                    });
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                    pdialog = ProgressDialog.show(context, "", "Enviando email...", true);

                    RetrieveFeedTask task = new RetrieveFeedTask();
                    task.execute();
                }

            }

            class RetrieveFeedTask extends AsyncTask<String, Void, String>{

                @Override
                protected String doInBackground(String... params) {
                    try{
                        if(session != null) {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(correo));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciep));
                            message.setSubject(subject);
                            message.setContent(comentario, "text/html; charset=utf-8");

                            if(correo.contains(gMail)) {
                                Transport.send(message);
                            }
                            else if(correo.contains(hotMail)){
                                Transport  transport = session.getTransport("smtp");
                                    transport.connect("smtp.live.com", 587, correo, contraseña);
                                    transport.sendMessage(message, message.getAllRecipients());
                                    transport.close();
                            }
                        }
                    }catch(MessagingException e){
                        e.printStackTrace();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String result) {
                    if(mandarMensaje)
                    {
                        pdialog.dismiss();
                        etFullName.setText("");
                        etComentario.setText("");
                        etEmail.setText("");
                        etAsunto.setText("");

                        Toast.makeText(getApplicationContext(), "Mensaje enviado!", Toast.LENGTH_LONG).show();
                        mandarMensaje = false;
                    }
                }
            }
        });
    }
    private void initializeWidgets(){

        inputLayoutComentario = (TextInputLayout) findViewById(R.id.inputLayoutComentario);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmail);
        inputLayoutFullName = (TextInputLayout) findViewById(R.id.inputLayoutFullName);
        inputLayoutAsunto = (TextInputLayout) findViewById(R.id.inputLayoutAsunto);
        inputLayoutEmailSolicitante = (TextInputLayout) findViewById(R.id.inputLayoutEmailSolicitante);
        inputLayoutContraseñaSolicitante = (TextInputLayout) findViewById(R.id.inputLayoutContraseñaSolicitante);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etContraseñaSolicitante = (EditText) findViewById(R.id.etContraseñaSolicitante);

        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        etComentario = (EditText) findViewById(R.id.etComentario);
        etFullName = (EditText) findViewById(R.id.etFullname);
        etEmail = (EditText) findViewById(R.id.etEmail);

    }


    private void signUp(){

        boolean isValid = true;
        if(etFullName.getText().toString().isEmpty()){

            inputLayoutFullName.setError(getString(R.string.username_validation_message));
            isValid = false;

        } else{

            inputLayoutFullName.setErrorEnabled(false);
        }
        if(etEmail.getText().toString().isEmpty()){

            inputLayoutEmail.setError(getString(R.string.email_validation_message));
            isValid = false;

        } else{

            inputLayoutEmail.setErrorEnabled(false);
        }
        if(etComentario.getText().toString().isEmpty()){

            inputLayoutComentario.setError(getString(R.string.comment_validation_message));
            isValid = false;

        } else{

            inputLayoutComentario.setErrorEnabled(false);
        }
        if(etAsunto.getText().toString().isEmpty()){

            inputLayoutAsunto.setError(getString(R.string.subject_validation_message));
            isValid = false;

        } else{

            inputLayoutComentario.setErrorEnabled(false);
        }
        if(etCorreo.getText().toString().isEmpty()){

            inputLayoutEmailSolicitante.setError(getString(R.string.email_validation_message));
            isValid = false;

        } else{

            inputLayoutComentario.setErrorEnabled(false);
        }
        if(etContraseñaSolicitante.getText().toString().isEmpty()){

            inputLayoutContraseñaSolicitante.setError(getString(R.string.password_validation_message));
            isValid = false;

        } else{

            inputLayoutComentario.setErrorEnabled(false);
        }
        if(isValid){

            mandarMensaje = true;
        }
    }

}
