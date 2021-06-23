package com.example.actpriority;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.fragment.NavHostFragment;

import com.example.actpriority.databinding.FragmentSecondBinding;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    DatePickerDialog picker;
    EditText eText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);


        EditText eText= binding.deadlineValue;
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                monthOfYear += 1;

                                String convertedMonth = monthOfYear < 10 ? "0" + monthOfYear : Integer.toString(monthOfYear);

                                eText.setText(dayOfMonth + "/" + convertedMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    String title = binding.titleValue.getText().toString();
                    String discipline = binding.disciplineValue.getText().toString();
                    String deadline = binding.deadlineValue.getText().toString();
                    Float points = Float.parseFloat(binding.pointsValue.getText().toString());
                    String description = binding.descriptionValue.getText().toString();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate inputDate = LocalDate.parse(deadline,formatter);

                    ActivityEntry entryList = new ActivityEntry(0, discipline, title, points, description, Date.from(inputDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            ActivitiesManager.addEntry(getContext(), entryList);
                        }
                    });
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Dados preenchidos incorretamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}