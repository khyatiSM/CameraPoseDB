package com.poseforcamera.pose1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;
//import static com.poseforcamera.pose1.MainActivity.categoryResults;
import static com.poseforcamera.pose1.MainActivity.selectedFrontCameraFragment;

@SuppressLint("ValidFragment")
public class BackCameraFragment extends Fragment{
    private static final String TAG = "MainActivity";
    private   List<ModelImage> imageUrls= imageUrls=new ArrayList<>();;
    private HashSet<Integer> hset=new HashSet<>();
    private boolean frontcamera=false;
    private String categoryResults;
    private static final String url="http://192.168.0.102" +
          ":8079/getImagesdb.php";

    @SuppressLint("ValidFragment")
    public BackCameraFragment(String categoryResults) {
        this.categoryResults=categoryResults;
    }
    // private static final String url="http://riidl.org/fablab/json_booking";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=null;

       if(categoryResults.equals("All"))
        {
            Log.d("View","ViewCalled");
           view= inflater.inflate(R.layout.fragment_backcamera,container,false);

           initImageBitmaps(view);


           }
        else{
           view= inflater.inflate(R.layout.activity_catergory_recycler,container,false);
           initImageBitmaps(view);

           }

       return view;
    }
    private void initImageBitmaps(final View view){

        /*Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        imageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        //  mNames.add("Havasu Falls");

        imageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        //  mNames.add("Trondheim");

        imageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        //  mNames.add("Portugal");

        imageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        //  mNames.add("Rocky Mountain National Park");


        imageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //   mNames.add("Mahahual");

        imageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        // mNames.add("Frozen Lake");


        imageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        //  mNames.add("White Sands Desert");

        imageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        // mNames.add("Austrailia");

        imageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        // mNames.add("Washington");
        */
        Map<String,String> hmap=new HashMap<>();
        hmap.put("category",categoryResults);
        Log.d("CategoryKyaHai",categoryResults);

        Log.d("SelectedFragmentKyaHai",selectedFrontCameraFragment);
        JSONObject jsonObject1=new JSONObject();
        JSONObject jsonObject2=new JSONObject();
        try {
            jsonObject1.put("category",categoryResults);
            jsonObject1.put("selectedFrontFrag",selectedFrontCameraFragment);
            jsonObject2.put("Json",jsonObject1);

        } catch (JSONException e) {
            e.printStackTrace();
        }






        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,url,jsonObject2, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Log.d("Called1","Adapter is called");
                    JSONArray jsonArray =response.getJSONArray("images");
                    Log.d("JsonText",response.toString());
                    JSONObject product = null;
                    for (int i = 0; i < jsonArray.length(); i++) {



                      product = jsonArray.getJSONObject(i);
                      Log.d("posename", product.optString("Name"));
                      if (product.getString("Fullimage").isEmpty()) {
                          Log.d("thulluuuupart2", "kuch nahi hai");
                      } else {
                          Log.d("haipart2", "hai bhai hai");
                      }


                            imageUrls.add(new ModelImage(product.optInt("PoseId"),
                                    product.optString("Name"),
                                    product.optBoolean("Frontcamera"),
                                    product.optString("Category"),
                                    product.optString("Fullimage"),
                                    product.optString("ColourImage"),
                                    product.optString("Skeleton"),
                                    product.optInt("FrequencyClicks"),
                                    product.optBoolean("Favourites")

                            ));




                      }

                    if(categoryResults.equals("All")){
                        Log.d("RecyclerAllCreated","Yes");
                        initRecyclerView(view);
                    }
                    else{
                        Log.d("RecyclerCategoryCreated","Yes");
                        initRecyclerView2(view);

                    }

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                /*    JSONObject product = jsonArray.getJSONObject(0);
                    imageUrls.add(new ModelImage(product.getInt("PoseId"),
                            product.getString("Name"),
                            product.getBoolean("Frontcamera"),
                            product.getString("category"),
                            product.getString("FullImage"),
                            product.getString("ColourImage"),
                            product.getString("SkeletonImage"),
                            product.getInt("FrequencyClicks"),
                            product.getBoolean("Favourites")));*/




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Called","Adapter is called");
                Log.d("Volley Error",error.toString());
                error.printStackTrace();

            }
        })  {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                     Map<String,String> hmap=new HashMap<>();
                     hmap.put("category",categoryResults);
                     hmap.put("selectedFrontFrag",selectedFrontCameraFragment);
                     return hmap;

            }
        };



        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }
   private void initRecyclerView(View view) {



            Log.d(TAG, "initRecyclerView1: init recyclerview.");
            RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview1);
            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            CameraPosesAdapter adapter1 = new CameraPosesAdapter(getContext(), imageUrls, frontcamera);
            recyclerView1.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();


            RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerview2);
            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            CameraPosesAdapter adapter2 = new CameraPosesAdapter(getContext(), imageUrls, frontcamera);
            recyclerView2.setAdapter(adapter2);



            RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerview3);
            recyclerView3.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            CameraPosesAdapter adapter3 = new CameraPosesAdapter(getContext(), imageUrls,frontcamera);
            recyclerView3.setAdapter(adapter3);



            RecyclerView recyclerView4 = (RecyclerView) view.findViewById(R.id.recyclerview4);
            recyclerView4.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            CameraPosesAdapter adapter4 = new CameraPosesAdapter(getContext(), imageUrls, frontcamera);
            recyclerView4.setAdapter(adapter4);






    }
/*
    private void initImageBitmaps2(View view){
       /* Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        imageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        //  mNames.add("Havasu Falls");

        imageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        //  mNames.add("Trondheim");

        imageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        //  mNames.add("Portugal");

        imageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        //  mNames.add("Rocky Mountain National Park");


        imageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        //   mNames.add("Mahahual");

        imageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        // mNames.add("Frozen Lake");


        imageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        //  mNames.add("White Sands Desert");

        imageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        // mNames.add("Austrailia");

        imageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        // mNames.add("Washington");




        initRecyclerView2(view);
    }*/
    private void initRecyclerView2(View view) {


            Log.d(TAG, "initRecyclerView: init recyclerview.");
            RecyclerView recyclerView1 = null;
            recyclerView1  =(RecyclerView) view.findViewById(R.id.categorRecyclerID);
            recyclerView1.setLayoutManager(new GridLayoutManager(getContext(), 3));
            CameraPosesAdapter adapter1 = new CameraPosesAdapter(getContext(), imageUrls,frontcamera);
            recyclerView1.setAdapter(adapter1);






    }

}
