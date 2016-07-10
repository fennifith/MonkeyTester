package james.monkeytester;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String URL_MONKEY_DOCS = "http://developer.android.com/reference/android/app/ActivityManager.html#isUserAMonkey%28%29";
    private static final String URL_GOAT_DOCS = "http://developer.android.com/reference/android/os/UserManager.html#isUserAGoat%28%29";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean monkey = ActivityManager.isUserAMonkey();
        boolean goat = ((UserManager) getSystemService(Context.USER_SERVICE)).isUserAGoat();

        TextView text = (TextView) findViewById(R.id.data);

        if (monkey && goat) {
            text.setText(R.string.moat_or_gokey);
        } else if (monkey) {
            text.setText(R.string.monkey);
        } else if (goat) {
            text.setText(R.string.goat);
        } else {
            text.setText(R.string.neither);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_description));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this).setTitle(R.string.action_docs)
                        .setPositiveButton(R.string.action_monkey,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getCustomTabsIntent().launchUrl(MainActivity.this, Uri.parse(URL_MONKEY_DOCS));
                                    }
                                })
                        .setNeutralButton(R.string.action_goat,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getCustomTabsIntent().launchUrl(MainActivity.this, Uri.parse(URL_GOAT_DOCS));
                                    }
                                })
                        .create().show();
            }
        });

        findViewById(R.id.me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomTabsIntent().launchUrl(MainActivity.this, Uri.parse("http://theandroidmaster.github.io/"));
            }
        });

        findViewById(R.id.kartik).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomTabsIntent().launchUrl(MainActivity.this, Uri.parse("http://kartikarora.me/"));
            }
        });

        findViewById(R.id.alex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCustomTabsIntent().launchUrl(MainActivity.this, Uri.parse("https://plus.google.com/+adueppen"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_github).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_github));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_github) {
            getCustomTabsIntent().launchUrl(this, Uri.parse("https://github.com/TheAndroidMaster/MonkeyTester"));
        }
        return super.onOptionsItemSelected(item);
    }

    private CustomTabsIntent getCustomTabsIntent() {
        return new CustomTabsIntent.Builder().setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)).build();
    }
}
