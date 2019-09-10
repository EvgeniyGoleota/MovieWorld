package com.escorp.movieworld.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.escorp.movieworld.R
import com.escorp.movieworld.ui.actorDetailScreen.ActorDetailFragment
import com.escorp.movieworld.utils.*
import com.escorp.movieworld.utils.enums.DetailActivityTag
import com.escorp.movieworld.utils.enums.DetailActivityTag.*
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initFragment()
    }

    private fun initFragment() {
        when (intent.getSerializableExtra(TAG) as DetailActivityTag) {
            ACTOR -> loadFragment(ActorDetailFragment())
//            MOVIE -> loadFragment()
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
