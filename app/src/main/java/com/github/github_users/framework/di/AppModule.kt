package com.github.github_users.framework.di

import android.content.Context
import com.github.github_users.presentation.NavigationHelper
import com.github.github_users.presentation.UserListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideNavigationHelper() = NavigationHelper()

    @Provides
    fun provideListAdapter(navigationHelper: NavigationHelper) = UserListAdapter(navigationHelper)


}