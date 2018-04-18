package com.example.bar.coinproj6;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(dataCoin.get(position));

        DataCoin pu = dataCoin.get(position);

        holder.pName.setText(pu.getcoinName());
        holder.pSymbol.setText(pu.getcoinSymbol());



    }

    @Override
    public int getItemCount() {
        if(dataCoin.size()>25){
            return 25;
        }else{
        return dataCoin.size();
    }}

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pName;
        public TextView pSymbol;
        public View title;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            pName = (TextView) itemView.findViewById(R.id.pNametxt);
            pSymbol = (TextView) itemView.findViewById(R.id.pSymboltxt);

            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra(String.valueOf(R.id.pSymboltxt), pSymbol.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });

        }

    }




}
