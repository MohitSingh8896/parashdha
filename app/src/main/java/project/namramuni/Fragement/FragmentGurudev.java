package project.namramuni.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import project.namramuni.R;

public class FragmentGurudev extends Fragment {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPagerguru;
    MyAdapter myAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gurudev, null);
        ButterKnife.bind(this, view);
        myAdapter = new MyAdapter(getChildFragmentManager(), tablayout.getTabCount());
        viewPagerguru.setAdapter(myAdapter);
        viewPagerguru.setOffscreenPageLimit(0);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (viewPagerguru.getCurrentItem() == 2) {
                    viewPagerguru.setAdapter(null);
                    viewPagerguru.setAdapter(myAdapter);
                } else if (viewPagerguru.getCurrentItem() == 3) {
                    viewPagerguru.setAdapter(null);
                    viewPagerguru.setAdapter(myAdapter);
                }else if (viewPagerguru.getCurrentItem() == 4) {
                    viewPagerguru.setAdapter(null);
                    viewPagerguru.setAdapter(myAdapter);
                }
                viewPagerguru.setCurrentItem(tab.getPosition());
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
            this.tabCount = tabCount;
        }

        @Override
        public Fragment getItem(int position) {
                            switch (position) {
                                case 0:
                                    return new FragmentWatch();
                                case 1:
                                   // return new FragmentListen();
                                    return new FragmentRead();
                                case  2:
                                    return  new FragmentConnect();
                                case 3:
                                    return new FragmentEvents();

                                  //  return new FragmentRead();
                //                case 3:
                //                    return new FragmentEvents();

                            }
                            return null;
                        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
