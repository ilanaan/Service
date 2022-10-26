package ilana.andriesh.service;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        start.setOnClickListener(this::onClick);
        stop.setOnClickListener(this::onClick);
    }
    public void onClick(View v)
    {
        if(v==start)
        {
            Intent music=new Intent(getApplicationContext(),MyService.class);
            startService(music);
        }
        if(v==stop)
        {
            Intent music=new Intent(getApplicationContext(),MyService.class);
            stopService(music);
        }
    }public void makeAlert() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Warning");
        adb.setMessage("Are you sure you want to exit the program?");
        adb.setIcon(R.drawable.alertsign);
        adb.setCancelable(false);
        adb.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == dialog.BUTTON_NEGATIVE) {
                    CreateToast("Exiting canceled");
                    dialog.dismiss();
                }
            }
        });
        adb.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == dialog.BUTTON_POSITIVE) {
                    CreateToast("Exiting the program");
                    dialog.dismiss();
                    finish();
                }
            }
        });
        adb.create().show();
    }
    public void CreateToast(String input) {
        Toast.makeText(getApplicationContext(), input, Toast.LENGTH_SHORT).show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit)
            makeAlert();
        return super.onOptionsItemSelected(item);
    }
}