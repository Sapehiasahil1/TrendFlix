package com.example.trendflix.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trendflix.data.remote.ApiService
import com.example.trendflix.model.Film
import com.example.trendflix.util.FilmType
import retrofit2.HttpException
import java.io.IOException

class PopularFilmSource(private val api: ApiService, private val filmType: FilmType) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val popularMovies =
                if (filmType == FilmType.MOVIE) api.getPopularMovies(page = nextPage)
            else api.getPopularTvShows(page = nextPage)
            LoadResult.Page(
                data = popularMovies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularMovies.results.isEmpty()) null else popularMovies.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}
