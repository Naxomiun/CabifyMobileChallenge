package com.nramos.core.data.di

import com.nramos.core.data.repository.CartRepositoryImpl
import com.nramos.core.data.repository.ProductRepositoryImpl
import com.nramos.core.domain.repository.CartRepository
import com.nramos.core.domain.repository.ProductsRepository
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