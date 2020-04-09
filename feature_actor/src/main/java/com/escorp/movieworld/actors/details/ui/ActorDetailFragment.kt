package com.escorp.movieworld.actors.details.ui

import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.actors.R
import com.escorp.movieworld.actors.details.ui.credits.ActorCreditsFragment
import com.escorp.movieworld.actors.details.ui.info.ActorInfoFragment
import com.escorp.movieworld.actors.details.ui.photos.ActorPhotoFragment
import com.escorp.movieworld.core.dagger.viewmodel.ViewModelFactory
import com.escorp.movieworld.core.ui.base.BasePagerFragment
import com.escorp.movieworld.core.ui.PagerAdapter
import javax.inject.Inject

class ActorDetailFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory
) : BasePagerFragment() {

    private val args: ActorDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(
        childFragmentManager
    ).apply {
        addTab(ActorInfoFragment.newInstance(args.personId, viewModelFactory) to resources.getString(R.string.title_info))
        addTab(ActorPhotoFragment.newInstance(args.personId, viewModelFactory) to resources.getString(R.string.title_photos))
        addTab(ActorCreditsFragment.newInstance(args.personId, viewModelFactory) to resources.getString(R.string.title_movies))
    }
}