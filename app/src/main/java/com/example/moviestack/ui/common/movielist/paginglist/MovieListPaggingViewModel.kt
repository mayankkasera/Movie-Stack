package com.example.moviestack.ui.common.movielist.paginglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviestack.api.pojo.Result
import com.example.moviestack.api.pojo.Search
import com.example.moviestack.api.pojo.SmallItemList
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.api.repo.movieInfo.genre.GenreDataSource
import com.example.moviestack.api.repo.movieInfo.genre.GenreDataSourceFactory
import com.example.moviestack.api.repo.movieInfo.similarpaging.SimilarDataSource
import com.example.moviestack.api.repo.movieInfo.similarpaging.SimilarDataSourceFactory
import com.example.moviestack.api.repo.search.SearchRepositoryI
import com.example.moviestack.api.repo.search.searchpaging.SearchDataSource
import com.example.moviestack.api.repo.search.searchpaging.SearchDataSourceFactory
import com.example.moviestack.api.repo.smallitemlist.SmallItemRepositoryI
import com.example.moviestack.api.repo.smallitemlist.trendingmovie.MoreItemDataSource
import com.example.moviestack.api.repo.smallitemlist.trendingmovie.MoreItemDataSourceFactory
import com.example.moviestack.ui.common.movielist.MovieListState
import io.reactivex.disposables.CompositeDisposable

class MovieListPaggingViewModel() : ViewModel() {

    lateinit var moviePagedList: LiveData<PagedList<Result>>
    lateinit var state : LiveData<MovieListState>
    private val compositeDisposable = CompositeDisposable()


    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    fun getMoviesData(smallItemRepositoryI: SmallItemRepositoryI, type : SmallItemList.Type){
        var  trendingMoviesDataSourceFactory  =
            MoreItemDataSourceFactory(
                compositeDisposable,
                smallItemRepositoryI,
                type
            )
        moviePagedList = LivePagedListBuilder(trendingMoviesDataSourceFactory, config).build()
        state = Transformations.switchMap<MoreItemDataSource, MovieListState>(
                trendingMoviesDataSourceFactory.moviesLiveDataSource, MoreItemDataSource::mutableLiveData)
    }

    fun getSimilarData(id : String, movieRepositoryI: MovieRepositoryI){
        var similarDataSourceFactory = SimilarDataSourceFactory(id,compositeDisposable,movieRepositoryI)
        moviePagedList = LivePagedListBuilder(similarDataSourceFactory, config).build()
        state = Transformations.switchMap<SimilarDataSource, MovieListState>(
            similarDataSourceFactory.moviesLiveDataSource, SimilarDataSource::mutableLiveData)
    }

    fun getGenreData(data : String, discoverRepositoryI: DiscoverRepositoryI){
        var genreDataSourceFactory = GenreDataSourceFactory(data,compositeDisposable,discoverRepositoryI)
        moviePagedList = LivePagedListBuilder(genreDataSourceFactory, config).build()
        state = Transformations.switchMap<GenreDataSource, MovieListState>(
            genreDataSourceFactory.moviesLiveDataSource, GenreDataSource::mutableLiveData)
    }

    fun getSearchMovieData(data : String, searchRepositoryI: SearchRepositoryI,type : SmallItemList.Type){
        var searchDataSourceFactory = SearchDataSourceFactory(data,compositeDisposable,searchRepositoryI,type)
        moviePagedList = LivePagedListBuilder(searchDataSourceFactory, config).build()
        state = Transformations.switchMap<SearchDataSource, MovieListState>(
            searchDataSourceFactory.moviesLiveDataSource, SearchDataSource::mutableLiveData)
    }





}