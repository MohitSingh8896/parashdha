package project.namramuni.Fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.R;

import static project.namramuni.Application.masterContext;

public class FragmentConnect extends Fragment {

    @BindView(R.id.rlvIntro)
    RelativeLayout rlvIntro;
    @BindView(R.id.rlvMeditate)
    RelativeLayout rlvMeditate;
    @BindView(R.id.rlvBenefits)
    RelativeLayout rlvBenefits;

    @BindView(R.id.relative1)
    RelativeLayout relative1;

    @BindView(R.id.relative2)
    RelativeLayout relative2;
    @BindView(R.id.relative3)
    RelativeLayout relative3;

    @BindView(R.id.imgIntro)
    ImageView imgIntro;
    @BindView(R.id.txtIntro)
    TextView txtIntro;
    @BindView(R.id.imgMeditation)
    ImageView imgMeditation;
    @BindView(R.id.txtMeditation)
    TextView txtMeditation;
    @BindView(R.id.imgBenefit)
    ImageView imgBenefit;
    @BindView(R.id.txtBenefit)
    TextView txtBenefit;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_connect, null);
        ButterKnife.bind(this, view);

        rlvIntro.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                    setTab("intro", view);

            }
        });
        rlvMeditate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                   setTab("meditate", view);
            }
        });
        rlvBenefits.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                setTab("benefits", view);
            }
        });
        setTab("intro", view);

        return view;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTab(String s, View view) {
        setSelection(s);
//        getList(s, view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSelection(String s) {
        int heading = ContextCompat.getColor(masterContext, R.color.heading);
        int heading1 = ContextCompat.getColor(masterContext, R.color.white);
        if (s.equals("intro")) {
            rlvIntro.setBackgroundResource(R.drawable.read1);
            rlvBenefits.setBackgroundResource(R.drawable.read2);
            rlvMeditate.setBackgroundResource(R.drawable.read2);
            imgIntro.setColorFilter(heading1);
            txtIntro.setTextColor(heading1);
            imgMeditation.setColorFilter(heading);
            txtMeditation.setTextColor(heading);
            imgBenefit.setColorFilter(heading);
            txtBenefit.setTextColor(heading);

            relative1.setVisibility(View.VISIBLE);
            relative2.setVisibility(View.GONE);
            relative3.setVisibility(View.GONE);

        } else if (s.equals("meditate")) {
            rlvMeditate.setBackgroundResource(R.drawable.read1);
            rlvIntro.setBackgroundResource(R.drawable.read2);
            rlvBenefits.setBackgroundResource(R.drawable.read2);
            imgIntro.setColorFilter(heading);
            txtIntro.setTextColor(heading);
            imgMeditation.setColorFilter(heading1);
            txtMeditation.setTextColor(heading1);
            imgBenefit.setColorFilter(heading);
            txtBenefit.setTextColor(heading);
            relative1.setVisibility(View.GONE);
            relative3.setVisibility(View.GONE);
            relative2.setVisibility(View.VISIBLE);


        } else if (s.equals("benefits")) {
            rlvIntro.setBackgroundResource(R.drawable.read2);
            rlvMeditate.setBackgroundResource(R.drawable.read2);
            rlvBenefits.setBackgroundResource(R.drawable.read1);
            imgIntro.setColorFilter(heading);
            txtIntro.setTextColor(heading);

            imgMeditation.setColorFilter(heading);
            txtMeditation.setTextColor(heading);
            imgBenefit.setColorFilter(heading1);
            txtBenefit.setTextColor(heading1);
            relative1.setVisibility(View.GONE);
            relative2.setVisibility(View.GONE);
            relative3.setVisibility(View.VISIBLE);
        }
    }

}
