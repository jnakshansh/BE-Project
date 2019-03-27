package com.example.digitalchecker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.nitri.gauge.Gauge;


public class FirstFragment extends Fragment {

    TextView temperature, humidity;
    Button on_off,refresh;
    Gauge gauge;
    int temp=0;

    public FirstFragment() {
        // Required empty public constructor
    }
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        humidity = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.humidity);
//        temperature = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.temperature);
//        gauge = getView().findViewById(R.id.gauge);


//        HashMap<String, String> testObject = new HashMap<>();
//        testObject.put( "foo", "bar" );
//        testObject.put("Gas_Reading","25");
//        Backendless.Data.of( "Gas" ).save(testObject,
//                new AsyncCallback() {
//                    @Override
//                    public void handleResponse(Object response) {
//                        TextView label = new TextView(getContext());
//                        label.setText("Object is saved in Backendless. Please check in the console.");
////                        setContentView(label);
//                    }
//
//                    @Override
//                    public void handleFault(BackendlessFault fault) {
//                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
//                    }
//                });

        Backendless.Persistence.of( "Gas" ).findLast( new AsyncCallback<Map>(){
            @Override
            public void handleResponse( Map Gas_Readings )
            {
                // last contact instance has been found
                Log.e("Gas_Reading", String.valueOf(Gas_Readings));
                Log.e("Gas_Reading_Value", String.valueOf(Gas_Readings.get("Gas_Reading")));
                humidity = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.humidity);
                int humid = (int) Gas_Readings.get("Humidity");
                humidity.setText(String.valueOf(humid));
                temperature = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.temperature);
                int temp = (int) Gas_Readings.get("Temp");
                temperature.setText(String.valueOf(temp));
                int gauge_read = (int) Gas_Readings.get("Gas_Reading");
                gauge = getView().findViewById(R.id.gauge);
                gauge.moveToValue(gauge_read);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });

//        textdisplay();
    }



    private void textdisplay() {

        Backendless.Persistence.of( "Gas" ).findLast( new AsyncCallback<Map>(){
            @Override
            public void handleResponse( Map Gas_Readings )
            {
                // last contact instance has been found
                Log.e("Gas_Reading", String.valueOf(Gas_Readings));
                Log.e("Gas_Reading_Value", String.valueOf(Gas_Readings.get("Gas_Reading")));
//                humidity = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.humidity);
                int humid = (int) Gas_Readings.get("Humidity");
                humidity.setText(String.valueOf(humid));
//                temperature = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.temperature);
                int temp = (int) Gas_Readings.get("Temp");
                temperature.setText(String.valueOf(temp));
                int gauge_read = (int) Gas_Readings.get("Gas_Reading");
//                Gauge gauge = getView().findViewById(R.id.gauge);
                gauge.moveToValue(gauge_read);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        humidity = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.humidity);
//        temperature = (TextView) Objects.requireNonNull(getView()).findViewById(R.id.temperature);
//        gauge = getView().findViewById(R.id.gauge);

        refresh = view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textdisplay();
            }
        });

        on_off = (Button) Objects.requireNonNull(getView()).findViewById(R.id.on_off);
        on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.Persistence.of("On_Off").findLast(new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Log.e("On_Off response",String.valueOf(response));
                        Boolean btnresponse = (Boolean) response.get("Button");
                        Log.e("btnresponse", String.valueOf(btnresponse));
                        if (btnresponse){
                            HashMap<String, Boolean> btnobject = new HashMap<>();
                            btnobject.put("Button",Boolean.FALSE);
                            Backendless.Data.of("On_Off").save(btnobject, new AsyncCallback<Map>() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void handleResponse(Map response) {
                                    Log.e("Response", String.valueOf(response));
                                    on_off.setText("ON");
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                }
                            });

                        }
                        else {
                            HashMap<String, Boolean> btnobject = new HashMap<>();
                            btnobject.put("Button",Boolean.TRUE);
                            Backendless.Data.of("On_Off").save(btnobject, new AsyncCallback<Map>() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void handleResponse(Map response) {
                                    Log.e("Response", String.valueOf(response));
                                    on_off.setText("OFF");
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {

                                }
                            });
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });

//                if(temp==1){
//                    HashMap<String, Boolean> btnobject = new HashMap<>();
//                    btnobject.put("Button",Boolean.TRUE);
//                    Backendless.Data.of("On_Off").save(btnobject, new AsyncCallback<Map>() {
//                        @Override
//                        public void handleResponse(Map response) {
//                            Log.e("Response", String.valueOf(response));
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//
//                        }
//                    });
//                    temp=0;
//                }
//                else{
//
//                    HashMap<String, Boolean> btnobject = new HashMap<>();
//                    btnobject.put("Button",Boolean.FALSE);
//                    Backendless.Data.of("On_Off").save(btnobject, new AsyncCallback<Map>() {
//                        @Override
//                        public void handleResponse(Map response) {
//                            Log.e("Response", String.valueOf(response));
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//
//                        }
//                    });
//                }
//                temp=1;
            }
        });

    }
}
