package scb.academy.jinglebell.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import scb.academy.jinglebell.R
import scb.academy.jinglebell.fragment.CountryListFragment
import scb.academy.jinglebell.fragment.SongListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bnvNavigation: BottomNavigationView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener when (item.itemId) {
            R.id.action_country_list, R.id.action_song, R.id.action_profile -> {
                changeFragment(item.itemId)
                true
            }

            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnvNavigation = findViewById(R.id.bnv_navigation)

        bnvNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null) {
            changeFragment(bnvNavigation.selectedItemId)
        }
    }

    private fun changeFragment(id: Int) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)

        val newFragment = when (id) {
            R.id.action_country_list -> {
                if (currentFragment is CountryListFragment) return
                CountryListFragment()
            }

            R.id.action_song -> {
                if (currentFragment is SongListFragment) return
                SongListFragment()
            }

            R.id.action_profile -> { Fragment() }

            else -> return
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commit()
    }
}
