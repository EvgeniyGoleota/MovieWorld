package com.escorp.movieworld.ui.uiUtils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.escorp.movieworld.utils.defaultPageSize

abstract class PaginationScrollListener(private val layoutManager: RecyclerView.LayoutManager?) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (layoutManager != null && layoutManager is LinearLayoutManager) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount >= defaultPageSize
                ) {
                    loadMore()
                }
            }
        }
    }

    protected abstract fun loadMore()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean
}