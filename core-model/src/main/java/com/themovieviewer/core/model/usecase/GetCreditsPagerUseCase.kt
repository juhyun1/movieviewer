package com.themovieviewer.core.model.usecase

interface GetCreditsPagerUseCase {

    operator fun invoke(movieId: Int, language: String): Any
}