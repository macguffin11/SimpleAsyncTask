package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Integer, Integer, String> {
    // The TextView where we will show results
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> progressBar;

    // Constructor that provides a reference to the TextView from the MainActivity
    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = new WeakReference<>(tv);
        progressBar = new WeakReference<>(pb);
    }

    @Override
    protected String doInBackground(Integer... integers) {
        // Generate a random number between 0 and 10.
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running.
        int s = n * 200;

        // Sleep for the random amount of time.
        for (int count =0; count <= integers[0]; count++) {
            try {
                Thread.sleep(s);
                publishProgress(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Return a String result.
        return "Awake at last after sleeping for " + (s*integers[0]) + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}

