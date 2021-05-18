package com.themovieviewer.momain.util

interface DomainMapper <T, DomainModel>{
    fun mapToDomainModel(model: T): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): T
}