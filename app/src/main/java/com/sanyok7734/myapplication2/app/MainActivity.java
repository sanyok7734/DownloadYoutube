package com.sanyok7734.myapplication2.app;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.github.axet.vget.VGet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class MainActivity extends ActionBarActivity {

    private final String FILENAME_SD = "video";
    public final String URL = "https://www.youtube.com/watch?v=TsqbxwQLeEE";
    public File sdFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "SD card not available", Toast.LENGTH_SHORT).show();
        } else {
            // get sd path
            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath.getAbsolutePath() + "/" + "DownloadYoutube");
            // create directory
            sdPath.mkdirs();

            sdFile = new File(sdPath, FILENAME_SD);
            MyTask mt = new MyTask();
            mt.execute();
        }


    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d("SANNO", "Start");
                VGet vGet = new VGet(new URL(URL), sdFile);
                Log.d("SANNO", "Other");
                vGet.download();
                Log.d("SANNO", "Finish");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("SANNO", "Error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

}
