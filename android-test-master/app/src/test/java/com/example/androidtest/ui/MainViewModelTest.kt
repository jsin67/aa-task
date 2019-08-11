package com.example.androidtest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidtest.repo.Repo
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Rule

class MainViewModelTest {

    private lateinit var repo: Repo
    private lateinit var viewModel : MainViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repo = mock()
        viewModel = MainViewModel(repo)
    }

    @After
    fun tearDown() {
    }

}