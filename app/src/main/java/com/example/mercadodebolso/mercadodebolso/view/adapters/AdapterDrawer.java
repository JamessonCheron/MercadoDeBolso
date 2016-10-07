package com.example.mercadodebolso.mercadodebolso.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.view.CadastroConta;
import com.example.mercadodebolso.mercadodebolso.view.Main;

import java.util.ArrayList;

/**
 * Created by jamesson on 5/22/2016.
 */
public class AdapterDrawer extends BaseAdapter {
    Context mContext;
    ArrayList<NavItem> mNavItems;
    Intent drawerListItem;

    public AdapterDrawer(Context context, ArrayList<NavItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        //ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavItems.get(position).getmTitle() );
        subtitleView.setText( mNavItems.get(position).getmSubtitle() );
        //iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }

}
