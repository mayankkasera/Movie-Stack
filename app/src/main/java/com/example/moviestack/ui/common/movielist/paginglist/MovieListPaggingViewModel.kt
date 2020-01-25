package com.example.moviestack.ui.common.movielist.paginglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviestack.pojo.Result
import com.example.moviestack.pojo.SmallItemList
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.discover.genre.GenreDataSource
import com.example.moviestack.api.repo.discover.genre.GenreDataSourceFactory
import com.example.moviestack.api.repo.search.SearchRepositoryI
import com.example.moviestack.api.repo.search.paging.SearchDataSource
import com.example.moviestack.api.repo.search.paging.SearchDataSourceFactory
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.movie.paging.MovieDataSource
import com.example.moviestack.api.repo.movie.paging.MovieDataSourceFactory
import com.example.moviestack.api.repo.movie.similarpaging.SimilarDataSource
import com.example.moviestack.api.repo.movie.similarpaging.SimilarDataSourceFactory
import com.example.moviestack.api.repo.trending.TrendingRepositoryI
import com.example.moviestack.api.repo.trending.paging.TrendingItemDataSource
import com.example.moviestack.api.repo.trending.paging.TrendingItemDataSourceFactory
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.api.repo.tvshow.paging.TvShowDataSource
import com.example.moviestack.api.repo.tvshow.paging.TvShowDataSourceFactory
import com.example.moviestack.api.repo.tvshow.similarpaging.TvShowSimilarDataSource
import com.example.moviestack.api.repo.tvshow.similarpaging.TvShowSimilarDataSourceFactory
import com.example.moviestack.ui.common.movielist.MovieListState
import com.example.moviestack.ui.moviedetail.DetailData
import io.reactivex.disposables.CompositeDisposable

class MovieListPaggingViewModel() : ViewModel() {

    lateinit var moviePagedList: LiveData<PagedList<Result>>
    lateinit var state : LiveData<MovieListState>
    private val compositeDisposable = CompositeDisposable()


    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()


    fun getTrendingData(trendingRepositoryI: TrendingRepositoryI, type : SmallItemList.Type){
        var  trendingItemDataSourceFactory  =
            TrendingItemDataSourceFactory(
                compositeDisposable,
                trendingRepositoryI,
                type
            )
        moviePagedList = LivePagedListBuilder(trendingItemDataSourceFactory, config).build()
        state = Transformations.switchMap<TrendingItemDataSource, MovieListState>(
            trendingItemDataSourceFactory.moviesLiveDataSource, TrendingItemDataSource::mutableLiveData)
    }

    fun getMoviesData(movieItemRepositoryI: MovieItemRepositoryI, type : SmallItemList.Type){
        var  trendingMoviesDataSourceFactory  =
            MovieDataSourceFactory(
                compositeDisposable,
                movieItemRepositoryI,
                type
            )
        moviePagedList = LivePagedListBuilder(trendingMoviesDataSourceFactory, config).build()
        state = Transformations.switchMap<MovieDataSource, MovieListState>(
                trendingMoviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::mutableLiveData)
    }

    fun getTvShowData(tvShowRepositoryI: TvShowRepositoryI, type : SmallItemList.Type){
        var  tvShowDataSourceFactory  =
            TvShowDataSourceFactory(
                compositeDisposable,
                tvShowRepositoryI,
                type
            )
        moviePagedList = LivePagedListBuilder(tvShowDataSourceFactory, config).build()
        state = Transformations.switchMap<TvShowDataSource, MovieListState>(
            tvShowDataSourceFactory.moviesLiveDataSource, TvShowDataSource::mutableLiveData)
    }

    fun getSimilarData(id : String, movieItemRepositoryI: MovieItemRepositoryI){
        var similarDataSourceFactory = SimilarDataSourceFactory(id,compositeDisposable,movieItemRepositoryI)
        moviePagedList = LivePagedListBuilder(similarDataSourceFactory, config).build()
        state = Transformations.switchMap<SimilarDataSource, MovieListState>(
            similarDataSourceFactory.moviesLiveDataSource, SimilarDataSource::mutableLiveData)
    }

    fun getSimilarData(id : String, tvShowRepositoryI: TvShowRepositoryI){
        var tvShowSimilarDataSourceFactory = TvShowSimilarDataSourceFactory(id,compositeDisposable,tvShowRepositoryI)
        moviePagedList = LivePagedListBuilder(tvShowSimilarDataSourceFactory, config).build()
        state = Transformations.switchMap<TvShowSimilarDataSource, MovieListState>(
            tvShowSimilarDataSourceFactory.moviesLiveDataSource, TvShowSimilarDataSource::mutableLiveData)
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