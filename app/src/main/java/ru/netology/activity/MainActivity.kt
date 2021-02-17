package ru.netology.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.Post
import ru.netology.adapter.OnListenerPress
import ru.netology.databinding.ActivityMainBinding
import ru.netology.vievmodel.PostViewModel
import ru.netology.adapter.PostAdapter

import ru.netology.utils.Calculator.calculator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnListenerPress {
            override fun onToSendListener(post: Post) {
                viewModel.toSendsById(post.id)
            }

            override fun onLikeListener(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onVievingListener(post: Post) {
                viewModel.viewingById(post.id)
            }
        })


        binding.list.adapter = adapter
        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)

        })
    }


}


