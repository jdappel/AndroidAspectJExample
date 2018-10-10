package com.jdappel.example;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.archinamon.example.MyApplication;
import com.archinamon.example.R;
import com.archinamon.kotlin.InfoActivity;

import java.util.Locale;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private TextView mHelloField;
    private boolean once = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloField = (TextView) findViewById(R.id.hello_world);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final MyApplication app = (MyApplication) getApplication();
        final String lang = app.getPreferences()
                               .getString(MyApplication.LANG_KEY,
                                          Locale.getDefault().getDisplayLanguage());

        if (once && mHelloField != null) {
            mHelloField.append("\n");
            mHelloField.append(getString(R.string.running));
            mHelloField.append(" ");
            mHelloField.append(lang);
            mHelloField.append("!");

            once = false;
        }
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

    public void onFirstButtonClicked(View view) {
        toastFromJava();
    }

    public void onSecondButtonClicked(View view) {
        toastFromAspectJ();
    }

    public void onThirdButtonClicked(View view) {
        startActivity(new Intent(this, InfoActivity.class));
        toastFromKotlin();
    }

    private void toastFromJava() {
        String msg = getString(R.string.something_happend_java);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void toastFromAspectJ() {}
    private void toastFromKotlin() {}
}