package ru.netology.adapter

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.PopupMenu
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
            if (post.video == true){
                videoButton.visibility = VISIBLE
                imagePlay.visibility = VISIBLE
            }else{
                videoButton.visibility = GONE
                imagePlay.visibility = GONE
            }

//            imageLike.setImageResource(
//                if (post.liked) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
//            )

            imageLike.isChecked = post.liked
            imageLike.text = "${post.likes}"


            imageLike.setOnClickListener {
                onListenerPress.onLikeListener(post)
            }

            imageToSend.isChecked = post.toSend
            imageToSend.text = "${calculator(post.toSends)}"

            imageToSend.setOnClickListener {
                onListenerPress.onToSendListener(post)
            }

            imageViewing.isChecked = post.viewing
            imageViewing.text = "${calculator(post.viewings)}"

            imageViewing.setOnClickListener {
                onListenerPress.onVievingListener(post)
            }
//видео
            videoButton.setOnClickListener {
                onListenerPress.onPlay(post)
            }





  //          textLike.text = post.likes.toString()
//            textToSend.text = calculator(post.toSends)
//             textViewing.text = calculator(post.viewings)



            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onListenerPress.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onListenerPress.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }

        }
    }
}
