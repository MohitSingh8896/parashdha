package project.namramuni.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.Listener.MediaPlayers;
import project.namramuni.R;

public class FragmentPunyaBank extends Fragment {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPagerguru;
    @Nullable
    @BindView(R.id.tabActivityBox)
    TabItem tabActivityBox;
    @Nullable
    @BindView(R.id.tabDivinity)
    TabItem tabDivinity;
//    @Nullable
//    @BindView(R.id.tabLearn)
//    TabItem tabLearn;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_punyabank, null);
        ButterKnife.bind(this, view);
        MyAdapter myAdapter = new MyAdapter(getChildFragmentManager(),tablayout.getTabCount());
        viewPagerguru.setAdapter(myAdapter);


        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerguru.setCurrentItem(tab.getPosition());
                MediaPlayers.getMusicStop();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPagerguru.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewPagerguru.setCurrentItem(Integer.parseInt(getArguments().getString("position")));
        return view;
    }
    public class MyAdapter extends FragmentStatePagerAdapter {
        int tabCount;
        public MyAdapter(FragmentManager manager, int tabCount) {
            super(manager);
            this.tabCount=tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentDiviniti();
                case 1:
                    return new FragmentActivityBox();
//                case 2:
//                    return new FragmentLearn();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
