package com.example.diego.dependencyinjectiondemo

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class FavoritesBusiness {
    val network = FavoritesNetwork()

    fun getFavorites(): Observable<List<FavoriteItem>> {
        return network.getFavorites()
                .observeOn(Schedulers.computation())
                .map { it.sortedBy { it.attachment } }
    }
}
