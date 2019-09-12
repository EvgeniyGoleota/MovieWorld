package com.escorp.movieworld.ui.detailScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsInfo.ActorInfoFragment
import com.escorp.movieworld.ui.detailScreen.actorDetail.actorsPhotos.ActorPhotoFragment
import com.escorp.movieworld.utils.*
import com.escorp.movieworld.utils.enums.DetailActivityTag
import com.escorp.movieworld.utils.enums.DetailActivityTag.*
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        configActivity()
    }

    private fun configActivity() {
        id = intent.getSerializableExtra(ID) as Long

        id?.let {
            when (intent.getSerializableExtra(TAG) as DetailActivityTag) {
                ACTOR -> {
                    initActorNavView()
                    loadFragment(ActorInfoFragment())
                }
                MOVIE -> {
//                initMovieNavView()
//                loadFragment()
                }
            }
        }
    }

    private fun initActorNavView() {
        navigation_view.apply {
            inflateMenu(R.menu.bottom_nav_menu_detail_screen_actor)
            setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.info -> {
                        loadFragment(ActorInfoFragment())
                        return@setOnNavigationItemSelectedListener true
                    }
                    R.id.photos -> {
                        loadFragment(ActorPhotoFragment())
                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.movies -> {
//                        loadFragment()
                        return@setOnNavigationItemSelectedListener true
                    }
                }
                false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            commit()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
