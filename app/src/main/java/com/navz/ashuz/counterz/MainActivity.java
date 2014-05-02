package com.navz.ashuz.counterz;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.startapp.android.publish.StartAppAd;


public class MainActivity extends Activity  {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Integer hundreds;
        Integer counter;

        boolean iflongclick = false;

        TextView hundredsDigit;
        Button counterDigit;

        public Integer getCounterDigit() {
            return Integer.valueOf((String) counterDigit.getText());
        }

        public void setCounterDigit(Integer digit) {
            counterDigit.setText(digit.toString());
        }

        public Integer getHundredsDigit() {
            return Integer.valueOf((String) hundredsDigit.getText());
        }

        public void setHundredsDigit(Integer Digit) {
            hundredsDigit.setText(Digit.toString());
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            /*
            mSensorManager = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            */
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            StartAppAd.init(getActivity(), "101242287", "205300058");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            hundredsDigit = (TextView) rootView.findViewById(R.id.hundredsTV);
            counterDigit = (Button) rootView.findViewById(R.id.counterTV);
            counterDigit.setText("0");
            hundredsDigit.setText("0");

            counterDigit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    iflongclick = true;
                    setCounterDigit(0);
                    setHundredsDigit(0);
                    hundredsDigit.setVisibility(View.GONE);
                    return false;
                }
            });

            counterDigit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (iflongclick) {
                        iflongclick = false;
                        return;
                    }

                    Integer currentValue = getCounterDigit();
                    if (currentValue == 99) {
                        setCounterDigit(0);
                        Integer currentHundreds = getHundredsDigit();
                        if (currentHundreds == 0) {
                            hundredsDigit.setVisibility(View.VISIBLE);
                        }
                        setHundredsDigit(++currentHundreds);
                    } else {
                        setCounterDigit(++currentValue);
                    }
                }
            });

            counterDigit.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View view, DragEvent dragEvent) {
                    Integer dummy = getCounterDigit();
                    dummy--;
                    setCounterDigit(dummy);
                    return false;
                }
            });


            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

       /* //shaky things
        //shaky things
        private SensorManager mSensorManager = null;
        private Sensor mAccelerometer;

        public void onResume() {
            super.onResume();
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }



        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
        */


        }
    }


