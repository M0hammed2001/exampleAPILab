package uk.ac.aston.cs3mdd.apiexample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.aston.cs3mdd.apiexample.databinding.FragmentFirstBinding;
import uk.ac.aston.cs3mdd.apiexample.model.User;
import uk.ac.aston.cs3mdd.apiexample.model.UserListAdapter;
import uk.ac.aston.cs3mdd.apiexample.model.UsersViewModel;
import uk.ac.aston.cs3mdd.apiexample.service.RandomUser;
import uk.ac.aston.cs3mdd.apiexample.service.UserRepository;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private UsersViewModel viewModel;

    private RecyclerView mRecyclerView;
    private UserListAdapter mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new UserListAdapter(getContext(), viewModel.getAllUsers().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomUser service = retrofit.create(RandomUser.class);

        viewModel.requestRandomUsers(new UserRepository(service));
        final Observer<List<User>> userListObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> userList) {
                // Update the UI
                mAdapter.updateData(userList);
            }
        };
        viewModel.getAllUsers().observe(getViewLifecycleOwner(), userListObserver);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}