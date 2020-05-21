package com.escorp.movieworld.actors.internal.screen.details.ui.photos

import com.escorp.movieworld.actors.internal.screen.details.domain.GetPersonPhotosUseCase
import com.escorp.movieworld.actors.internal.screen.details.ui.info.ActorInfoViewModel
import com.escorp.movieworld.core.data.api.dto.common.Image
import com.escorp.movieworld.test_unit_base.AndroidArchComponentExtension
import com.escorp.movieworld.test_unit_base.RxJavaExtension
import com.escorp.movieworld.test_unit_base.test
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions

@Extensions(
    ExtendWith(AndroidArchComponentExtension::class),
    ExtendWith(RxJavaExtension::class)
)
internal class ActorPhotoViewModelTest {
    private val TEST_PERSON_ID = Int.MAX_VALUE

    private val imageMock = mock<Image>()

    private val getPersonPhotosUseCaseMock = mock<GetPersonPhotosUseCase>()

    private val actorInfoViewModel = ActorPhotoViewModel(getPersonPhotosUseCaseMock)

    @Test
    fun getPhotos() {
        whenever(getPersonPhotosUseCaseMock.invoke(any())).thenReturn(Single.just(listOf(imageMock)))

        actorInfoViewModel.getPhotos(TEST_PERSON_ID)
        actorInfoViewModel.photos.test().assertValue { it.size == 1 }

        verify(getPersonPhotosUseCaseMock).invoke(TEST_PERSON_ID)
        verifyNoMoreInteractions(getPersonPhotosUseCaseMock)
    }
}