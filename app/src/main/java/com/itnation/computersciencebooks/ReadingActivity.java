package com.itnation.computersciencebooks;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReadingActivity extends AppCompatActivity {


    TextView toolText;
    PDFView pdfView;
    ProgressBar progressBar;
    ImageButton backbt, downloadBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Window window = ReadingActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.primary));



        toolText= findViewById(R.id.toolText);
        pdfView= findViewById(R.id.pdfView);
        progressBar= findViewById(R.id.progressbar);
        backbt= findViewById(R.id.backbt);
        downloadBtn= findViewById(R.id.downloadBtn);



        String bookName = getIntent().getStringExtra("bookName");
        String bookLink = getIntent().getStringExtra("bookLink");


        toolText.setText(bookName);


        showPDF(bookLink);

        backbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ReadingActivity.this);
                builder.setMessage("Choose Download Type");
                builder.setPositiveButton("Using Default Browser", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bookLink)));
                    }
                });
                builder.setNegativeButton("Using Download Manager", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloadPdf(bookLink);
                    }
                });
                builder.show();
            }
        });


    }


    private void downloadPdf(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("PDF Download");
        request.setDescription("Downloading PDF file");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "pdf_file.pdf");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }



    private void showPDF(String pdfLink) {



        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(pdfLink)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                Toast.makeText(ReadingActivity.this, "Loading Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                InputStream inputStream = response.body().byteStream();
                runOnUiThread(() -> {

                    pdfView.fromStream(inputStream)
                            .onLoad(nbPages -> {

                                progressBar.setVisibility(View.GONE);
                            })
                            .swipeHorizontal(true)
                            .defaultPage(0)
                            .enableSwipe(true)
                            .load();

                });

            }
        });

    }

    @Override
    public void onBackPressed() {

        finish();
        super.onBackPressed();
    }
}