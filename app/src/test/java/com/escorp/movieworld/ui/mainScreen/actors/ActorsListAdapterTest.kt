package com.escorp.movieworld.ui.mainScreen.actors

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import androidx.test.core.app.ApplicationProvider
import com.escorp.movieworld.R
import com.escorp.movieworld.databinding.ActorListItemBinding
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import mockActor
import mockActorList
import mockPagedList
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import java.util.Arrays.asList

@RunWith(RobolectricTestRunner::class)
class ActorsListAdapterTest {

    private lateinit var adapter: ActorsListAdapter
    private lateinit var viewHolder: ActorsListAdapter.ViewHolder
    private val mockView: View = mock()
    private val mockFragment: Fragment = mock()

    @Before
    fun setUp() {
        adapter = ActorsListAdapter()
    }

    @Test
    fun checkItemCount() {
        val mockList = mockActorList()
        adapter.submitList(mockPagedList(mockList))
        assertEquals(mockList.size, adapter.itemCount)
    }

    @Test
    fun checkItemAtPosition() {
        val firstActor = mockActor()
        val secondActor = mockActor()
        adapter.submitList(mockPagedList(listOf(firstActor, secondActor)))

        assertEquals(firstActor, adapter.currentList?.get(0))
        assertEquals(secondActor, adapter.currentList?.get(1))
    }

    @Test
    fun checkView() {
        val fakeUserName = "Fake User"
        val actor = mockActor().apply {
            name = fakeUserName
        }

        adapter.submitList(mockPagedList(listOf(actor)))

        val inflater = ApplicationProvider.getApplicationContext<Application>()
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val itemBinding = ActorListItemBinding.inflate(inflater, null, false)
        viewHolder = ActorsListAdapter().ViewHolder(itemBinding)

        adapter.onBindViewHolder(viewHolder, 0)

        assertEquals(fakeUserName, viewHolder.itemView.)
    }
}