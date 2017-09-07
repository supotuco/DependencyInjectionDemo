package com.example.diego.dependencyinjectiondemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), Observer<List<FavoriteItem>> {

    val favoritesContainer by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
    val favoriteGetter = FavoritesBusiness()
    var subscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        favoritesContainer.layoutManager = LinearLayoutManager(this)
        favoritesContainer.adapter = FavoriteItemAdapter(listOf())
        favoriteGetter.getFavorites()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        subscription?.dispose()
    }

    override fun onComplete() {
    }

    override fun onNext(items: List<FavoriteItem>) {
        favoritesContainer.adapter = FavoriteItemAdapter(items)
    }

    override fun onSubscribe(d: Disposable) {
        subscription?.dispose()
        subscription = d
    }

    override fun onError(e: Throwable) {
        System.out.println("Stream Error: $e ")
    }
}
