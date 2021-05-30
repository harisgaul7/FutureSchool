package haris.org.futureschool.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import haris.org.futureschool.PilihTopikActivity;
import haris.org.futureschool.R;
import haris.org.futureschool.TestActivity;
import static android.Manifest.permission.CALL_PHONE;

public class RekomendasiFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_rekomendasi, container, false);

        Button topik = (Button)view.findViewById(R.id.btn_rekomendasi);

        topik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(view.getContext(), TestActivity.class);
                Intent x = new Intent(view.getContext(), PilihTopikActivity.class);
                view.getContext().startActivity(x);
            }
        });

        return view;
    }
}
