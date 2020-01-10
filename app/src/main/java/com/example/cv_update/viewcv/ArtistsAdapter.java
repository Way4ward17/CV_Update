package com.example.cv_update.viewcv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cv_update.R;


import java.util.List;

/**
 * Created by Way4ward on 4/17/2018.
 */

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {
    private Context mCtx;
    private List<ArtistBlog> artistList;
    private ArtistBlog artist;


    public ArtistsAdapter(Context mCtx, List<ArtistBlog> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.blog, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistViewHolder holder,final int position) {


        holder.fullname.setText(artistList.get(position).getFullname());
        holder.discipline.setText(artistList.get(position).getDiscipline());
        holder.language.setText(artistList.get(position).getLanguage());
        holder.phone.setText(artistList.get(position).getPhone1());
        holder.email.setText(artistList.get(position).getEmail());



       holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        artist =artistList.get(position);
                        Intent intent = new Intent(mCtx,
                                Fulldetails.class);
                        intent.putExtra("key", artist);
                        mCtx.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        ImageView imagecard;
        TextView fullname, discipline, language, phone, email;// itextViewAge, textViewCountry;
        CardView cardView;
        Button more;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.babaCard);
            fullname = itemView.findViewById(R.id.fullnamecard);
            discipline = itemView.findViewById(R.id.disciplinecard);
            language = itemView.findViewById(R.id.languagecard);
             phone = itemView.findViewById(R.id.phone1card);
                   more = itemView.findViewById(R.id.viewmorecard);
            email = itemView.findViewById(R.id.emailcard);

          //  textViewCountry = itemView.findViewById(R.id.text_view_country);
        }
    }
}
