package com.apdallahyousry.customgalleryapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apdallahyousry.customgalleryapplication.data.models.AlbumModel
import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import com.apdallahyousry.customgalleryapplication.ui.viewmodels.AlbumsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class AlbumsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mediaRepository: MediaRepository

    // Class under test
    lateinit var mediaViewModel: AlbumsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        // Inject TestCoroutineDispatcher for testing
        mediaViewModel = AlbumsViewModel(mediaRepository)
    }

    @Test
    fun `mediaList should emit expected values`()= runTest {
        // Arrange
        val mockMediaList = listOf(
            AlbumModel("TestAlbumName",null, listOf())
        )

        Mockito.`when`(mediaRepository.readMedia()).thenReturn(flowOf(mockMediaList))

        mediaViewModel.loadAlbums()

        assert(mediaViewModel.albumsLiveData.value == mockMediaList)
    }
}