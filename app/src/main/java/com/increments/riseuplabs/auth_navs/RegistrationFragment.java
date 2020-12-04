package com.increments.riseuplabs.auth_navs;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.increments.riseuplabs.R;
import com.increments.riseuplabs.databinding.FragmentRegistrationBinding;
import com.increments.riseuplabs.models.User;
import com.increments.riseuplabs.viewmodels.UserViewModel;

public class RegistrationFragment extends Fragment implements View.OnClickListener {
    private FragmentRegistrationBinding mBinding;

    public RegistrationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    private UserViewModel mViewModel;
    private NavController mNavController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //user view model
        mViewModel =  new UserViewModel(requireActivity().getApplication());

        //navigation controller
        mNavController = Navigation.findNavController(view);

        //register btn
        mBinding.registerBtn.setOnClickListener(this);

        //terms & conditions text
        String termsConditions = "By clicking register, you're agree to <b>terms & conditions</b> and <b>Privacy Policy</b> of Rise UP Labs.";
        mBinding.termsConditionsTxt.setText(Html.fromHtml(termsConditions));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_btn) {
            registrationState();
        }
    }

    //manage registration with basic validation
    private void registrationState() {
        String name = mBinding.nameEt.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mBinding.nameTil.setError("Required | Min:3");
            mBinding.nameEt.requestFocus();
            return;
        }

        String userName = mBinding.usernameEt.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            mBinding.usernameEt.setError("Required | Min:5");
            mBinding.usernameEt.requestFocus();
            return;
        }

        String password = mBinding.passwordEt.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.passwordTil.setError("Required | Min:8");
            mBinding.passwordEt.requestFocus();
            return;
        }

        String phone = mBinding.phoneEt.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            mBinding.passwordTil.setError("Required | Min:11");
            mBinding.phoneEt.requestFocus();
            return;
        }

        mViewModel.inset(new User(name, userName, password, phone));
        mNavController.navigate(R.id.action_registrationFragment_to_loginFragment);
    }
}