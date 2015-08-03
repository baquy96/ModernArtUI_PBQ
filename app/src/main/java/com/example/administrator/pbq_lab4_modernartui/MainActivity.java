package com.example.administrator.pbq_lab4_modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;



public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout palette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.more_info)
        {
            //Show dialog
            AlertDialog alertDialog = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
            alertDialog.setMessage(getString(R.string.dialog_text));
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dialog_not_now), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.dialog_visit), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Launch browser
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                    startActivity(browserIntent);
                }
            });

            alertDialog.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {
        View mview1, mview2, mview3, mview4, mview5;
        int colorview1, colorview2, colorview3, colorview4, colorview5;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,	false);

            colorview1 = getResources().getColor(R.color.Blue_1);
            colorview2 = getResources().getColor(R.color.red);
            colorview3 = getResources().getColor(R.color.pink);
            colorview4 = getResources().getColor(R.color.white);
            colorview5 = getResources().getColor(R.color.Blue_2);

            //Get references to UI elements
            mview1 = (View)rootView.findViewById(R.id.view1);
            mview2 = (View)rootView.findViewById(R.id.view2);
            mview3 = (View)rootView.findViewById(R.id.view3);
            mview4 = (View)rootView.findViewById(R.id.view4);
            mview5 = (View)rootView.findViewById(R.id.view5);

            final SeekBar seekBar = (SeekBar)rootView.findViewById(R.id.seekBar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    updateColors(1 - ((float)progress / 100));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            return rootView;
        }

        private void updateColors(float percentage)
        {
            mview1.setBackgroundColor(getUpdatedColor(colorview1, percentage));
            mview2.setBackgroundColor(getUpdatedColor(colorview2, percentage));
            mview3.setBackgroundColor(getUpdatedColor(colorview3, percentage));
            mview4.setBackgroundColor(getUpdatedColor(colorview4, percentage));
            mview5.setBackgroundColor(getUpdatedColor(colorview5, percentage));
        }

        private int getUpdatedColor(int color, float percentage)
        {
            //Modify red and blue components depending on the current progress
            int red = (int)((float)Color.red(color) * percentage);
            int green = Color.green(color);
            int blue = (int)((float)Color.blue(color) * percentage);
            int alpha = Color.alpha(color);

            return Color.argb(alpha, red, green, blue);
        }
    }
}



