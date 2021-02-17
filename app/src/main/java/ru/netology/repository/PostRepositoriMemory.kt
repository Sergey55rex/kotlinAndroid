package ru.netology.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.Post

class PostRepositoriMemory: PostRepository {
//    private var nextId = 1L
    private var posts = listOf(
            Post(
                    id = 1,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                    published = "21 мая в 18:36",
                    liked = false,
                    likes = 999,
                    toSend = false,
                    toSends = 999,
                    viewing = false,
                    viewings = 0
            ),
            Post(
                    id = 2,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
                    published = "18 сентября в 10:12",
                    liked = false,
                    likes = 0,
                    toSend = false,
                    toSends = 999999,
                    viewing = false,
                    viewings = 999999
            ),
            Post(
                    id = 3,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
                    published = "19 сентября в 10:24",
                    liked = false,
                    likes = 0,
                    toSend = false,
                    toSends = 0,
                    viewing = false,
                    viewings = 0
            ),
            Post(
                    id = 4,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
                    published = "19 сентября в 14:12",
                    liked = false,
                    likes = 0,
                    toSend = false,
                    toSends = 0,
                    viewing = false,
                    viewings = 0
            ),

    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else
                it.copy(liked = !it.liked, likes = if (it.liked) it.likes-1 else it.likes+1)
        }
        data.value = posts
    }

    override fun toSendsById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(toSends = if (it.toSend) it.toSends+1 else it.toSends+1)
        }
        data.value = posts
    }

    override fun viewingById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(viewings = if (it.viewing) it.viewings+1 else it.viewings+1)
        }
        data.value = posts
    }
}
