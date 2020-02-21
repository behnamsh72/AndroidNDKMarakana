package com.behnam.androidndkmarakana;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FibonacciActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "FibonacciActivity";
    static {
        try {
            System.loadLibrary("libfriendlyarm-things");
        }catch (UnsatisfiedLinkError e){
            Log.d(TAG,"lib friendly arm not found");
        }
    }

    private EditText input;
    private RadioGroup type;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        input = findViewById(R.id.edtNumber);
        type = findViewById(R.id.type);
        output = findViewById(R.id.output);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onClick(View v) {

        String s = this.input.getText().toString();
        if (TextUtils.isEmpty(s)) {
            return;
        }
        final long n = Long.parseLong(s);
        final ProgressDialog dialog = ProgressDialog.show(this, "", "Calculating", true);
        new AsyncTask<Void, Void, String>() {

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... voids) {

                long result = 0;
                long t = System.currentTimeMillis();

                switch (type.getCheckedRadioButtonId()) {
                    case R.id.type_fib_jr:
                        result = FibLib.fibJR(n);
                        break;

                    case R.id.type_fib_ji:
                        result = FibLib.fibJI(n);
                        break;

                    case R.id.type_fib_nr:
                        result = FibLib.fibNR(n);
                        break;

                    case R.id.type_fib_ni:
                        result = FibLib.fibNI(n);
                        break;
                }
                t = System.currentTimeMillis() - t;
                return String.format("Fib(%d)=%d in %d ms", n, result, t);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                output.setText(s);
            }
        }.execute();


    }
}
