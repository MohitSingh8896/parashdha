package project.namramuni.Fragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import project.namramuni.Activity.MainActivity;
import project.namramuni.R;

public class FragmentNotification extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.notification, null);
        ((MainActivity) getActivity()).enableNavigationIcon();
        return view;
    }

}
