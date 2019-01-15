package com.example.viktor.k3_soft.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viktor.k3_soft.Const;
import com.example.viktor.k3_soft.R;
import com.example.viktor.k3_soft.model.ItemViewModel;
import com.example.viktor.k3_soft.source.pojoMovies.Result;
import com.example.viktor.k3_soft.view.Fragments.FragmentAddInform;
import com.example.viktor.k3_soft.view.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicInteger;


public class ItemAdapter extends PagedListAdapter<Result, ItemAdapter.ItemViewHolder> {

    private Context mCtx;

    public ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_recyclerview_grid, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {

     final    Result result = getItem(position);

        if (result != null) {

           Picasso.get().load(Const.BASE_IMAGE_PATH + result.getPosterPath()).into(holder.imageView);
            holder.title.setText(result.getTitle());
            holder.rating.setText(String.valueOf(result.getVoteAverage()) + "/10 (" + result.getVoteCount() + ")");
holder.layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Log.d("lof", "onClick: ");
        ((MainActivity) mCtx).recyclerView.setVisibility(View.INVISIBLE);
        FragmentAddInform inform = FragmentAddInform.newInstance(result.getTitle(), result.getOverview().toString(), result.getPosterPath());

        ((AppCompatActivity) mCtx).getSupportFragmentManager().beginTransaction()
                .addToBackStack("add_info")
                .add(R.id.FrLoy, inform).commit();
        // TODO: 15.01.19 setEnable -true
    }
});

        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }

    }




    private static DiffUtil.ItemCallback<Result> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Result>() {
                @Override
                public boolean areItemsTheSame(Result oldItem, Result newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(Result oldItem, Result newItem) {
                    return oldItem.equals(newItem);
                }
            };


    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView rating;
        ConstraintLayout layout;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_grid);
            title = itemView.findViewById(R.id.title_grid);
            rating = itemView.findViewById(R.id.rating_grid);
            layout = itemView.findViewById(R.id.cl_grid);

        }
    }
}

