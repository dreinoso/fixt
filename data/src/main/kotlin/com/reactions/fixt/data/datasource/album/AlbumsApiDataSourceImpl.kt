package com.reactions.fixt.data.datasource.album

import io.reactivex.Single
import com.com.reactions.fixt.data.api.AlbumApi
import com.reactions.fixt.data.common.extension.applyIoScheduler
import com.reactions.fixt.data.mapper.map
import com.reactions.fixt.domain.entity.Entity

/**
 * Album network data source implementation
 */
class AlbumsApiDataSourceImpl(private val api: AlbumApi) : AlbumsApiDataSource {

    /**
     * Get all of albums from network implementation
     */
    override fun getAlbums(page: Int, pageSize: Int): Single<List<Entity.Album>> =
            api.getAlbums(page, pageSize)
                    .applyIoScheduler()
                    .map { item -> item.map { it.map() } }
}