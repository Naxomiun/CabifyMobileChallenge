package com.nramos.cabifymobilechallenge.core.data.di

import com.nramos.cabifymobilechallenge.core.data.repository.CartRepositoryImpl
import com.nramos.cabifymobilechallenge.core.data.repository.ProductRepositoryImpl
import com.nramos.cabifymobilechallenge.core.domain.repository.CartRepository
import com.nramos.cabifymobilechallenge.core.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductsRepository

    @Binds
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository

}