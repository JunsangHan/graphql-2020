package com.example.rocketreserver

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.coroutines.toDeferred
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val response = try {
                apolloClient(this@MainActivity).query(LoginUserQuery()).toDeferred().await()
            } catch (e: Exception) {
                null
            }

            response?.let {
                val viewer = it.data?.viewer
                Log.d("GraphQL", "GraphQL, viewer $viewer")
            }
        }


    }
}
