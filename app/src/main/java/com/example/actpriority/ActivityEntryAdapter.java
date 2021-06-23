package com.example.actpriority;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActivityEntryAdapter extends ArrayAdapter<ActivityEntry> {

    private Context context;
    private List<ActivityEntry> activityEntries = null;
    private MainActivity mainActivity;

    public ActivityEntryAdapter(Context context,  List<ActivityEntry> activityEntries,  MainActivity mainActivity) {
        super(context, 0, activityEntries);
        this.activityEntries = activityEntries;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ActivityEntry activityEntry = activityEntries.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.activity_entry_item, null);

        TextView title = (TextView) view.findViewById(R.id.title_entry);
        title.setText(
                "[" + activityEntry.getDiscipline() + "]" + " - " + activityEntry.getActivityName()
        );

        TextView pointsDescription = (TextView) view.findViewById(R.id.points_description);

        pointsDescription.setText(
                activityEntry.getPoints() + "pts" + " - " + activityEntry.getDescription()
        );

        TextView deadline = (TextView) view.findViewById(R.id.deadline);

        CheckedTextView deadlineAdvise = (CheckedTextView) view.findViewById(R.id.checkedTextView);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Date today = new Date();
        Date deadlineDate = activityEntry.getDeadline();

        long diffInMillies = Math.abs(deadlineDate.getTime() - today.getTime());

        if (diffInMillies >= 0.0) {
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            deadlineAdvise.setText("Faltam: " + diff + " dias.");
            if (diff > 0 && diff <= 2.0) {
                view.setBackgroundResource(R.color.red);
            } else {
                if (diff == 0.0) {
                    view.setBackgroundResource(R.color.alarm);
                }
            }
        } else {
            deadlineAdvise.setText("Prazo esgotado");
            deadlineAdvise.setVisibility(View.GONE);
            view.setBackgroundResource(R.color.alarm);
        }

        deadline.setText(format.format(deadlineDate));

        return view;
    }
}
