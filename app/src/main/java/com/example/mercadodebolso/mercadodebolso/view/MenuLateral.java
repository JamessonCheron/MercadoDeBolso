package com.example.mercadodebolso.mercadodebolso.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mercadodebolso.mercadodebolso.R;
import com.example.mercadodebolso.mercadodebolso.controller.UsuarioController;
import com.example.mercadodebolso.mercadodebolso.model.Usuario;
import com.example.mercadodebolso.mercadodebolso.sistema.LoginInfo;
import com.example.mercadodebolso.mercadodebolso.view.adapters.AdapterDrawer;
import com.example.mercadodebolso.mercadodebolso.view.adapters.NavItem;
import com.example.mercadodebolso.mercadodebolso.view.adapters.PreferencesFragment;

import java.util.ArrayList;

/**
 * Created by Cais - Automação on 25/05/2016.
 */
public class MenuLateral {
    private Context mContext;
    private Intent drawerListItem;
    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private Activity activityPai;
    private LoginInfo sistema;
    private Usuario usuario;
    private UsuarioController usuarioController;

    public MenuLateral(Context context){
        mContext = context;
        this.activityPai = (Activity) context;
        usuarioController = new UsuarioController(context);
        sistema = LoginInfo.getInstance(context);

        usuario = usuarioController.getUsuario(sistema.getUserConnected());
    }

    public void loadDrawerLayout(){

        TextView userName = (TextView) activityPai.findViewById(R.id.userName);
        Button btnMenu = (Button) activityPai.findViewById(R.id.btn_menu);

        userName.setText(usuario.getNome());

        mNavItems.add(new NavItem("Home", "Minhas listas"));
        mNavItems.add(new NavItem("Histórico", "Compras realizadas"));
        mNavItems.add(new NavItem("Sair", "log off"));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) activityPai.findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) activityPai.findViewById(R.id.drawerPane);
        mDrawerList = (ListView) activityPai.findViewById(R.id.navList);
        AdapterDrawer adapter = new AdapterDrawer(activityPai, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerPane);
            }
        });

    }

    /*
    * Called when a particular item from the navigation drawer
    * is selected.
    * */
    private void selectItemFromDrawer(int position) {
        NavItem itemClicado = (NavItem) mDrawerList.getAdapter().getItem(position);
//        PreferencesFragment fragment = new PreferencesFragment();
//
//        FragmentManager fragmentManager = activityPai.getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.Main, fragment)
//                .commit();

        switchView( position );

        mDrawerList.setItemChecked(position, true);
        activityPai.setTitle(mNavItems.get(position).getmTitle());

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    private void switchView( int pos){

        switch (pos){
            case 0: //Home
                drawerListItem = new Intent(mContext, Main.class);
                ((Activity) mContext).startActivityForResult(drawerListItem, 1);
                break;
            case 1: //Histórico
                Intent novaIntent = new Intent(activityPai, HistoricoView.class);
                activityPai.startActivityForResult(novaIntent, 1);
                break;
            case 2: //Sair
                sistema.setUserConnected(0);
                drawerListItem = new Intent(activityPai, LoginView.class);
                activityPai.startActivityForResult(drawerListItem, 1);
                break;
            default :
        }
    }

}
