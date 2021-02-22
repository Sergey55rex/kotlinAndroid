package ru.netology.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import ru.netology.Post
import ru.netology.R
import ru.netology.adapter.OnListenerPress
import ru.netology.databinding.ActivityMainBinding
import ru.netology.vievmodel.PostViewModel
import ru.netology.adapter.PostAdapter
import ru.netology.utils.AndroidUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnListenerPress {

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onToSendListener(post: Post) {
                viewModel.toSendsById(post.id)
            }

            override fun onLikeListener(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onVievingListener(post: Post) {
                viewModel.viewingById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

        })

        binding.cancellation.isGone = true

        binding.list.adapter = adapter
        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })

        viewModel.edited.observe(this, { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                binding.cancellation.isGone = false
                requestFocus()
                setText(post.content)
            }
        })

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                            this@MainActivity,
                            context.getString(R.string.error_empty_content),
                            Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()
                binding.cancellation.isGone = true
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }


        binding.cancellation.setOnClickListener{
            with(binding.content) {
                binding.cancellation.isGone = true
                setText("")
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}


