package project.namramuni.Fragement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Activity.LoginActivity;
import project.namramuni.Activity.MainActivity;
import project.namramuni.Holder.MoreAdapter;
import project.namramuni.Listener.RecyclerTouchListener;
import project.namramuni.Pojo.MenuDataModel;
import project.namramuni.Pojo.MyData;
import project.namramuni.R;
import project.namramuni.UIDesign.SetFont;
import project.namramuni.preference.PreferenceManager;

public class FragmentMore extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.btnTitle)
    Button btnTitle;
    private static ArrayList<MenuDataModel> data;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_more, null);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        SetFont.setFont1(btnTitle,getContext(),SetFont.bold);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<MenuDataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new MenuDataModel(
                    MyData.nameArray[i],
                    MyData.drawableArray[i]
            ));
        }
        MoreAdapter adapter = new MoreAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position==0){
                    ((MainActivity) getActivity()).showFragment1(new FragmentMyProfile());
                }else if (position ==1){
                    ((MainActivity) getActivity()).showFragment1(new FragmentLucyDraw());

               } else if (position==2) {
//                    ((MainActivity) getActivity()).showFragment1(new FragmentNotification());
                }else if (position==3){
                    Intent email = new Intent(Intent.ACTION_SENDTO);
                    email.setData(Uri.parse("mailto:"));
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "super@test.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                    email.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(email, "Choose email"));
                } else if (position==4){
                    ((MainActivity) getActivity()).showFragment1(new FragmentContact());
                } else if (position==5){
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String sAux = "\nLet me recommend you this application\n\n";
                    sAux = sAux + "http://bit.do/eEPoH";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } else if (position==6){
                    ((MainActivity) getActivity()).showFragment1(new FragmentAbout());
                } else if (position==7){
                    PreferenceManager preferenceManager = new PreferenceManager(getContext());
                    preferenceManager.clearSharedPreferance();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finishAffinity();
                }
            }
        }));
        return view;
    }
}




