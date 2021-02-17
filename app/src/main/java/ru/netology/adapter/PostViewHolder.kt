package ru.netology.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.Post
import ru.netology.R
import ru.netology.databinding.CardPostBinding
import ru.netology.utils.Calculator.calculator

class PostViewHolder(
    private val binding: CardPostBinding,
     private val onListenerPress: OnListenerPress,
) :RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post){
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            imageLike.setImageResource(
                if (post.liked) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
            )
            imageLike.setOnClickListener {
                onListenerPress.onLikeListener(post)
            }

            imageToSend.setOnClickListener {
                onListenerPress.onToSendListener(post)
            }
            imageViewing.setOnClickListener {
                onListenerPress.onVievingListener(post)
            }

            textLike.text = post.likes.toString()
            textToSend.text = calculator(post.toSends)
            textViewing.text = calculator(post.viewings)
        }
    }
}