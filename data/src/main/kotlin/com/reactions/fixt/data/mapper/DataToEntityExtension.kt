package com.reactions.fixt.data.mapper

import com.reactions.fixt.data.db.album.AlbumData
import com.reactions.fixt.domain.entity.Entity

/**
 * Extension class to map album data to album entity
 */
fun AlbumData.Album.map() = Entity.Album(
        id = id,
        userId = userId,
        title = title
)