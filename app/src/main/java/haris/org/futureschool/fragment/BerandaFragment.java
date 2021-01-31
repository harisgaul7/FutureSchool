package haris.org.futureschool.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import haris.org.futureschool.PilihTopikActivity;
import haris.org.futureschool.R;

public class BerandaFragment extends Fragment {

    @Nullable
    @Override
    
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_beranda, container, false);
        Button topik = (Button)view.findViewById(R.id.btn_rekomendasi);

        topik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(view.getContext(), PilihTopikActivity.class);
                view.getContext().startActivity(x);
            }
        });


        return view;
    }
}
