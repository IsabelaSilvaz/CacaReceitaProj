package com.example.cacareceitaproj.classes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cacareceitaproj.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<Card> cardList;
    private OnItemClickListener listener;

    public CardAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardTitle.setText(card.getTitulo());
        Picasso.get().load(card.getImagemCard()).into(holder.cardImage);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView cardTitle;
        ImageView cardImage;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardImage = itemView.findViewById(R.id.card_image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

