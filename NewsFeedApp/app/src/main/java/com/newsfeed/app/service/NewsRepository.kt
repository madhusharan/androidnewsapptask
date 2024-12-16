import androidx.lifecycle.LiveData
import com.newsfeed.app.roomdb.NewsArticle
import com.newsfeed.app.roomdb.NewsArticleDao
import com.newsfeed.app.service.NewsApiService
import org.jetbrains.annotations.NotNull
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsArticleDao: NewsArticleDao
) {

    suspend fun fetchNews(category: String, apiKey: String): List<NewsArticle> {
        val response = newsApiService.getNews(category, apiKey)
        val articles = response.articles
        newsArticleDao.insertArticles(articles) // Save fetched data to the database
        return articles
    }

    fun getAllNews(): LiveData<List<NewsArticle>> {
        return newsArticleDao.getAllArticles()
    }
}
