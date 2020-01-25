package com.suman.kennelservice.ui.MyProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    CircleImageView profileimg1;
    private TextView tvfname, tvlname, tvaddress, tvphone, tvemail, tvusername;


    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        profileimg1 = view.findViewById(R.id.profileimg1);
        tvfname = view.findViewById(R.id.tvfname);
        tvlname = view.findViewById(R.id.tvlname);
        tvaddress = view.findViewById(R.id.tvaddress);
        tvphone = view.findViewById(R.id.tvphone);
        tvemail = view.findViewById(R.id.tvemail);
        tvusername = view.findViewById(R.id.tvusername);

        loadcurrentuser();


        return view;
    }

    private void loadcurrentuser() {
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<User> userCall = userapi.getUserDetails(url.token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = url.imagePath + response.body().getImage();
//                tvfname.setText(user.getFirstName());
//                tvlname.setText(user.getLastName());
//                tvaddress.setText(user.getAddress());
//                tvemail.setText(user.getEmail());
//                tvphone.setText(user.getPhoneNumber());
//                tvusername.setText(user.getUsername());
                Picasso.get().load(imgPath).into(profileimg1);


//                StrictModeClass.StrictMode();
//                try {
//                    URL url = new URL(imgPath);
//                    imgProgileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }



        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
            // TODO: Use the ViewModel
        }


}
