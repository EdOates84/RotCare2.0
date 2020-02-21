package com.example.android.rotcare1;

import android.app.DownloadManager;
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

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class Req_Status_Adapter extends RecyclerView.Adapter<Req_Status_Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<Request> Request_List;
    public String Name;

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
        if (Request_List.get(position).getStatus() == 0) {
            holder.Status.setText("Pending");
        }
        if (Request_List.get(position).getStatus() == 1) {

//            holder.alloted_to.setText();
            holder.alloted_to.setText(Request_List.get(position).getAcceptname());
            holder.alloted_no.setText(Request_List.get(position).getAcceptno());
            holder.Status.setText("Process");
        }
        if (Request_List.get(position).getStatus() == 2) {
            holder.Status.setText("Complete");
        }
//        Picasso.get().load(Request_List.get(position).getDoc_profile()).into(holder.image);


        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Req_Status_Info_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Name = Request_List.get(position).getName();
//                Log.e("DOCTORNAME", "YOYOYOYOYO" + DoctorName);

//                intent.putExtra("image", Request_List.get(position));
                intent.putExtra("name", Request_List.get(position).getName());
                intent.putExtra("tok", String.valueOf(Request_List.get(position).getToken()));
                intent.putExtra("subject", Request_List.get(position).getSub());
                intent.putExtra("Uiid", Request_List.get(position).getUid());
                intent.putExtra("dis",Request_List.get(position).getDis());
                intent.putExtra("sta",Request_List.get(position).getStatus());
                intent.putExtra("acceptname",Request_List.get(position).getAcceptname());
                intent.putExtra("acceptno",Request_List.get(position).getAcceptno());
                context.startActivity(intent);
            }
        });

//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Req_Status_Info_Activity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
////                DoctorName = Request_List.get(position).getDoc_name();
//                Log.e("DOCTORNAME", "YOYOYOYOYO" + DoctorName);
//
////                intent.putExtra("image", Request_List.get(position).getDoc_profile());
////                intent.putExtra("name", Request_List.get(position).getDoc_name());
////                intent.putExtra("graduate", Request_List.get(position).getDoc_graduate());
////                intent.putExtra("dpt", Request_List.get(position).getDoc_dpt());
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
        TextView name, token, subject, alloted_to, mobile_no, Status , alloted_no;
        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            token = itemView.findViewById(R.id.tok);
            subject = itemView.findViewById(R.id.sub);
            alloted_to = itemView.findViewById(R.id.alloted_name);
            alloted_no = itemView.findViewById(R.id.alloted_mob_no);
            Status = itemView.findViewById(R.id.status);
            relative = itemView.findViewById(R.id.relative);
        }
    }
}

