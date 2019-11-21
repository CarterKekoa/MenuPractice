package com.example.menufuns1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        // task: create ListView
        final ListView listView = new ListView(this);
        // set the content view to be the listview
        setContentView(listView);
        // no layout needed!
        // create a list of items (halloween strings)
        List<String> items = new ArrayList<>();
        items.add("Bats");
        items.add("Student debt");
        items.add("Midterms");

        // create an array adapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_activated_1,
                items);
        listView.setAdapter(arrayAdapter);
        // set the listview to support multiple selections
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // set the multi choice listener
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                int numChecked = listView.getCheckedItemCount();
                actionMode.setTitle(numChecked + " selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.cam_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                // don't need this for PA7
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                // executes when the user clicks a CAM menu item
                // task: switch on menu item id... try to show (log or toast) the
                // indexes of the items that are checked
                switch (menuItem.getItemId()) {
                    case R.id.deleteMenuItem:
                        // hint for PA7... use ids...
                        String temp = listView.getCheckedItemPositions().toString();
                        Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();

                        actionMode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                // don't need this for PA7
            }
        });


        // make sure your items show up in the list view

        //startEditItemActivity();
    }

    private void startEditItemActivity() {
        Intent intent = new Intent(this, EditItemActivity.class);
        startActivity(intent);
    }

    // we need to inflate our main_menu.xml


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get a reference to the MenuInflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // override a callback that executes whenever an options menu action is clicked

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.addMenuItem:
                startEditItemActivity();
                return true; // we have consumed/handled this click event
            // task: finish remaining two cases
            case R.id.preferencesMenuItem:
                Toast.makeText(this, "TODO: show preferences", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aboutMenuItem:
                Toast.makeText(this, "TODO: show about app", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
