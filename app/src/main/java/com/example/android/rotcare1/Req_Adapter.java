//package com.example.android.rotcare1;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class Req_Adapter extends RecyclerView.Adapter<Req_Adapter.MyViewHolder> {
//
//    private Context context;
//    private ArrayList<Doctor> Doctors_List;
//    public String DoctorName;
//
//    public Req_Adapter(Pending_req_Activity c, ArrayList<Doctor> u) {
//        context = c;
//        Doctors_List = u;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_pending_req_, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//
//
//        holder.graduate.setText(Doctors_List.get(position).getDoc_graduate());
//        holder.dpt.setText(Doctors_List.get(position).getDoc_dpt());
//        holder.name.setText(Doctors_List.get(position).getDoc_name());
//        Picasso.get().load(Doctors_List.get(position).getDoc_profile()).into(holder.image);
//
//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, Doc_InfoActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//                DoctorName = Doctors_List.get(position).getDoc_name();
//                Log.e("DOCTORNAME", "YOYOYOYOYO" + DoctorName);
//
//                intent.putExtra("image", Doctors_List.get(position).getDoc_profile());
//                intent.putExtra("name", Doctors_List.get(position).getDoc_name());
//                intent.putExtra("graduate", Doctors_List.get(position).getDoc_graduate());
//                intent.putExtra("dpt", Doctors_List.get(position).getDoc_dpt());
//                context.startActivity(intent);
//            }
//        });
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return Doctors_List.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        ImageView image;
//        TextView name, graduate, dpt;
//        RelativeLayout relative;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//            image = itemView.findViewById(R.id.image);
//            name = itemView.findViewById(R.id.name);
//            graduate = itemView.findViewById(R.id.graduate);
//            dpt = itemView.findViewById(R.id.dpt);
//            relative = itemView.findViewById(R.id.relative);
//        }
//    }
//}
//
