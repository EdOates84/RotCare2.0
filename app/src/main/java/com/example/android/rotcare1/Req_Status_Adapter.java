package com.example.android.rotcare1;

import android.content.Context;
import android.content.Intent;
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

public class Req_Status_Adapter extends RecyclerView.Adapter<Req_Status_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Request> Request_List;
    public String DoctorName;

    public Req_Status_Adapter(My_req_Activity c, ArrayList<Request> u) {
        context = c;
        Request_List = u;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.req_status_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.name.setText(Request_List.get(position).getName());
        holder.token.setText(String.valueOf(Request_List.get(position).getToken()));
        holder.subject.setText(Request_List.get(position).getSub());
//        holder.Status.setText(Request_List.get(position).getStatus());
//        Picasso.get().load(Request_List.get(position).getDoc_profile()).into(holder.image);

//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Doc_InfoActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                DoctorName = Request_List.get(position).getDoc_name();
//                Log.e("DOCTORNAME", "YOYOYOYOYO" + DoctorName);
//
//                intent.putExtra("image", Request_List.get(position).getDoc_profile());
//                intent.putExtra("name", Request_List.get(position).getDoc_name());
//                intent.putExtra("graduate", Request_List.get(position).getDoc_graduate());
//                intent.putExtra("dpt", Request_List.get(position).getDoc_dpt());
//                context.startActivity(intent);
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return Request_List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,token,subject,alloted_to,mobile_no,Status;
        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            token = itemView.findViewById(R.id.tok);
            subject = itemView.findViewById(R.id.sub);
            alloted_to = itemView.findViewById(R.id.alloted_name);
            mobile_no = itemView.findViewById(R.id.alloted_mob_no);
            Status = itemView.findViewById(R.id.status);
            relative = itemView.findViewById(R.id.relative);
        }
    }
}
