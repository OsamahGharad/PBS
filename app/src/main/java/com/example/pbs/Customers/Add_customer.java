package com.example.pbs.Customers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.LoginActivity.TokenManager;
import com.example.pbs.R;
import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.Responses.CustomerResponse;
import com.example.pbs.Request.RetrofitBuilder;
import com.example.pbs.Request.ServiceGenerator;
import com.example.pbs.utile.HostURL;
import com.example.pbs.utile.JSONParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Add_customer extends Fragment {
   // OasQ_add
    Customer_Entity customer_entity;
    TokenManager tokenManager;
    public static final String TAG="Add_customer";
    Toolbar toolbar;
    static CallFragment callFragment;
    BackOfficeApi backOfficeApi;
    // Progress Dialog
    private ProgressDialog pDialog;
    EditText Fname,Lname,cus_Address,cus_phone,cus_email,cust_type,max_credit;
    Button edit_Customer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customer_entity=new Customer_Entity();
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", MODE_PRIVATE));
        backOfficeApi= RetrofitBuilder.createServiceWithAuth(BackOfficeApi.class,tokenManager);
    }

    public void setCallFragments(CallFragment callFragment) {
        this.callFragment = callFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_addcustomer, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_customer));
        toolbar.setNavigationIcon(R.drawable.arrow_navigation);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        Fname        = (EditText) view.findViewById(R.id.cus_FirstName);
        Lname        = (EditText) view.findViewById(R.id.cus_LastName);
        cus_Address  = (EditText) view.findViewById(R.id.address);
        cus_phone    = (EditText) view.findViewById(R.id.PhoneNumber);
        cus_email    = (EditText) view.findViewById(R.id.Cus_email);
        max_credit   = (EditText) view.findViewById(R.id.Max_credit);
        cust_type    = (EditText) view.findViewById(R.id.cust_Type);

        Log.e(TAG,  customer_entity.getCust_first_name()+"successful connection");
        //add customer into website
        // Create button
        Button btnCreateCustomer = (Button) view.findViewById(R.id.save_send);
        // button click event
        btnCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating new customer in background thread
                customer_entity.setCust_first_name(Fname.getText().toString()); ;
                customer_entity.setCust_last_name(Lname.getText().toString()); ;
                customer_entity.setArea(cus_Address.getText().toString());
                customer_entity.setPhone_number(cus_phone.getText().toString());
                customer_entity.setCust_category(cust_type.getText().toString());
                customer_entity.setMaximum_credit(max_credit.getText().toString());
                customer_entity.setEmail_address(cus_email.getText().toString());
                CreateNewCustomer();
//                new CreateNewCustomer().execute();
            }
        });

        //edit customer
        edit_Customer = (Button) view.findViewById(R.id.editCus);
        edit_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callFragment.call_fragment_method(new EditCustomer());
            }
        });



        return view;
    }





    private void CreateNewCustomer() {
        Toast.makeText(getActivity(),"Ongoing to create add_customer",Toast.LENGTH_LONG).show();

        if(customer_entity!=null){
            Toast.makeText(getActivity(),customer_entity.getCust_first_name(),Toast.LENGTH_LONG).show();
        }

        Call<CustomerResponse > add_cus=backOfficeApi.add_cus(customer_entity);
        add_cus.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse > call, Response<CustomerResponse > response) {
                if (response.isSuccessful()) {
                    Log.e(TAG,  response.body()+"successful connection");

                }
                else
                {

                    Log.e(TAG,  response.code()+"Not_successful connection");
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse > call, Throwable t) {
                Log.e(TAG,  t.getMessage()+"Not_successful connection");
            }
        });

    }




}
