package james.monkeytester;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean monkey, goat;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        monkey = ActivityManager.isUserAMonkey();
        goat = ((UserManager) getSystemService(Context.USER_SERVICE)).isUserAGoat();
        text = (TextView) findViewById(R.id.data);

        if (monkey && goat) {
            text.setText("You are both a monkey and a goat, a new type of monkey-goat hybrid... either a moat or gokey. Names are confusing.");
        } else if (monkey) {
            text.setText("You are a monkey.");
        } else if (goat) {
            text.setText("You are a goat.");
        } else {
            text.setText("You are neither a monkey or a goat.");
        }

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle("View Documentation").setPositiveButton("ActivityManager.isUserAMonkey()", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/ActivityManager.html#isUserAMonkey%28%29")));
                    }
                }).setNeutralButton("UserManager.isUserAGoat()", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/os/UserManager.html#isUserAGoat%28%29")));
                    }
                }).create().show();
            }
        });

        findViewById(R.id.me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://theandroidmaster.github.io/")));
            }
        });

        findViewById(R.id.alex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+adueppen")));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/TheAndroidMaster/MonkeyTester")));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
