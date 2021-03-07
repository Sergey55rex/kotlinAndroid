package ru.netology.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import ru.netology.Post
import ru.netology.R
import ru.netology.adapter.OnListenerPress
import ru.netology.adapter.PostAdapter
import ru.netology.databinding.ActivityMainBinding
import ru.netology.vievmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    private val newPostRequestCode = 1
    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnListenerPress {

            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onToSendListener(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
                viewModel.toSendsById(post.id)
            }

            override fun onPlay(post: Post) {
                val intent = Intent(this@MainActivity, VideoActivity::class.java)
                startActivity(intent)
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
//                binding.cancellation.isGone = false
                val intent = Intent(this@MainActivity, СhangePostActivity::class.java)
                startActivityForResult(intent, newPostRequestCode)
            }
        })





//        viewModel.edited.observe(this, { post ->
//            if (post.id == 0L) {
//                return@observe
//            }
//            with(binding.content) {
//                binding.cancellation.isGone = false
//                requestFocus()
//                setText(post.content)
//            }
//        })

//        binding.save.setOnClickListener {
//            with(binding.content) {
////                if (text.isNullOrBlank()) {
////                    Toast.makeText(
////                            this@MainActivity,
////                            context.getString(R.string.error_empty_content),
////                            Toast.LENGTH_SHORT
////                    ).show()
////                    return@setOnClickListener
////                }
//
////                viewModel.changeContent(text.toString())
//                viewModel.save()
//                binding.cancellation.isGone = true
//                setText("")
//                clearFocus()
//                AndroidUtils.hideKeyboard(this)
//            }
//        }
//
//
//        binding.cancellation.setOnClickListener{
//            with(binding.content) {
//                binding.cancellation.isGone = true
//                setText("")
//                AndroidUtils.hideKeyboard(this)
//            }
//        }







// Это добавлено
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPostActivity::class.java)
            startActivityForResult(intent, newPostRequestCode)
        }
    }



// Это добавлено
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            newPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }

                data?.getStringExtra(Intent.EXTRA_TEXT)?.let {
                    viewModel.changeContent(it)
                    viewModel.save()
                }
            }
        }
    }


}


