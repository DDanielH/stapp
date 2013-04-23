package de.whs.stapp.presentation.views;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class StappCollectionPagerAdapter extends FragmentStatePagerAdapter {

	private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
	
	public StappCollectionPagerAdapter(FragmentManager fm) {
		super(fm);
	
		mFragments.add(new SessionFragment());
		mFragments.add(new HistoryFragment());
	}

	@Override
	public Fragment getItem(int index) {
        if(index < 0 && index > mFragments.size())
        	throw new IndexOutOfBoundsException();
        return mFragments.get(index);
	}

	@Override
	public int getCount() {

		return mFragments.size();
	}

}
