package haris.org.futureschool.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import haris.org.futureschool.R;
import haris.org.futureschool.adapter.TabAdapter;

public class AkunFragment extends Fragment {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_akun, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter.addFragment(new Tab1Fragment(), "Sekolah favorit");
        tabAdapter.addFragment(new Tab2Fragment(), "Baru ini dikunjungi");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.sekolah_favorit);
        tabLayout.getTabAt(1).setIcon(R.drawable.recently);
        return view;
    }
}
