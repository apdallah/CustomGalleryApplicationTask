package com.apdallahyousry.customgalleryapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apdallahyousry.customgalleryapplication.data.local.ImageQueryHelper
import com.apdallahyousry.customgalleryapplication.data.local.MediaQueryHelper
import com.apdallahyousry.customgalleryapplication.data.local.VideoQueryHelper
import com.apdallahyousry.customgalleryapplication.data.models.MediaItemModel
import com.apdallahyousry.customgalleryapplication.data.models.MediaMapper
import com.apdallahyousry.customgalleryapplication.data.models.MediaType
import com.apdallahyousry.customgalleryapplication.data.repo.LocaleAlbumRepository
import com.apdallahyousry.customgalleryapplication.data.repo.MediaRepository
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MediaRepositoryTest {
    // Mock dependencies
    @Mock
    lateinit var imageQueryHelper: ImageQueryHelper

    @Mock
    lateinit var videoQueryHelper: VideoQueryHelper

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    // Class under test
    lateinit var mediaRepository: MediaRepository

    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this)

        // Create an instance of the repository with mocked dependencies
        mediaRepository = LocaleAlbumRepository(imageQueryHelper, videoQueryHelper, MediaMapper())
    }
    @Test
      fun `getAllMedia should return combined list of images and videos`() = runTest{
        // Arrange
        val mockImageList = listOf(MediaItemModel(1, "title", "pictures","",MediaType.MEDIA_TYPE_IMAGE),MediaItemModel(1, "title", "trip","",MediaType.MEDIA_TYPE_IMAGE))
        val mockVideoList = listOf(MediaItemModel(2, "title", "videos","",MediaType.MEDIA_TYPE_VIDEO))
        // Mock the behavior of queryMedia for image and video helpers
        Mockito.`when`(imageQueryHelper.queryMedia()).thenReturn(flowOf( mockImageList))
        Mockito.`when`(videoQueryHelper.queryMedia()).thenReturn(flowOf( mockVideoList))


        // Act
        val result = mediaRepository.readMedia()
        // Assert

        //total size should be 5 (All Images,All Videos,pictures,videos,trip)
        assert( result.toList().flatten().size== 5)


    }
}