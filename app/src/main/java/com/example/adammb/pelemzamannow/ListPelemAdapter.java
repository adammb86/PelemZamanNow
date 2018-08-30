package com.example.adammb.pelemzamannow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListPelemAdapter extends RecyclerView.Adapter<ListPelemAdapter.CategoryViewHolder> {
    private Context context;
    private LinkedList<Pelem> listPelem;
    public static final String EXTRA_TITLE = "originalTitle";
    public static final String EXTRA_IMAGE_PATH = "imagePath";
    public static final String EXTRA_VOTE_AVERAGE = "voteAverage";
    public static final String EXTRA_VOTE_COUNT = "voteCount";
    public static final String EXTRA_DESCRIPTION = "extraDescription";
    public static final String EXTRA_RELEASEDATE = "releaseDate";

    public ListPelemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    public LinkedList<Pelem> getListPelem() {
        return listPelem;
    }

    public void setListPelem(LinkedList<Pelem> listPelem) {
        this.listPelem = listPelem;
        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_original_title)
        TextView tvOriginalTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_release_date)
        TextView tvReleaseDate;
        @BindView(R.id.img_poster_film)
        ImageView imgPosterFilm;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailIntent = new Intent(context, DetailPelemActivity.class);
                    detailIntent.putExtra(EXTRA_TITLE, getListPelem().get(getAdapterPosition()).getOriginal_title());
                    detailIntent.putExtra(EXTRA_IMAGE_PATH, getListPelem().get(getAdapterPosition()).getPoster_path());
                    detailIntent.putExtra(EXTRA_VOTE_AVERAGE, getListPelem().get(getAdapterPosition()).getVote_average());
                    detailIntent.putExtra(EXTRA_VOTE_COUNT, getListPelem().get(getAdapterPosition()).getVote_count());
                    detailIntent.putExtra(EXTRA_DESCRIPTION, getListPelem().get(getAdapterPosition()).getOverview());
                    detailIntent.putExtra(EXTRA_RELEASEDATE, getListPelem().get(getAdapterPosition()).getRelease_date());

                    context.startActivity(detailIntent);
                }
            });
        }
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pelem, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvOriginalTitle.setText(getListPelem().get(position).getOriginal_title());
        holder.tvDescription.setText(getListPelem().get(position).getOverview());

        SimpleDateFormat releaseDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date date = releaseDate.parse(getListPelem().get(position).getRelease_date());
            SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMMM-yyyy", Locale.US);
            holder.tvReleaseDate.setText(formattedDate.format(date));
        } catch (ParseException e) {
            holder.tvReleaseDate.setText(getListPelem().get(position).getRelease_date());
            e.printStackTrace();
        }

        Glide.with(context)
                .load(getListPelem().get(position).getPoster_path())
                .crossFade()
                .into(holder.imgPosterFilm);
    }

    @Override
    public int getItemCount() {
        return getListPelem().size();
    }


}
