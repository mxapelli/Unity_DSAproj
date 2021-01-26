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

    public static String getLevel(String num)
    {
        final String[] mapvector = new String[1];
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UnityMap> call = apiInterface.getMap(num);
        call.enqueue(new Callback<UnityMap>() {
            @Override
            public void onResponse(Call<UnityMap> call, Response<UnityMap> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    UnityMap map = response.body();
                    mapvector[0] = map.getVectMap();
                } else {
                    Log.d("Error", "Login failed");
                    mapvector[0] = "";
                }
            }

            @Override
            public void onFailure(Call<UnityMap> call, Throwable t) {
                call.cancel();
                mapvector[0] = "";
                Log.d("Error", "Failure");
            }
        });
        return mapvector[0];
    }
    public static String getLevelName(String num)
    {
        final String[] maplevel = new String[1];
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UnityMap> call = apiInterface.getMap(num);
        call.enqueue(new Callback<UnityMap>() {
            @Override
            public void onResponse(Call<UnityMap> call, Response<UnityMap> response) {
                Log.d("TAG", response.code() + "");
                if (response.code() == 201) {
                    UnityMap map = response.body();
                    maplevel[0] = map.getMapName();
                } else {
                    Log.d("Error", "Login failed");
                    maplevel[0] = "";
                }
            }

            @Override
            public void onFailure(Call<UnityMap> call, Throwable t) {
                call.cancel();
                maplevel[0] = "";
                Log.d("Error", "Failure");
            }
        });
        return maplevel[0];
    }



}