package com.app.oxostayadmin.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.oxostayadmin.model.ApprovedModel;
import com.app.oxostayadmin.utils.FullScreenImage;
import com.app.oxostayadmin.utils.MyFirebaseInstanceIDService;
import com.app.oxostayadmin.R;
import com.app.oxostayadmin.model.User;
import com.app.oxostayadmin.notif.ApiClient;
import com.app.oxostayadmin.notif.ApiService;
import com.app.oxostayadmin.notif.RequestNotification;
import com.app.oxostayadmin.notif.SendNotificationModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class ApprovingActivity extends AppCompatActivity {

//    private ArrayList<ApprovedModel> listdata;
    String name,address,phno,gst,pan,aadhar,key,act;
    TextView tv_name,tv_address,tv_no;
    ImageView iv_gst,iv_pancard,iv_aadhar;
    Button btn_yes,btn_no;
    FirebaseAuth fAuth;
    ApiService apiService;
    FirebaseDatabase database;
    DatabaseReference ref,ref_old;
    boolean isImageFitToScreen;
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approving);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        Intent intent = getIntent();
        act = intent.getStringExtra("act");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        phno = intent.getStringExtra("no");
        gst = intent.getStringExtra("gst");
        pan = intent.getStringExtra("pan");
        aadhar = intent.getStringExtra("aadhar");
        key = intent.getStringExtra("key");
        Log.e("checkKeyFinal",">>" + key);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_no = (TextView) findViewById(R.id.tv_no);
        iv_gst = (ImageView) findViewById(R.id.iv_gst);
        iv_pancard = (ImageView) findViewById(R.id.iv_pancard);
        iv_aadhar = (ImageView) findViewById(R.id.iv_aadhar);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_no = (Button) findViewById(R.id.btn_no);

        if (act.equals("approved")){
            btn_yes.setVisibility(View.GONE);
            btn_no.setVisibility(View.GONE);
        }else{
            btn_yes.setVisibility(View.VISIBLE);
            btn_no.setVisibility(View.VISIBLE);
        }

//        MyFirebaseInstanceIDService myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        Log.e("CheckTokenFire",">>" + MyFirebaseInstanceIDService.getToken(this));

        tv_name.setText(name);
        tv_address.setText(address);
        tv_no.setText(phno);

        SendNotificationModel sendNotificationModel = new SendNotificationModel("New Hotel admin", "New Hotel admin");
        RequestNotification requestNotificaton = new RequestNotification();
        requestNotificaton.setSendNotificationModel(sendNotificationModel);
        //token is id , whom you want to send notification ,
        requestNotificaton.setToken("ca9Nbi6OTwKfP0Cy2LR13Y:APA91bFU6yk7JeWKD4yFTN3t0eCqO7wVkgSUbYqes11I4bucovDE9lAnyCRw5s9uEaVCWtbQr5v22OLcjf7PWzBWqpf3gcHfX3Bq8DPdKpRXXO2UeBMvOuzjLOZ6SuXNchCtsO31InFF");

        apiService =  ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<ResponseBody> responseBodyCall = apiService.sendChatNotification(requestNotificaton);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d("kkkk",""+call);
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                Log.d("kkkk",""+call);


            }
        });


//        String requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(getApplicationContext()).load(gst).into(iv_gst);
        Glide.with(getApplicationContext()).load(pan).into(iv_pancard);
        Glide.with(getApplicationContext()).load(aadhar).into(iv_aadhar);
        iv_pancard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApprovingActivity.this, FullScreenImage.class);

                iv_aadhar.buildDrawingCache();
                Bitmap image= iv_aadhar.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

        iv_gst.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApprovingActivity.this, FullScreenImage.class);

                iv_aadhar.buildDrawingCache();
                Bitmap image= iv_aadhar.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

        iv_aadhar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApprovingActivity.this, FullScreenImage.class);

                iv_aadhar.buildDrawingCache();
                Bitmap image= iv_aadhar.getDrawingCache();

                Bundle extras = new Bundle();
                extras.putParcelable("imagebitmap", image);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });


        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApprovedModel user = new ApprovedModel();
                user.setFullName(name);
                user.setAddress(address);
                user.setPhNumber(phno);
                user.setGstCert(gst);
                user.setAadhaarCard(aadhar);
                user.setPanCard(pan);
                Log.e("checkSignupData",">>" + user);

                ref.child("oxostaypartner").child("hotelsapproved").child(key).setValue(user);
                ref_old = database.getReference();
                ref_old.child("oxostaypartner").child("hotelstobeapproved").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ApprovingActivity.this, "Successfully Approved", Toast.LENGTH_SHORT).show();
                    }
                });
                createNewUser();
            }
        });
    }

    private static String getRandomString(final int sizeOfPasswordString){
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfPasswordString);

        for(int i=0;i<sizeOfPasswordString;++i){
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));

        }
        return sb.toString();
    }

    public void createNewUser(){

        final String username;
        final String[] new_name;
        String password = null;
        new_name = phno.split("");
        String nn = new_name[0];
        String nnn = new_name[1];
        String nnnn = new_name[2];
        String nnnnn = new_name[3];
        username = name + nn + nnn + nnnn + nnnnn + "@oxostay.in";
//        username = phno;
        password = getRandomString(6);

        final String finalPassword = password;
        fAuth = FirebaseAuth.getInstance();
        fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("oxostaypartner").child("users");
                    User user = new User();
                    user.setName(name);
                    user.setUsername(username);
                    user.setPassword(finalPassword);
                    Log.e("checkSignupData",">>" + user);

                    user_ref.push().setValue(user);

                }else{
                    Toast.makeText(ApprovingActivity.this, "Error while creating a user", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}