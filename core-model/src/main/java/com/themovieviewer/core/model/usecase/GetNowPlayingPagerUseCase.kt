package com.themovieviewer.core.model.usecase

interface GetNowPlayingPagerUseCase{

    operator fun invoke(language: String): Any
}

