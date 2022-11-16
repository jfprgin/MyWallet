package com.example.mywallet;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static androidx.core.app.ActivityCompat.recreate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaPrihoda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPrihoda extends Fragment {

    RecyclerView recyclerView;
    DataBaseHelper db;
    ArrayList<String>  id_prihoda, kategorija, vrijednost, biljeska;
    CustomAdapter customAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaPrihoda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPrihoda.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPrihoda newInstance(String param1, String param2) {
        ListaPrihoda fragment = new ListaPrihoda();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_prihoda, container, false);
        recyclerView = view.findViewById(R.id.rw_prihod);

        db = new DataBaseHelper(getContext());
        id_prihoda = new ArrayList<>();
        kategorija = new ArrayList<>();
        vrijednost = new ArrayList<>();
        biljeska = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(),getContext(), kategorija, vrijednost, biljeska, id_prihoda);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 1) {
            getActivity().recreate();
        }
    }



    void storeDataInArrays() {
        Cursor cursor = db.readAllDataPrihod();
       if(cursor != null) {
            while ( cursor.moveToNext()) {
                kategorija.add(cursor.getString(0));
                vrijednost.add(cursor.getString(1));
                biljeska.add(cursor.getString(2));
                id_prihoda.add(cursor.getString(3));

        }
       }
    }
}