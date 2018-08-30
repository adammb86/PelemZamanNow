package com.example.adammb.pelemzamannow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPelemActivity extends AppCompatActivity {
    @BindView(R.id.tv_detail_original_title)
    TextView tvDetailOriginalTitle;
    @BindView(R.id.img_detail_poster)
    ImageView imgDetailPoster;
    @BindView(R.id.tv_detail_rate_film)
    TextView tvDetailRateFilm;
    @BindView(R.id.tv_detail_vote_count)
    TextView tvVoteCount;
    @BindView(R.id.tv_detail_release_date)
    TextView tvReleaseDate;
    @BindView(R.id.tv_detail_description)
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pelem);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pelem Zaman Now");

        Bundle intent = getIntent().getExtras();
        tvDetailOriginalTitle.setText(intent.getString(ListPelemAdapter.EXTRA_TITLE));

        Glide.with(this)
                .load(intent.getString(ListPelemAdapter.EXTRA_IMAGE_PATH))
                .crossFade()
                .into(imgDetailPoster);


        tvDetailRateFilm.setText(String.valueOf(intent.getDouble(ListPelemAdapter.EXTRA_VOTE_AVERAGE)));
        tvVoteCount.setText(String.valueOf(intent.getInt(ListPelemAdapter.EXTRA_VOTE_COUNT)));
        tvDescription.setText(intent.getString(ListPelemAdapter.EXTRA_DESCRIPTION));

        SimpleDateFormat releaseDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date date = releaseDate.parse(intent.getString(ListPelemAdapter.EXTRA_RELEASEDATE));
            SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMMM-yyyy", Locale.US);
            tvReleaseDate.setText(formattedDate.format(date));
        } catch (ParseException e) {
            tvReleaseDate.setText(intent.getString(ListPelemAdapter.EXTRA_RELEASEDATE));
            e.printStackTrace();
        }
    }
}
