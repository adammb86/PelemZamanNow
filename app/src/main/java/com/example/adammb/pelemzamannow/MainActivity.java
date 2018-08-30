package com.example.adammb.pelemzamannow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LoaderManager.LoaderCallbacks<LinkedList<Pelem>> {
    @BindView(R.id.edt_cari_pelem)
    EditText edtCariFilm;
    @BindView(R.id.btn_cari)
    Button btnCariFilm;
    @BindView(R.id.rv_view_pelem)
    RecyclerView rvPelem;

    private ListPelemAdapter listPelemAdapter;
    private String cariJudul;
    private static final String EXTRA_TITLE = "extraTitle";
    private int loaderID=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pelem Zaman Now");


        btnCariFilm.setOnClickListener(this);
        rvPelem.setHasFixedSize(true);

        showRecyclerList();

        Bundle bundle=new Bundle();
        cariJudul = edtCariFilm.getText().toString();
        bundle.putString(EXTRA_TITLE,cariJudul);


        getSupportLoaderManager().initLoader(loaderID,bundle,this);
    }

    private void showRecyclerList() {
        rvPelem.setLayoutManager(new LinearLayoutManager(this));
        listPelemAdapter = new ListPelemAdapter(this);
        listPelemAdapter.setListPelem(new LinkedList<Pelem>());
        rvPelem.setAdapter(listPelemAdapter);
        //pelemData.getListPelem(listPelemAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cari) {
            Bundle bundle=new Bundle();
            cariJudul = edtCariFilm.getText().toString();
            bundle.putString(EXTRA_TITLE,cariJudul);

            getSupportLoaderManager().restartLoader(loaderID,bundle,this);
        }
    }

    @NonNull
    @Override
    public Loader<LinkedList<Pelem>> onCreateLoader(int id, @Nullable Bundle args) {
        String originalTitle="";

        if(args!=null)
            originalTitle=args.getString(EXTRA_TITLE);

        return new MyPelemLoader(this,originalTitle);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<LinkedList<Pelem>> loader, LinkedList<Pelem> data) {
        listPelemAdapter.setListPelem(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<LinkedList<Pelem>> loader) {
        listPelemAdapter.setListPelem(null);
    }
}
