package com.reactions.fixt.data.datasource.album

import android.annotation.SuppressLint
import io.reactivex.Single
import com.reactions.fixt.data.datasource.BaseDataSource
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity

@SuppressLint("CheckResult")
fun getAlbums(
        apiSource: AlbumsApiDataSource,
        page: Int,
        itemsPerPage: Int,
        onResult: (result: ResultState<List<Entity.Album>>) -> Unit
) {
    apiSource.getAlbums((page - 1) * itemsPerPage, itemsPerPage)
            .subscribe({ data ->
                onResult(ResultState.Success(data))
            }, { throwable ->
                onResult(ResultState.Error(throwable, null))
            })
}

/**
 * Album network data source
 */
interface AlbumsApiDataSource : BaseDataSource {

    /**
     * Get all of albums from network
     */
    fun getAlbums(page: Int, pageSize: Int): Single<List<Entity.Album>>
}