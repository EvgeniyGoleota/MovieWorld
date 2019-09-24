package com.escorp.movieworld.ui.detailScreen.actorDetail

import androidx.navigation.fragment.navArgs
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsCredits.ActorCreditsFragment
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo.ActorInfoFragment
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoFragment
import com.escorp.movieworld.utils.PagerAdapter
import com.escorp.movieworld.utils.BasePagerFragment

class ActorDetailFragment : BasePagerFragment() {

    private val args: ActorDetailFragmentArgs by navArgs()

    override fun createPagerAdapter() = PagerAdapter(childFragmentManager).apply {
        addTab(ActorInfoFragment.newInstance(args.personId) to resources.getString(R.string.title_info))
        addTab(ActorPhotoFragment.newInstance(args.personId) to resources.getString(R.string.title_photos))
        addTab(ActorCreditsFragment.newInstance(args.personId) to resources.getString(R.string.title_movies))
    }
}