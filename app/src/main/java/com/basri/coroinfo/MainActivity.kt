package com.basri.coroinfo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bas.botinfo.data.entities.cor.Global
import com.bas.botinfo.data.entities.cor.ResponseData
import com.basri.coroinfo.api.ApiHelper
import com.basri.coroinfo.api.MainRepository
import com.basri.coroinfo.database.AppDatabase
import com.basri.coroinfo.entity.DataAdapter
import com.basri.coroinfo.entity.DataCountry
import com.basri.coroinfo.ui.Adapter
import com.basri.newsapp.network.ApiRetrofit
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    var listInfo: ArrayList<DataAdapter> = ArrayList()
    var result = ""
    var appDatabase: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

        }, 100000)

        listInfo.add(
            DataAdapter(
                "Welcome, \n " +
                        " - Want to know Cases in Every country, type CASES {code country} \n " +
                        "- Want to know Deaths in Every country, type DEATHS {code country} \n " +
                        "- Want to know Total Deaths, type DEATHS TOTAL \n " +
                        "- Want to know Total Cases, type CASES TOTAL \n "

                , false
            )
        )

        rv_info.setHasFixedSize(true)
        rv_info.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(listInfo)
        rv_info.adapter = adapter
        adapter.notifyDataSetChanged()
        appDatabase = AppDatabase.createDatabase(this)

        GlobalScope.launch(Dispatchers.Main) {

            val data = ApiRetrofit.apiService.getData()

            data.enqueue(object : retrofit2.Callback<ResponseData> {
                override fun onFailure(call: retrofit2.Call<ResponseData>, t: Throwable) {

                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    response.body()?.let { inserData(it) }
                }
            })

        }

        getData()
    }

    fun getData() {

        var dataCountry: DataCountry? = null
        var dataglobal: Global? = null

        btn_ask.setOnClickListener {
            var getData = edt_info.text.toString()

            var filter = ""


            getData.let {
                filter = it.substring(it.length - 5)
            }
            listInfo.add(DataAdapter(getData, true))
            adapter.notifyDataSetChanged()

            if (filter.equals("TOTAL")) {
                GlobalScope.launch {

                    dataglobal = appDatabase?.characterDao()
                        ?.getTotal()
                    if (edt_info.text.toString().contains("CASES TOTAL")) {

                        result = "Total Active Cases ${dataglobal?.totalConfirmed}"
                    } else if (edt_info.text.toString().contains("DEATHS TOTAL")) {
                        result = "Total Deaths ${dataglobal?.totalDeaths}"
                    } else {

                        result = "No data found"
                    }

                    if (dataglobal == null) {
                        result = "No data found"
                    }
                }

            } else {

                GlobalScope.launch {
                    if (edt_info.text.toString().length > 3) {

                        getData.let {

                            dataCountry = appDatabase?.characterDao()
                                ?.getDeaths(
                                    it
                                        .substring(edt_info.text.toString().length - 2)
                                )
                        }

                    }

                    if (dataCountry == null) {
                        result = "No data found"
                    }

                    if (edt_info.text.toString().contains("CASES")) {

                        result =
                            "${dataCountry?.countryCode} Active Cases ${dataCountry?.totalConfirmed}"

                    } else if (edt_info.text.toString().contains("DEATHS")) {
                        result = "${dataCountry?.countryCode} Deaths ${dataCountry?.totalDeaths}"

                    } else {
                        result = " No Data Found"
                    }

                }


            }


            listInfo.add(DataAdapter(result, false))
            adapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "data $result", Toast.LENGTH_SHORT).show()

            edt_info.setText("")

        }


    }

    fun inserData(data: ResponseData) {
        GlobalScope.launch(Dispatchers.IO) {
            var listData: ArrayList<DataCountry> = ArrayList()

            var dataCorona = data.Countries

            dataCorona.forEach {
                var dataLooping = DataCountry(
                    it.countryCode,
                    it.country,
                    it.countryCode,
                    it.date,
                    it.newConfirmed,
                    it.newDeaths,
                    it.newRecovered,
                    it.slug,
                    it.totalConfirmed,
                    it.totalDeaths,
                    it.totalRecovered
                )
                listData.add(dataLooping)


            }
            appDatabase?.characterDao()?.insertGlobal(data.Global)
            appDatabase?.characterDao()?.insertAll(listData)

        }
    }


}