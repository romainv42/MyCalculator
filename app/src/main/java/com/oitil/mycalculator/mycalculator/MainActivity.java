package com.oitil.mycalculator.mycalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
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
    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier("12345");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    // TODO: Move this method and use your own event name to track your key metrics
    public void onKeyMetric(View view) {
        // TODO: Use your own string attributes to track common values over time
        // TODO: Use your own number attributes to track median value over time
        Answers.getInstance().logCustom(new CustomEvent("Calculator opened")
                .putCustomAttribute("Category", "Math")
                .putCustomAttribute("Length", 42));
        crashMe("KeyMetrics Failed");
    }

    public void forceCrash(View view) {
        this.logUser();

        crashMe("Force Crash Succeed");
    }

    private void crashMe(String msg) {
        throw new RuntimeException(msg);
    }

    private int divided(int a, int b) {
        return (a / b);
    }

    public void makeDivision(View view) {
        int result = divided(42, 0);

    }
}
