package com.example.pbs.Activity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.pbs.CurrentLocation.Map_fragment;
import com.example.pbs.Customers.Add_customer;
import com.example.pbs.Customers.Customer;
import com.example.pbs.Fragment.CreateInvoice;
import com.example.pbs.Fragment.CreateReturnInvoice;
import com.example.pbs.Fragment.Good;
import com.example.pbs.Fragment.Income;
import com.example.pbs.Fragment.Notification;
import com.example.pbs.Fragment.Products;
import com.example.pbs.Fragment.Profiles;
import com.example.pbs.Fragment.Report;
import com.example.pbs.Fragment.ReturnInvoice;
import com.example.pbs.Fragment.Sales;
import com.example.pbs.Fragment.Setting;
import com.example.pbs.Fragment.salesInvoice;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.Orders.AddOrder;
import com.example.pbs.Orders.Order;
import com.example.pbs.R;
import com.example.pbs.Tasks.TaskNumber;
import com.example.pbs.Tasks.Tasks;
import com.example.pbs.adapter.Exbandable_Invoice;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  implements
       CallFragment, NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        init_actionbar();
        Bundle b = getIntent().getExtras();
        if (b != null) {

            String someData = b.getString("FCM");
            Toast.makeText(this,someData+"background",Toast.LENGTH_LONG).show();

            if(someData!=null) {
                Notification notification = new Notification();
                call_fragment_method(notification);
            }
        }
//
//         String FCM_message=getIntent().getStringExtra("FCM");
//        Toast.makeText(this,FCM_message,Toast.LENGTH_LONG).show();
//          if(FCM_message!=null)
//          {
//             if(FCM_message.equals("message"))
//             {
//
//                 Notification notification=new Notification();
//                 call_fragment_method(notification);
//             }
//          }
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Map_fragment()).commit();
        }


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String FCM_message = getIntent().getStringExtra("FCM");
        Toast.makeText(this, FCM_message, Toast.LENGTH_LONG).show();


    }
    @Override
    protected void onResume() {
        super.onResume();
      //  init();
    }

    public void init_actionbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.menu_home));
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void init() {
        Tasks tasks=new Tasks();
        tasks.setCallFragments(this);
        TaskNumber taskNumber=new TaskNumber();
        taskNumber.setCallFragments(this);
        salesInvoice SalesInvoice=new salesInvoice();
        SalesInvoice.setCallFragments(this);
        ReturnInvoice returnInvoice=new ReturnInvoice();
        returnInvoice.setCallFragments(this);
        Sales sales=new Sales();
        sales.setCallFragments(this);
        Setting setting=new Setting();
        setting.setCallFragments(this);
        Exbandable_Invoice.setCallFragments(this);
        Profiles profiles=new Profiles();
        profiles.setCallFragments(this);
        Add_customer add_customer=new Add_customer();
        add_customer.setCallFragments(this);
        CreateInvoice createInvoice=new CreateInvoice();
        createInvoice.setCallFragments(this);
        CreateReturnInvoice create_r_invoice=new CreateReturnInvoice();
        create_r_invoice.setCallFragments(this);
        AddOrder addOrder=new AddOrder();
        addOrder.setCallFragments(this);

    }

    public Toolbar  getToolbar()
   {
       Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       return toolbar;
   }
    @Override
    public void onBackPressed() {
       int BackStackCount=getSupportFragmentManager().getBackStackEntryCount();
         if (BackStackCount >0) {
        getSupportFragmentManager().popBackStack();
        }else if(BackStackCount==0)
        {
          finish();
        }
        else
            super.onBackPressed();

    }
    @Override
    public void call_fragment_method(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment).commit();


    }


        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();


            if (id == R.id.nav_task) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Tasks()).addToBackStack(null).commit();
            }else if (id == R.id.nav_goods) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Good()).addToBackStack(null).commit();

            } else if (id == R.id.nav_income) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Income()).addToBackStack(null).commit();
            } else if (id == R.id.nav_customer) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Customer()).addToBackStack(null).commit();

            } else if (id == R.id.nav_product) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Products()).addToBackStack(null).commit();

            } else if (id == R.id.nav_sales) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Sales()).addToBackStack(null).commit();

            } else if (id == R.id.nav_invoice) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new salesInvoice()).addToBackStack(null).commit();

            } else if (id == R.id.nav_report) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Report()).addToBackStack(null).commit();

            } else if (id == R.id.nav_setting) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new Setting()).addToBackStack(null).commit();

            } else if (id == R.id.nav_order) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new Order()).addToBackStack(null).commit();

            }


            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


}


