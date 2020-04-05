package com.example.project_spread_sheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    AsyncHttpClient client;
    Workbook workbook;
    List<String>name,phone_no;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://github.com/satyambup/sheet/blob/master/project_mad.xlsx";

        recyclerView=findViewById(R.id.listOfdata);
        progressBar=findViewById(R.id.progressBar);


        name = new ArrayList<>();
        phone_no = new ArrayList<>();

        client = new AsyncHttpClient();
        progressBar.setVisibility(View.VISIBLE);
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, File file) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this ,"Download Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, File file) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"downloaded",Toast.LENGTH_SHORT).show();
                WorkbookSettings ws = new WorkbookSettings();
                ws.setGCDisabled(true);
                if (file != null){
                    try {
                        workbook = Workbook.getWorkbook(file);
                        Sheet sheet = workbook.getSheet(0);
                        for (int I = 0 ; I < sheet.getRows() ; I++)
                        {
                            Cell[] row = sheet.getRow(I);
                            name.add(row[0].getContents());
                            phone_no.add(row[1].getContents());

                        }

                        Log.d("TAG","onsuccess: "+ name);

                        showdata();


                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (BiffException e){
                        e.printStackTrace();
                    }


                }

            }
        });



    }

    private void showdata() {
        adapter=new Adapter(this,name,phone_no);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
