package com.example.mywallet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UnosTroska#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnosTroska extends Fragment {

    Button btn_dodaj, btn_odustani;
    EditText et_kategorija, et_biljeska, et_vrijednost;
    Spinner spinner;

    DataBaseHelper dataBaseHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UnosTroska() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UnosTroska.
     */
    // TODO: Rename and change types and number of parameters
    public static UnosTroska newInstance(String param1, String param2) {
        UnosTroska fragment = new UnosTroska();
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
        View view = inflater.inflate(R.layout.fragment_unos_troska, container, false);

        btn_dodaj = view.findViewById(R.id.btn_dodaj1);
        btn_odustani = view.findViewById(R.id.btn_odustani1);
        et_vrijednost = view.findViewById(R.id.et_vrijednost1);
        et_biljeska = view.findViewById(R.id.et_biljeska1);
        spinner = view.findViewById(R.id.spinner2);

        dataBaseHelper = new DataBaseHelper(getContext());

        btn_dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrosakModel trosakModel = null;

                try {
                    trosakModel = new TrosakModel(-1, spinner.getSelectedItem().toString(), Integer.parseInt(et_vrijednost.getText().toString()), et_biljeska.getText().toString(), 1);
                    Toast.makeText(getContext(), "Uspješno dodano", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Greška", Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

                dataBaseHelper.addOneTrosak(trosakModel);

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);


            }
        });

        btn_odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
}