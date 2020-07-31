package com.destinyapp.mtb.Activity.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.destinyapp.mtb.Activity.ui.contact.Pager.AboutUsFragment;
import com.destinyapp.mtb.Activity.ui.contact.Pager.ContactsFragment;
import com.destinyapp.mtb.Activity.ui.contact.Pager.PhotoAlbumFragment;
import com.destinyapp.mtb.Adapter.TabPagerAdapter;
import com.destinyapp.mtb.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class ContactFragment extends Fragment {
    private TabLayout Table;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    private FragmentActivity context;
    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Table = (TabLayout)view.findViewById(R.id.tableLayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.AddFragment(new PhotoAlbumFragment(),"Photo Album");
        adapter.AddFragment(new ContactsFragment(),"Contact");
        adapter.AddFragment(new AboutUsFragment(),"Tentang Kami");

        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }
}