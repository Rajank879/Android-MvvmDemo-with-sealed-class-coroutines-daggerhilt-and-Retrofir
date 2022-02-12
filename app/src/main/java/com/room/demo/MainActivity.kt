package com.room.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.room.demo.Util.ApiState
import com.room.demo.adapter.PostAdapter
import com.room.demo.databinding.ActivityMainBinding
import com.room.demo.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        mainViewModel.getPost()

        lifecycleScope.launchWhenStarted {
            mainViewModel.stateFlow.collect {
                when(it){
                    is ApiState.Loading->{
                        binding.recycleView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Success->{
                        binding.recycleView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        postAdapter.setData(it.data)
                    }
                    is ApiState.Failure->{
                        binding.recycleView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Empty->{}
                }

            }
        }

    }

    private fun initRecycleView() {
        postAdapter = PostAdapter(ArrayList())
        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}