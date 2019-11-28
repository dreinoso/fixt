package com.reactions.fixt.data.mapper

import com.com.reactions.fixt.data.api.AlbumApi
import com.reactions.fixt.domain.entity.Entity

/**
 * Extension class to map album dto to album entity
 */
fun AlbumApi.Dto.Album.map() = Entity.Album(
        id = id,
        userId = userId,
        title = title
)
