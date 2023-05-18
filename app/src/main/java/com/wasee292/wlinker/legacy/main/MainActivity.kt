package com.wasee292.wlinker.legacy.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wasee292.wlinker.legacy.addlink.AddLinkFragment
import com.wasee292.wlinker.legacy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupUI()
        observeEvents()
    }

    private fun setupUI() {
        binding.apply {
            fabAddLink.setOnClickListener {
                openAddLinkDialog()
            }
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = mainAdapter
            }
        }
    }

    private fun observeEvents() {
        mainViewModel.events.observe(this) { mainEvent ->
            when (mainEvent) {
                is MainEvent.LinksUpdated -> {
                    mainAdapter.submitList(mainEvent.links)
                }

                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun openAddLinkDialog() {
        AddLinkFragment.show(supportFragmentManager)
    }
}
