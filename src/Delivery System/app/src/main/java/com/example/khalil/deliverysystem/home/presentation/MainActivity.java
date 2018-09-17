package com.example.khalil.deliverysystem.home.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.khalil.deliverysystem.R;
import com.example.khalil.deliverysystem.home.domain.interactor.GetDeliveryLists;
import com.example.khalil.deliverysystem.home.domain.model.DeliveryModel;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepository;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepositoryImp;
import com.example.khalil.deliverysystem.home.repository.DeliveryListRepositoryMockImp;
import com.example.khalil.deliverysystem.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DeliveryHomeContract.View {
    private DeliveryHomePresenter deliveryHomePresenter;
    private RecyclerView mDeliveryRecycler;
    private DeliveryRecyclerAdapter adapter;
    private DeliveryRecyclerAdapter.DeliveryItemClickListener deliveryItemClickListener;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initPresenter();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.things_to_deliver);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initView() {
        mDeliveryRecycler = findViewById(R.id.delivery_list_recycler);
        mDeliveryRecycler.addOnScrollListener(new RecyclerViewScroller());

        adapter = new DeliveryRecyclerAdapter();
        deliveryItemClickListener = new DeliveryItemClickListenerImp();
        adapter.setDeliveryItemClickListener(deliveryItemClickListener);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mDeliveryRecycler.setLayoutManager(layoutManager);
        mDeliveryRecycler.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initPresenter() {
        //   DeliveryListRepository deliveryListRepository = new DeliveryListRepositoryImp();
        DeliveryListRepository deliveryListRepository = new DeliveryListRepositoryMockImp();
        GetDeliveryLists getDeliveryLists = new GetDeliveryLists(deliveryListRepository);
        deliveryHomePresenter = new DeliveryHomePresenter(this, getDeliveryLists);
        deliveryHomePresenter.loadDeliveryList();
    }

    @Override
    public void showDeliveryList(List<DeliveryModel> deliveryList) {
        adapter.setDeliveryModelList(deliveryList);
    }

    @Override
    public void onError(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class DeliveryItemClickListenerImp implements DeliveryRecyclerAdapter.DeliveryItemClickListener {
        @Override
        public void onItemClick(DeliveryModel deliveryModel) {
            Intent intent = new Intent(MainActivity.this, DeliveryDetailsActivity.class);
            intent.putExtra(Constants.DELIVERY_ITEM_DETAILS, deliveryModel);
            startActivity(intent);
        }
    }

    private class RecyclerViewScroller extends RecyclerView.OnScrollListener {
        private boolean mLoading = false;
        private int previousState = 0;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (dy > 0 && layoutManager.getChildCount() > 0) {
                // Calculations..
                int indexOfLastItemViewVisible = layoutManager.getChildCount() - 1;
                View lastItemViewVisible = layoutManager.getChildAt(indexOfLastItemViewVisible);
                int adapterPosition = layoutManager.getPosition(lastItemViewVisible);
                boolean isLastItemVisible = (adapterPosition == adapter.getItemCount() - 1);

                // check
                if (!mLoading && isLastItemVisible) {
                    Log.d("Scroll end", "position=" + adapterPosition);
                    onLoadMore();
                }
            }
        }

        private void onLoadMore() {
            // controller.start();
            deliveryHomePresenter.loadDeliveryList();
            mLoading = true;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && previousState == RecyclerView.SCROLL_STATE_SETTLING && mLoading) {
                mLoading = false;
                onLoadMore();
            }
            Log.d("Scroll state", "previous= " + previousState + ", new state= " + newState);

            previousState = newState;

        }

    }

}
