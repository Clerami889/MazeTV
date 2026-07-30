package com.test.mazetv

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.test.mazetv.core.UiState
import com.test.mazetv.data.models.Cast
import com.test.mazetv.data.models.Character
import com.test.mazetv.data.models.Episode
import com.test.mazetv.data.models.Image
import com.test.mazetv.data.models.Person
import com.test.mazetv.data.models.Rating
import com.test.mazetv.data.models.Season
import com.test.mazetv.data.models.Shows
import com.test.mazetv.main.details.viewModel.ShowDetailsViewModel
import com.test.mazetv.repository.ShowRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShowDetailsViewModelTest {

  private val testDispatcher = StandardTestDispatcher()
  private lateinit var viewModel: ShowDetailsViewModel
  private lateinit var repository: ShowRepository

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    repository = mockk(relaxed = true)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `fetchShowDetails emits Success with show details`() = runTest {
    val showsId = 1
    val fakeShow =
        Shows(
            id = showsId,
            name = "Test Show",
            url = "url",
            rating = Rating(10.0),
            image = Image("original", "medium"),
            summary = "Summary",
            premiered = "2024",
        )
    val fakeSeasons = listOf(Season(id = 101, number = 1, name = "Season 1", image = null))
    val fakeCast =
        listOf(
            Cast(
                person = Person(1, "Actor", null),
                character = Character(1, "Role", null),
            )
        )

    coEvery { repository.getShowsById(showsId) } returns fakeShow
    coEvery { repository.getSeasons(showsId) } returns fakeSeasons
    coEvery { repository.getCast(showsId) } returns fakeCast
    coEvery { repository.getEpisodesBySeason(any()) } returns emptyList()

    viewModel =
        ShowDetailsViewModel(
            repository,
            savedStateHandle = SavedStateHandle(mapOf("showsId" to showsId)),
        )

    viewModel.showState.test {
      // The first item is either Loading or Success depending on how fast the init block runs
      // Turbine collects items as they are emitted.
      var item = awaitItem()
      if (item is UiState.Loading) {
        item = awaitItem()
      }

      assertTrue("Expected Success but got $item", item is UiState.Success)
      val data = (item as UiState.Success).data
      assertEquals(fakeShow, data.shows)
      assertEquals(fakeSeasons, data.seasons)
      assertEquals(fakeCast, data.cast)
    }
  }

  @Test
  fun `selectSeason emits Loading then Success with episodes`() = runTest {
    val showsId = 1
    val fakeShow = mockk<Shows>(relaxed = true)
    val fakeSeasons = listOf(Season(id = 101, number = 1, name = "Season 1", image = null))

    coEvery { repository.getShowsById(showsId) } returns fakeShow
    coEvery { repository.getSeasons(showsId) } returns fakeSeasons
    coEvery { repository.getCast(showsId) } returns emptyList()
    coEvery { repository.getEpisodesBySeason(109) } returns emptyList()

    viewModel =
        ShowDetailsViewModel(
            repository,
            savedStateHandle = SavedStateHandle(mapOf("showsId" to showsId)),
        )

    // Wait for init block and initial selectSeason(101) to finish
    advanceUntilIdle()

    val fakeEpisodes =
        listOf(
            Episode(
                id = 1,
                name = "Pilot",
                season = 1,
                number = 1,
                image = Image("original", "medium"),
            )
        )
    coEvery { repository.getEpisodesBySeason(102) } returns fakeEpisodes

    viewModel.seasonEpisodesState.test {
      val initialItem = awaitItem()
      assertTrue(
          "Initial item should be Success, but was $initialItem",
          initialItem is UiState.Success,
      )

      viewModel.selectSeason(102)

      val loading = awaitItem()
      assertTrue("Expected Loading state, but was $loading", loading is UiState.Loading)

      val success = awaitItem()
      assertTrue("Expected Success state, but was $success", success is UiState.Success)
      assertEquals(fakeEpisodes, (success as UiState.Success).data)
    }
  }
}
