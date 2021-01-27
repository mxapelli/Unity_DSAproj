package edu.upc.androidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.unity3d.player.UnityPlayerActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UnityAPI {
    static String mapvector;
    static String maplevel;
    public static String getLevel(String num)
    {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UnityMap> call = apiInterface.getMap(num);
        call.enqueue(new Callback<UnityMap>() {
            @Override
            public void onResponse(Call<UnityMap> call, Response<UnityMap> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    UnityMap map = response.body();
                    mapvector = map.getVectMap();
                } else {
                    Log.d("Error", "Login failed");
                    mapvector = "";
                }
            }
            @Override
            public void onFailure(Call<UnityMap> call, Throwable t) {
                call.cancel();
                Log.d("Error", "Failure");
                mapvector = "";
            }
        });
        return mapvector;
    }
    public static String getLevelName(String num)
    {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UnityMap> call = apiInterface.getMap(num);
        call.enqueue(new Callback<UnityMap>() {
            @Override
            public void onResponse(Call<UnityMap> call, Response<UnityMap> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    UnityMap map = response.body();
                    maplevel = map.getMapName();
                } else {
                    Log.d("Error", "Login failed");
                    maplevel = "";
                }
            }

            @Override
            public void onFailure(Call<UnityMap> call, Throwable t) {
                call.cancel();
                maplevel = "";
                Log.d("Error", "Failure");
            }
        });
        return maplevel;
    }



}