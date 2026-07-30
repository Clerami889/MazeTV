package com.test.mazetv

import app.cash.turbine.test
import com.test.mazetv.core.UiState
import com.test.mazetv.data.models.Image
import com.test.mazetv.data.models.Rating
import com.test.mazetv.data.models.Shows
import com.test.mazetv.main.shows.viewModel.ShowViewModel
import com.test.mazetv.repository.ShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class ShowViewModelTest {
  private val testDispatcher = StandardTestDispatcher()
  private lateinit var viewModel: ShowViewModel
  private lateinit var repository: ShowRepository

  @OptIn(ExperimentalCoroutinesApi::class)
  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    repository = mockk()
    viewModel = ShowViewModel(repository)
  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `fetchShowDetails emits Success with show data`() = runTest {
    // Arrange
    val fakeShow =
        listOf(
            Shows(
                id = 1,
                name = "Test Show",
                url = "fakeurlafassadasdw",
                rating = Rating(average = 10.0),
                image = Image("original", medium = "medium"),
                summary = "Summary 1 bla bla bla",
                premiered = "2022",
            )
        )

    coEvery { repository.getShows() } returns fakeShow

    viewModel = ShowViewModel(repository)

    viewModel.showState.test {
      val loading = awaitItem()
      assertTrue(loading is UiState.Loading)
      assertEquals(UiState.Loading, loading)
      val success = awaitItem()
      assertTrue(success is UiState.Success)
      assertEquals(fakeShow, (success as UiState.Success).data)
    }
  }
}
