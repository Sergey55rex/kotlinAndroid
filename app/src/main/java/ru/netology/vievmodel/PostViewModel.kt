package ru.netology.vievmodel

import androidx.lifecycle.*
import ru.netology.Post
import ru.netology.repository.PostRepositoriMemory
import ru.netology.repository.PostRepository

private val empty = Post(
        id = 0,
        content = "",
        author = "",
        liked  = false,
        published = "",
        likes = 0,
        toSend = false,
        toSends = 0,
        viewing = false,
        viewings = 0
)

class PostViewModel: ViewModel() {
    private val repository:PostRepository = PostRepositoriMemory()
    val data = repository.getAll()

    fun likeById(id: Long) = repository.likeById(id)
    fun toSendsById(id: Long) = repository.toSendsById(id)
    fun viewingById(id: Long) = repository.viewingById(id)
}