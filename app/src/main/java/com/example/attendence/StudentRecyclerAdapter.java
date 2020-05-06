package com.example.attendence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder> {
    List<Students> students;


    public void setStudents(List<Students> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_student, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Students students = this.students.get(position);
        holder.bindTo(students);


    }

    @Override
    public int getItemCount() {
        if (students == null)
            return 0;
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameTextView, textView2, textView3;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameTextView = itemView.findViewById(R.id.student_name);
            textView2 = itemView.findViewById(R.id.text_view_2);
            textView3 = itemView.findViewById(R.id.text_view_3);



        }

        public void bindTo(Students students){
            studentNameTextView.setText(students.getStudentName());
            textView2.setText(students.getMcneeseId());
            textView3.setText(students.getStudenId());



        }
    }
}
