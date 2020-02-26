import androidx.paging.PagedList
import com.escorp.movieworld.data.models.Actor
import com.escorp.movieworld.data.models.Movie
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.ArgumentMatchers

fun mockMovie() =
    Movie(1, 1, "", "", "", "", "", emptyList(), "", "", 0, "", 0.0, 0, false, 0.0, "", emptyList())

fun mockActor() = Actor(1, 1, "", "", 0.0, emptyList())

fun mockMovieList() = ArrayList<Movie>().apply {
    add(mockMovie())
    add(mockMovie())
    add(mockMovie())
}

fun mockActorList() = ArrayList<Actor>().apply {
    add(mockActor())
    add(mockActor())
    add(mockActor())
}

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = mock<PagedList<T>>()
    whenever(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    whenever(pagedList.count()).thenReturn(list.size)
    return pagedList
}