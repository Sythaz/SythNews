package com.example.sythnews

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sythnews.databinding.ActivityMainBinding
import com.example.sythnews.helper.ViewModelFactory
import com.example.sythnews.note.ui.insert.NoteAddUpdateActivity
import com.example.sythnews.note.ui.main.MainViewModel
import com.example.sythnews.note.ui.main.NoteAdapter
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
        private const val NewsID = "0a924b28a2754d51848582999d7c62fd"
    }

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var nAdapter: NoteAdapter
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Berita
        getNews()

        //Note
        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                nAdapter.setListNotes(noteList)
            }
        }

        nAdapter = NoteAdapter()

        binding?.rvDaftarNote?.layoutManager = LinearLayoutManager(this)
        binding?.rvDaftarNote?.setHasFixedSize(true)
        binding?.rvDaftarNote?.adapter = nAdapter

        binding?.tambah?.setOnClickListener { view ->
            if (view.id == R.id.tambah) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    //Berita
    private fun getNews() {
        val client = ApiConfig.getApiService().getNews(NewsID)
        client.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    val responseNews = response.body()
                    val dataNews = responseNews?.articles
                    val newsAdapter = NewsAdapter(dataNews)
                    newsAdapter.setNews(articles)

                    binding?.rvDaftarBerita?.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL ,false)
                        setHasFixedSize(true)
                        newsAdapter.notifyDataSetChanged()
                        adapter = newsAdapter
                    }
                }
                else {Log.e(TAG, "onFailure: ${response.message()}")}
            }
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}