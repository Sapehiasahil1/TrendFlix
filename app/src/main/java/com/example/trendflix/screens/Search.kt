package com.example.trendflix.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.trendflix.model.Film
import com.example.trendflix.R
import com.example.trendflix.screens.destinations.MovieDetailsDestination
import com.example.trendflix.sharedComposables.BackButton
import com.example.trendflix.sharedComposables.SearchBar
import com.example.trendflix.sharedComposables.SearchResultItem
import com.example.trendflix.ui.theme.AppPrimaryColor
import com.example.trendflix.ui.theme.AppOnPrimaryColor
import com.example.trendflix.util.Constants.BASE_POSTER_IMAGE_URL
import com.example.trendflix.util.FilmType
import com.example.trendflix.viewmodel.HomeViewModel
import com.example.trendflix.viewmodel.SearchViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val searchResult = searchViewModel.multiSearchState.value.collectAsLazyPagingItems()
    val includeAdult =
        searchViewModel.includeAdult.value.collectAsState(initial = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth(fraction = 0.60F)
        ) {
            val focusManager = LocalFocusManager.current
            BackButton {
                focusManager.clearFocus()
                navigator.navigateUp()
            }

            Text(
                text = "Search",
                modifier = Modifier.padding(start = 50.dp),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = AppOnPrimaryColor
            )
        }

        SearchBar(
            autoFocus = true,
            onSearch = {
                searchViewModel.searchRemoteMovie(includeAdult.value ?: true)
            })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            when (searchResult.loadState.refresh) {
                is LoadState.NotLoading -> {
                    items(searchResult.itemCount) { film ->
                        val focus = LocalFocusManager.current
                        SearchResultItem(
                            title = searchResult[film]!!.title,
                            mediaType = searchResult[film]?.mediaType,
                            posterImage = "$BASE_POSTER_IMAGE_URL/${searchResult[film]?.posterPath}",
                            genres = homeViewModel.filmGenres.filter { genre ->
                                return@filter if (searchResult[film]?.genreIds.isNullOrEmpty()) false else
                                    searchResult[film]?.genreIds?.contains(genre.id) == true
                            },
                            rating = (searchResult[film]?.voteAverage ?: 0) as Double,
                            releaseYear = searchResult[film]?.releaseDate,
                            onClick = {
                                val navFilm = Film(
                                    adult = searchResult[film]?.adult ?: false,
                                    backdropPath = searchResult[film]?.backdropPath,
                                    posterPath = searchResult[film]?.posterPath,
                                    genreIds = searchResult[film]?.genreIds,
                                    genres = searchResult[film]?.genres,
                                    mediaType = searchResult[film]?.mediaType,
                                    id = searchResult[film]?.id ?: 0,
                                    imdbId = searchResult[film]?.imdbId,
                                    originalLanguage = searchResult[film]?.originalLanguage ?: "",
                                    overview = searchResult[film]?.overview ?: "",
                                    popularity = searchResult[film]?.popularity ?: 0F.toDouble(),
                                    releaseDate = searchResult[film]?.releaseDate ?: "",
                                    runtime = searchResult[film]?.runtime,
                                    title = searchResult[film]?.title ?: "",
                                    video = searchResult[film]?.video ?: false,
                                    voteAverage = searchResult[film]?.voteAverage ?: 0F.toDouble(),
                                    voteCount = searchResult[film]?.voteCount ?: 0
                                )
                                focus.clearFocus()
                                navigator.navigate(
                                    direction = MovieDetailsDestination(
                                        navFilm,
                                        if (navFilm.mediaType == "tv") FilmType.TVSHOW else FilmType.MOVIE
                                    )
                                ) {
                                    launchSingleTop = true
                                }
                            })
                    }
                    if (searchResult.itemCount == 0) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.no_match_found),
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }

                is LoadState.Loading -> item {
                    if (searchViewModel.searchParam.value.isNotEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                else -> item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_match_found),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}