package uk.ac.aston.cs3mdd.apiexample.model;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uk.ac.aston.cs3mdd.apiexample.FirstFragmentDirections;
import uk.ac.aston.cs3mdd.apiexample.R;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private List<User> mUserList;
    private final LayoutInflater mInflater;
//    private Object Picasso;

    public UserListAdapter(Context context,
                           List<User> userList) {
        mInflater = LayoutInflater.from(context);
        this.mUserList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.user_item,
                parent, false);
        return new UserViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.user = user;
        Name name = user.getName();
        String displayName = name.getTitle() + " " + name.getFirst() + " " + name.getLast();
        holder.usernameView.setText(displayName);
        String thumb = user.getPicture().getMedium();
        Picasso.get().load(thumb).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return this.mUserList.size();
    }

    public void updateData(List<User> list) {
        this.mUserList = list;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView usernameView;
        final UserListAdapter mAdapter;
        public User user;
        public final ImageView userImage;

        public UserViewHolder(@NonNull View itemView, UserListAdapter adapter) {
            super(itemView);
            usernameView = itemView.findViewById(R.id.username);
            this.userImage = itemView.findViewById(R.id.userimage);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            Log.i("AJB", "You clicked " + user.toString());
//        }
        @Override
        public void onClick(View view) {
            FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(user);
            Navigation.findNavController(view).navigate(action);
        }
    }

}
