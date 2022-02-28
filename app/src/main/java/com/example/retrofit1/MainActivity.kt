package com.example.retrofit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit1.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL ="https://jsonplaceholder.typicode.com/users/1/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    private lateinit var linearLayoutManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerview.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        binding.recyclerview.layoutManager=linearLayoutManager
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyData.MyDataItem>?> {
            override fun onResponse(call: Call<List<MyData.MyDataItem>?>, response: Response<List<MyData.MyDataItem>?>) {
                val responseBody=response.body()!!

                myAdapter=MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()
                binding.recyclerview.adapter=myAdapter

               /* val myStringBuilder=StringBuilder()
                for (myData in responseBody){
                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                }
               *//* val text1=findViewById<TextView>(R.id.text1)
                text1.text=myStringBuilder*//*
                binding.text1.text=myStringBuilder*/
            }

            override fun onFailure(call: Call<List<MyData.MyDataItem>?>, t: Throwable) {
                    Log.d("MainActivity","onfailer"+t.message)
            }
        })

    }
}