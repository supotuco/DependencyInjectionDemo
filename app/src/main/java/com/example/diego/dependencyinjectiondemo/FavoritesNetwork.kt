package com.example.diego.dependencyinjectiondemo

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

enum class Strength {
    OK,
    A_LOT,
    TOO_MUCH
}

data class FavoriteItem(val name: String, val attachment: Strength)

internal fun Int.toStrength(): Strength {
    val size = Strength.values().size
    val index = ((this % size) + size) % size
    return Strength.values()[index]
}

class FavoritesNetwork {
    fun getFavorites(): Observable<List<FavoriteItem>> {
        val favorites = List(100) { FavoriteItem("I like the number $it", it.toStrength()) }

        return Observable.just(favorites)
                .delay(1000, TimeUnit.MILLISECONDS)
    }
}
