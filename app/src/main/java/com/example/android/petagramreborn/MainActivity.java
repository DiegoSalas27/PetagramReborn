package com.example.android.petagramreborn;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.petagramreborn.adapter.MascotaAdaptador;
import com.example.android.petagramreborn.adapter.PageAdapter;
import com.example.android.petagramreborn.fragment.PerfilFragment;
import com.example.android.petagramreborn.fragment.RecyclerViewFragment;
import com.example.android.petagramreborn.pojo.Mascota;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar miActionbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        miActionbar = (Toolbar) findViewById(R.id.miActionbar);
        setSupportActionBar(miActionbar);
        setUpViewPager();

    }
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment()); // primer tab
        fragments.add(new PerfilFragment()); //segundo tab

        return fragments;
    }
    private void setUpViewPager(){
        //pasamos el soporte de fragment manager y la lista de fragments que queremos agregar al viewPager
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.mContacto:
                Intent intent = new Intent(this, ActivityContacto.class);
                startActivity(intent); break;
            case R.id.mAcercade:
                Intent intent2 = new Intent(this, ActivityAbout.class);
                startActivity(intent2); break;
        }

        return super.onOptionsItemSelected(item);
    }
}
