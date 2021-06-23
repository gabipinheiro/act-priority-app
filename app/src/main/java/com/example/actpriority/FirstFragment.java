package com.example.actpriority;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.fragment.NavHostFragment;

import com.example.actpriority.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public class LoadEntriesAsyncTask extends AsyncTask<Void, Void, List<ActivityEntry>> {

        public FragmentFirstBinding binding;
        public Context context;

        LoadEntriesAsyncTask(FragmentFirstBinding binding, Context context){
            this.binding = binding;
            this.context = context;
        }

        @Override
        protected List<ActivityEntry> doInBackground(Void... voids) {
            return ActivitiesManager.getAll(this.context);
        }

        protected void onPostExecute(List<ActivityEntry> result) {
            MainActivity main = (MainActivity) getActivity();
            ActivityEntryAdapter adapter = new ActivityEntryAdapter(this.context, result, main);
            this.binding.activitiesList.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        new LoadEntriesAsyncTask(binding, getContext()).execute();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}