package com.reactions.fixt.domain.usecase.album

import androidx.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import com.reactions.fixt.domain.common.ResultState
import com.reactions.fixt.domain.entity.Entity
import com.reactions.fixt.domain.usecase.BaseUseCase

/**
 * Album use case
 */
interface GetAlbumsUseCase : BaseUseCase {

    /**
     * Get all of albums use case
     */
    fun getAlbums(): Flowable<ResultState<PagedList<Entity.Album>>>

    fun deleteAlbum(album: Entity.Album): Single<ResultState<Int>>

    //fun loadAlbums(pageNumber: Int): Single<ResultState<List<Entity.Album>>>
}