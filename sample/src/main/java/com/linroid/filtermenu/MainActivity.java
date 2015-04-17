package com.linroid.filtermenu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.linroid.filtermenu.library.FilterMenu;
import com.linroid.filtermenu.library.FilterMenuLayout;

import hugo.weaving.DebugLog;


public class MainActivity extends ActionBarActivity {
    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FilterMenuLayout layout1 = (FilterMenuLayout) findViewById(R.id.filter_menu1);
        attachMenu1(layout1);

        FilterMenuLayout layout2 = (FilterMenuLayout) findViewById(R.id.filter_menu2);
        attachMenu2(layout2);

        FilterMenuLayout layout3 = (FilterMenuLayout) findViewById(R.id.filter_menu3);
        attachMenu3(layout3);

        FilterMenuLayout layout4 = (FilterMenuLayout) findViewById(R.id.filter_menu4);
        attachMenu4(layout4);
    }
    private FilterMenu attachMenu1(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.ic_action_add)
                .addItem(R.drawable.ic_action_clock)
                .addItem(R.drawable.ic_action_info)
                .addItem(R.drawable.ic_action_io)
                .addItem(R.drawable.ic_action_location_2)
                .attach(layout)
                .withListener(listener)
                .build();
    }
    private FilterMenu attachMenu2(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.ic_action_add)
                .addItem(R.drawable.ic_action_clock)
                .addItem(R.drawable.ic_action_info)
                .addItem(R.drawable.ic_action_location_2)
                .attach(layout)
                .withListener(listener)
                .build();
    }
    private FilterMenu attachMenu3(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.ic_action_add)
                .addItem(R.drawable.ic_action_clock)
                .addItem(R.drawable.ic_action_location_2)
                .attach(layout)
                .withListener(listener)
                .build();
    }
    private FilterMenu attachMenu4(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .inflate(R.menu.menu_filter)
                .attach(layout)
                .withListener(listener)
                .build();
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
        if (id == R.id.action_github) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/linroid/FilterMenu"));
            startActivity(Intent.createChooser(intent, null));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    FilterMenu.OnMenuChangeListener listener = new FilterMenu.OnMenuChangeListener() {
        @DebugLog
        @Override
        public void onMenuItemClick(View view, int position) {
            Toast.makeText(MainActivity.this, "Touched position " + position, Toast.LENGTH_SHORT).show();
        }

        @DebugLog
        @Override
        public void onMenuCollapse() {

        }

        @DebugLog
        @Override
        public void onMenuExpand() {

        }
    };
}
