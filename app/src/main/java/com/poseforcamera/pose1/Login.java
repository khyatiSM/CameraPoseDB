package com.poseforcamera.pose1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener  {


    private SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    public static String Name;
    public static String Email;
    public static String  profile_url;
    private TextView error_msg,skip;

    private static final int REQUEST_CODE=9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        error_msg=(TextView)findViewById(R.id.errorID);
        signInButton=(SignInButton)findViewById(R.id.signinID);
        skip=(TextView)findViewById(R.id.skipID);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });

        GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this).addApi(Auth.GOOGLE_SIGN_IN_API,googleSignInOptions).build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        signInButton.setOnClickListener(this);
        finish();

    }

    @Override
    public void onClick(View v) {
        signIn();
    }
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE);
//        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(intent,REQUEST_CODE);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
//        if(resultCode==REQUEST_CODE){
//            GoogleSignInResult results=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleResults(results);
//            Log.d("inactresult","innnn");
//        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("success","sss");
            updateUI(account);
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("failedddd", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);

        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account!=null){
            Name=account.getDisplayName();
            Email=account.getEmail();
            //profile_url=account.getPhotoUrl().toString();
            error_msg.setVisibility(View.INVISIBLE);
            Log.d("sucessss","ssss");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

//    private void handleResults(GoogleSignInResult results){
//        if(results.isSuccess()){
//            GoogleSignInAccount account=results.getSignInAccount();
//            Name=account.getDisplayName();
//            Email=account.getEmail();
//            profile_url=account.getPhotoUrl().toString();
//            error_msg.setVisibility(View.INVISIBLE);
//            Log.d("sucessss","ssss");
//            Intent intent=new Intent(this,MainActivity.class);
//            startActivity(intent);
//            }
//            else{
//                error_msg.setVisibility(View.VISIBLE);
//                Log.d("failedddd","faileddd");
//              }
//
//    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
