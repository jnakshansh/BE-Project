package com.example.digitalchecker;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import weborb.client.ant.wdm.Table;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TableLayout tableLayout = Objects.requireNonNull(getView()).findViewById(R.id.reading_table);

        Backendless.Data.of( "Gas" ).find( new AsyncCallback<List<Map>>(){
            @Override
            public void handleResponse( List<Map> GasReadings )
            {
                // every loaded object from the "Contact" table is now an individual java.util.Map
                Log.e("GasReadings", String.valueOf(GasReadings));
                LinearLayout linearLayout = Objects.requireNonNull(getView()).findViewById(R.id.linear);
                TableLayout tableLayout = new TableLayout(getContext());
                TableLayout.LayoutParams params = new TableLayout.LayoutParams();
                params.gravity=Gravity.CENTER;
                tableLayout.setLayoutParams(params);
//                tableLayout.setBackgroundColor(Color.BLACK);
                TableRow r1 = new TableRow(getContext());
                TextView t1 = new TextView(getContext());

//                t1.setTypeface(t1.getTypeface(), Typeface.BOLD);
                t1.setText("Sl No.");
                t1.setTextSize(18);
                t1.setPadding(10,10,10,10);
//                t1.setBackgroundColor(Color.GRAY);
                t1.setGravity(Gravity.CENTER);
//                t1.setBackgroundColor(Color.WHITE);
                r1.addView(t1);
                TextView t2 = new TextView(getContext());
                t2.setText("Created");
                t2.setTextSize(18);
                t2.setPadding(10,10,10,10);
                t2.setGravity(Gravity.CENTER);
//                t2.setBackgroundColor(Color.WHITE);
                r1.addView(t2);
                TextView t3 = new TextView(getContext());
                t3.setText("Gas");
                t3.setTextSize(18);
                t3.setPadding(10,10,10,10);
                t3.setGravity(Gravity.CENTER);
//                t3.setBackgroundColor(Color.WHITE);
                r1.addView(t3);
                TextView t4 = new TextView(getContext());
                t4.setText("Temperature");
                t4.setTextSize(18);
                t4.setPadding(10,10,10,10);
                t4.setGravity(Gravity.CENTER);
//                t4.setBackgroundColor(Color.WHITE);
                r1.addView(t4);
                TextView t5 = new TextView(getContext());
//                t5.setTypeface(t1.getTypeface(), Typeface.BOLD);
                t5.setText("Humidity");
                t5.setTextSize(18);
                t5.setPadding(10,10,10,10);
                t5.setGravity(Gravity.CENTER);
//                t5.setBackgroundColor(Color.WHITE);
                r1.addView(t5);
                tableLayout.addView(r1);
                for(int i=0;i<GasReadings.size();i++){
                    TableRow r2 = new TableRow(getContext());
                    TextView th1 = new TextView(getContext());
                    th1.setText(""+(i+1));
                    th1.setGravity(Gravity.CENTER);
//                    th1.setBackgroundColor(Color.GRAY);
                    th1.setTextSize(16);
                    r2.addView(th1);
                    TextView th2 = new TextView(getContext());
                    String[] str = String.valueOf(GasReadings.get(i).get("created")).split(" ");
                    String btnstr = str[0]+" "+str[1]+" "+str[2]+"\n"+str[3];
                    th2.setText(""+btnstr);
                    th2.setGravity(Gravity.CENTER);
                    th2.setTextSize(16);
                    r2.addView(th2);
                    TextView th3 = new TextView(getContext());
                    th3.setText(""+GasReadings.get(i).get("Gas_Reading"));
                    th3.setGravity(Gravity.CENTER);
                    th3.setTextSize(16);
                    r2.addView(th3);
                    TextView th4 = new TextView(getContext());
                    th4.setText(""+GasReadings.get(i).get("Temp"));
                    th4.setGravity(Gravity.CENTER);
                    th4.setTextSize(16);
                    r2.addView(th4);
                    TextView th5 = new TextView(getContext());
                    th5.setText(""+GasReadings.get(i).get("Humidity"));
                    th5.setGravity(Gravity.CENTER);
                    th5.setTextSize(16);
                    r2.addView(th5);
                    tableLayout.addView(r2);
                }
                linearLayout.addView(tableLayout);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

}
