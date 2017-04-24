package com.shixels.wemeet;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.shixels.wemeet.adapter.NavigationDrawerAdapter;
import com.shixels.wemeet.helper.FontChangeCrawler;
import com.shixels.wemeet.helper.UIHelper;

public class NavigationDrawerFragment extends Fragment {

    private static String TAG = NavigationDrawerFragment.class.getSimpleName();

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;
    TextView  userCompanyName, userRole;
    ImageView userPhoto, imageSettings, imageLike, imageWidgets;

    String navTitles[];
    public TypedArray navIcons;

    public NavigationDrawerFragment() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // drawer labels

        navTitles = getActivity().getResources().getStringArray(R.array.navDrawerItems);
        navIcons = getActivity().getResources().obtainTypedArray(R.array.navDrawerIcons);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new NavigationDrawerAdapter(navTitles, navIcons, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                NavigationDrawerAdapter.selected_item =position;
                recyclerView.getAdapter().notifyDataSetChanged();

                drawerListener.onDrawerItemSelected(view, position);
                if (position != 0) {
                    mDrawerLayout.closeDrawer(containerView);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
            }));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        userPhoto = (ImageView) layout.findViewById(R.id.image_view);

        userCompanyName = (TextView) layout.findViewById(R.id.text_company_name);
        //userCompanyName.setText("");  //change company name from here
        userRole = (TextView) layout.findViewById(R.id.text_role);
        //userRole.setText("");  //change user role from here

        userPhoto = (ImageView) layout.findViewById( R.id.image_view);

        //change image when we have image resource from internet request
        Bitmap bitmap = BitmapFactory.decodeResource( getActivity().getResources(), R.drawable.image);

        userPhoto.setImageBitmap(UIHelper.getCircularBitmapWithWhiteBorder(bitmap, 3));

        imageSettings = (ImageView) layout.findViewById(R.id.image_nav_settings);
            imageSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go to settings page
                }
            });
            imageLike = (ImageView) layout.findViewById(R.id.image_heart);
            imageLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //like
                }
            });
            imageWidgets = (ImageView) layout.findViewById(R.id.image_item);
            imageWidgets.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        return layout;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);

                adapter.notifyDataSetChanged();

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                super.onDrawerSlide(drawerView, slideOffset);

                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public void openDrawer() {

        mDrawerLayout.openDrawer(containerView);
    }

    public void closeDrawer() {

        mDrawerLayout.closeDrawer(containerView);
    }

    public boolean isDrawerOpen() {

        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(containerView);
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //changing the fonts
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity().getAssets(), "fonts/proxima-nova-regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.getView());
    }
}