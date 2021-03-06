package com.shetouane.armitage.fragments;

import com.shetouane.armitage.MainService;
import com.shetouane.armitage.R;
import com.shetouane.armitage.activities.AttackHallActivity;
import com.shetouane.armitage.structures.HostsAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HostsFragment extends Fragment {

	public static ListView listview;
	public static HostsAdapter listAdapter;

	public static final HostsFragment newInstance() {
		HostsFragment fragment = new HostsFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_hosts, container, false);

		listview = (ListView) view.findViewById(R.id.targetsFragmentListView);
		listview.setEmptyView(view.findViewById(R.id.targetsFragmentListViewEmpty));
		listAdapter = new HostsAdapter(getActivity().getApplicationContext(),
				MainService.hostsList);

		listview.setAdapter(listAdapter);

		if (AttackHallActivity.getCurListPosition() + 1 > MainService.hostsList
				.size())
			AttackHallActivity.setCurListPosition(0);

		listAdapter.setSelectedIndex(AttackHallActivity.getCurListPosition());

		setupListViewListener();
		return view;
	}

	private void setupListViewListener() {
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getActivity().openContextMenu(view);
			}
		});
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
		super.onActivityCreated(savedState);
		registerForContextMenu(listview);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser && listAdapter != null)
			listAdapter.notifyDataSetChanged();
	}

	public static void updateHostsRecords() {
		if (listAdapter != null && AttackHallActivity.getActivity() != null) {
			
			AttackHallActivity.getActivity().runOnUiThread(new Runnable() {  
                @Override
                public void run() {
    				listAdapter.notifyDataSetChanged();
                }
            });
		}
		
	}
}
