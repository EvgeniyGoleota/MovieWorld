package com.escorp.movieworld.ui.detailScreen.actor

import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.actor.credits.ActorCreditsFragment
import com.escorp.movieworld.ui.detailScreen.actor.info.ActorInfoFragment
import com.escorp.movieworld.ui.detailScreen.actor.photos.ActorPhotoFragment
import com.escorp.movieworld.ui.uiUtils.PagerAdapter
import com.escorp.movieworld.ui.uiUtils.BasePagerFragment

class ActorDetailFragment : BasePagerFragment() {

    private val args: ActorDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(
        childFragmentManager
    ).apply {
        addTab(ActorInfoFragment.newInstance(args.personId) to resources.getString(R.string.title_info))
        addTab(ActorPhotoFragment.newInstance(args.personId) to resources.getString(R.string.title_photos))
        addTab(ActorCreditsFragment.newInstance(args.personId) to resources.getString(R.string.title_movies))
    }
}