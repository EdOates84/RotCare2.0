package com.example.android.rotcare1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Alloted_Req_Adapter extends RecyclerView.Adapter<Alloted_Req_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Request> Request_List;
    public String DoctorName;

    public Alloted_Req_Adapter(Alloted_req_Activity c, ArrayList<Request> u) {
        context = c;
        Request_List = u;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.pending_req_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.name.setText(Request_List.get(position).getName());
        holder.token.setText(String.valueOf(Request_List.get(position).getToken()));
        holder.subject.setText(Request_List.get(position).getSub());
//        holder.Status.setText(String.valueOf(Request_List.get(position).getStatus()));
//        if (Request_List.get(position).getStatus() == 0){
//            holder.Status.setText("Pending");
//        }
//        if (Request_List.get(position).getStatus() == 1){
//            holder.Status.setText("Process");
//        }
//        if (Request_List.get(position).getStatus() == 2){
//            holder.Status.setText("Complete");
//        }
//        Picasso.get().load(Request_List.get(position).getDoc_profile()).into(holder.image);

     holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Alloted_Req_Info_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                Name = Request_List.get(position).getName();

//                intent.putExtra("image", Request_List.get(position));
                intent.putExtra("name", Request_List.get(position).getName());
                intent.putExtra("token",String.valueOf(Request_List.get(position).getToken()));
                intent.putExtra("subject", Request_List.get(position).getSub());
                intent.putExtra("Uiid", Request_List.get(position).getUid());
                intent.putExtra("dis",Request_List.get(position).getDis());
//                intent.putExtra("status", Request_List.get(position).getStatus());
//                intent.putExtra("alloted_to", Request_List.get(position));
//                intent.putExtra("mobile_no", Request_List.get(position).getStatus());

                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return Request_List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,token,subject,mobile_no;
        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            token = itemView.findViewById(R.id.tok);
            subject = itemView.findViewById(R.id.sub);
            mobile_no = itemView.findViewById(R.id.mobile_no);
            relative = itemView.findViewById(R.id.relative);
        }
    }
}

