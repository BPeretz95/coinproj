package com.example.bar.coinproj6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AdapterCoin extends RecyclerView.Adapter<AdapterCoin.ViewHolder> {

    private Context context;
    private List<DataCoin> dataCoin;

    public AdapterCoin(Context context, List dataCoin) {
        this.context = context;
        this.dataCoin = dataCoin;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(dataCoin.get(position));

        DataCoin pu = dataCoin.get(position);

        holder.pName.setText(pu.getcoinName());

    }

    @Override
    public int getItemCount() {
        return dataCoin.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;

        public ViewHolder(View itemView) {
            super(itemView);

            pName = (TextView) itemView.findViewById(R.id.pNametxt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DataCoin cpu = (DataCoin) view.getTag();

                    Toast.makeText(view.getContext(), cpu.getcoinName(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}