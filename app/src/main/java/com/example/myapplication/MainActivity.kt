package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import org.depinfo.retrofit_demo.http.RetrofitUtil
import org.depinfo.retrofit_demo.http.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Appeler un service de liste et afficher dans le TextView
        binding.btn.setOnClickListener {
            val service: Service = RetrofitUtil.get()
            val nom: String = binding.et.text.toString()
            val call: Call<String> = service.listReposString(nom)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        // HTTP 200, tout s'est bien passé
                        val resultat = response.body()
                        binding.tv.text = resultat
                    } else {
                        // Cas d'erreur HTTP 400, 404, etc.
                        binding.tv.text = "REPONSE ERREUR : " + response.code()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // Erreur d'accès réseau ou GSON
                    binding.tv.text = "PAS DE REPONSE : " + t.message
                }
            })
        }
    }
}