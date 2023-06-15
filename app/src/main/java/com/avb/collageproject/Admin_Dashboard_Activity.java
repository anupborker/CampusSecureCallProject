    package com.avb.collageproject;

    import static android.net.Uri.parse;

    import static com.avb.collageproject.QR_Scanner_Activity.hasPermissions;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.ActionBarDrawerToggle;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.app.AppCompatDelegate;
    import androidx.appcompat.widget.SwitchCompat;
    import androidx.appcompat.widget.Toolbar;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;
    import androidx.drawerlayout.widget.DrawerLayout;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import android.Manifest;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.content.pm.PackageManager;
    import android.content.res.Configuration;
    import android.net.Uri;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.CompoundButton;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;
    import android.widget.ToggleButton;

    import com.google.android.material.navigation.NavigationView;
    import com.google.firebase.auth.FirebaseAuth;


    public class Admin_Dashboard_Activity extends AppCompatActivity {
        private static final int REQUEST_CODE_OPEN_DOCUMENT = 1;
        private static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 1;
        private static final int PICK_IMAGE_REQUEST = 1;

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;
         SwitchCompat nightModeSwitch;
        ImageView headerImage;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_admin_dashboard);
            drawerLayout = findViewById(R.id.drawerLayout);
            navigationView = findViewById(R.id.MenuDrawer);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Menu menu = navigationView.getMenu();
            MenuItem menuItem = menu.findItem(R.id.menuNightMode);

            View actionView = menuItem.getActionView();
            // Retrieve the saved name and designation from SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "");

            String designation = sharedPreferences.getString("designation", "");
            System.out.println(name + "" + designation);

            // Set the name and designation in the NavigationView header
            View headerView = navigationView.getHeaderView(0);
            headerImage = headerView.findViewById(R.id.menu_image_111);
            TextView nameTextView = headerView.findViewById(R.id.menu_name_111);
            TextView designationTextView = headerView.findViewById(R.id.menu_desgination_111);
            nameTextView.setText(name);
            designationTextView.setText(designation);
            String imageUri = sharedPreferences.getString("IMAGE_KEY", null);
            System.out.println("image" +imageUri);
            loadFragment(new ReportFragment());
            if (actionView instanceof SwitchCompat) {
                nightModeSwitch = (SwitchCompat) actionView;
            }
            Log.d("TAG", "Toolbar inflated successfully");

            nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    if (isChecked && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
                        // Set the app to dark mode
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    } else if (!isChecked && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
                        // Set the app to light mode
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
            });


            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.menuReport){
                        loadFragment(new ReportFragment());

                    }else if(id == R.id.menuStudent){
                        loadFragment(new StudentFragment());

                    }else  if(id == R.id.menuFaculty){
                        loadFragment(new Teachers_Deatilas_menuFragment());
                    }else  if(id == R.id.menuStaff){
                        loadFragment(new Staff_menu_Fragment());
                    }else  if (id == R.id.menusSettings){
                        loadFragment(new SettingsFragment());
                    }else  if(id == R.id.menuLogout){
                        logout();
                    }
                    return true;
                }
            });
        }

        private void logout() {
            Intent logout = new Intent(Admin_Dashboard_Activity.this, MainActivity.class);
            FirebaseAuth.getInstance().signOut();
            startActivity(logout);
            finish(); // Add this line to finish the activity when user logs out
        }

        private void loadFragment(Fragment fragemt) {
            FragmentManager fm = getSupportFragmentManager() ;
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.campus_entry_container  ,fragemt);
            ft.commit();
            ft.addToBackStack(null);
            Log.d("TAG","Student data load ");
        }

        private void setAppTheme() {
            if (getResources().getBoolean(R.bool.isDarkTheme)) {
                setTheme(R.style.AppTheme_Dark);
            } else {
                setTheme(R.style.AppTheme_Light);
            }
        }



        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    // Your code here
                } else {
                    // Permission denied
                    // Your code here
                }
            }
        }


        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                // Get selected image URI and save to SharedPreferences
            }
        }

    }