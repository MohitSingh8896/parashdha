package project.namramuni.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Constant.FileDownloader;
import project.namramuni.Pojo.ReadPojo;
import project.namramuni.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.ProcessedData;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PDFViewerActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener ,OnPageScrollListener {
    @BindView(R.id.toolbar1)
    Toolbar toolbar1;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.share)
    ImageView share;
    PDFView pdfView;
    String pdfFileName;
    Integer pageNumber = 0;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        ButterKnife.bind(this);
        pdfView = findViewById(R.id.pdfView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Intent.ACTION_SEND);
                Uri uri = Uri.parse(copyFile.toString());
                send.setType("text/plain");
                send.putExtra(Intent.EXTRA_STREAM,uri);
                send.putExtra(Intent.EXTRA_SUBJECT, "Namramuni");
//                send.setData(uri);
                startActivity(Intent.createChooser(send, "Share Now..."));
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle("Please wait");
        dialog.show();
        PdfFileName(getIntent().getStringExtra("pdf"));
    }

    private void PdfFileName(String readPojo) {
        try {
            pageNumber = 0;
            pdfFileName = readPojo;
//            txtVideoName.setText(pdfFileName);
            new DownloadFile().execute(readPojo);
        } catch (Exception e) {
            e.getMessage();
            dialog.cancel();
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPageScrolled(int page, float positionOffset) {
       /* fullscreen_button.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fullscreen_button.setVisibility(View.GONE);
            }
        }, 3000);*/
    }

    private class DownloadFile extends AsyncTask<String, Void, File> {
        @Override
        protected File doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = "a.pdf";  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();
            File pdfFile = new File(folder, fileName);
            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            File pdfFile1 = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + fileName);  // -> filename = maven.pdf
            return pdfFile1;
        }

        @Override
        protected void onPostExecute(File s) {
            super.onPostExecute(s);
            copyFile = s;
            pdfView.fromFile(s)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onPageChange(PDFViewerActivity.this)
                    .spacing(5) // in dp
                    .enableAnnotationRendering(true)
                    .onLoad(PDFViewerActivity.this)
                    .onPageScroll(PDFViewerActivity.this)
                    .scrollHandle(new DefaultScrollHandle(PDFViewerActivity.this))
                    .load();
            pdfView.setVerticalScrollBarEnabled(true);
            dialog.dismiss();
        }
    }

    File copyFile=null;


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
