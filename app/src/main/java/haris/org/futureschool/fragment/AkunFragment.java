package haris.org.futureschool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import haris.org.futureschool.LoginActivity;
import haris.org.futureschool.MainActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.SettingActivity;
import haris.org.futureschool.adapter.TabAdapter;
import haris.org.futureschool.session.SessionManager;

import static haris.org.futureschool.session.SessionManager.KEY_FULLNAME;

public class AkunFragment extends Fragment {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    TextView nama_lengkap;
    ImageView setting;

    SessionManager sm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_akun, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        sm = new SessionManager(getContext());

        setting = (ImageView) view.findViewById(R.id.iv_setting);
        nama_lengkap = (TextView) view.findViewById(R.id.txt_nama_lengkap);
        nama_lengkap.setText(sm.getDetailLogin().get(KEY_FULLNAME).toString());

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), SettingActivity.class);
                startActivity(in);
            }
        });

        Bundle argsBundle = new Bundle();
        argsBundle.putString("id", "kosong");

        tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter.addFragmentBundle(new Tab1Fragment(), "Sekolah favorit", argsBundle);
        tabAdapter.addFragmentBundle(new Tab2Fragment(), "Baru ini dikunjungi", argsBundle);

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.sekolah_favorit);
        tabLayout.getTabAt(1).setIcon(R.drawable.recently);
        return view;
    }
}
