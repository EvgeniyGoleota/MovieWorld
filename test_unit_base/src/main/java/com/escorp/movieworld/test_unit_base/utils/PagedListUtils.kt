package com.escorp.movieworld.test_unit_base.utils

import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.ArgumentMatchers

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = mock<PagedList<T>>()

    whenever(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }

    whenever(pagedList.size).thenReturn(list.size)

    return pagedList
}