package com.reactions.fixt.data.mapper

import com.reactions.fixt.data.db.album.AlbumData
import com.reactions.fixt.domain.entity.Entity

/**
 * Extension class to map album entity to album data
 */
fun Entity.Album.map() = AlbumData.Album(
        id = id,
        userId = userId,
        title = title
)