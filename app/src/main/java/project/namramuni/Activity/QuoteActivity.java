package project.namramuni.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class QuoteActivity extends AppCompatActivity {
    TextView titleHeading, signature,titleMainHeading,txttitle,txtViewCount;
    ImageView share,img,back,imgq2,imgq1;
    LinearLayout lnrView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        back = findViewById(R.id.back);
        share = findViewById(R.id.share);
        img = findViewById(R.id.img);
        imgq2 = findViewById(R.id.imgq2);
        imgq1 = findViewById(R.id.imgq1);
        txttitle = findViewById(R.id.txttitle);
        lnrView = findViewById(R.id.lnrView);
        titleMainHeading = findViewById(R.id.titleMainHeading);
        txtViewCount = findViewById(R.id.txtViewCount);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        titleHeading = findViewById(R.id.titleHeading);
        signature = findViewById(R.id.signature);
        SetFont.setFont(titleHeading, this, SetFont.bold);
        SetFont.setFont(signature, this, SetFont.signature);
        SetFont.setFont(txttitle, this, SetFont.bold);
        if (getIntent().getStringExtra("view")!=null){
            lnrView.setVisibility(View.GONE);
            titleMainHeading.setVisibility(View.GONE);
            imgq2.setVisibility(View.GONE);
            imgq1.setVisibility(View.GONE);
            txtViewCount.setText(getIntent().getStringExtra("view"));
        } else {
            imgq2.setVisibility(View.GONE);
            imgq1.setVisibility(View.GONE);
            lnrView.setVisibility(View.GONE);
            titleMainHeading.setVisibility(View.GONE);
        }
        titleMainHeading.setText(Html.fromHtml(getIntent().getStringExtra("title")));
        titleHeading.setText(Html.fromHtml(getIntent().getStringExtra("text")));
        Picasso.with(this).load(getIntent().getStringExtra("image")).into(img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,getIntent().getStringExtra("text"));
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Namramuni");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}