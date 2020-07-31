package com.destinyapp.mtb.Activity.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.destinyapp.mtb.Activity.ui.product.Pager.CategoryFragment;
import com.destinyapp.mtb.Activity.ui.product.Pager.ProdukFragment;
import com.destinyapp.mtb.Activity.ui.product.Pager.BlogFragment;
import com.destinyapp.mtb.Adapter.TabPagerAdapter;
import com.destinyapp.mtb.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class ProductFragment extends Fragment {
    private TabLayout Table;
    private AppBarLayout appBar;
    private ViewPager viewPager;
    private FragmentActivity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Table = (TabLayout)view.findViewById(R.id.tableLayout);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.AddFragment(new ProdukFragment(),"Katalog");
        adapter.AddFragment(new BlogFragment(),"Blog");
        adapter.AddFragment(new CategoryFragment(),"Rental Mesin");

        viewPager.setAdapter(adapter);
        Table.setupWithViewPager(viewPager);
    }
}