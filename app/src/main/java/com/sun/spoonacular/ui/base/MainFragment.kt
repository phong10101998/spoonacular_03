package com.sun.spoonacular.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sun.spoonacular.R
import com.sun.spoonacular.utils.MenuNumber
import com.sun.spoonacular.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSearch -> containerViewpager.currentItem = MenuNumber.SEARCH.pageNumber
            R.id.itemHome -> containerViewpager.currentItem = MenuNumber.HOME.pageNumber
            R.id.itemFavourite -> containerViewpager.currentItem = MenuNumber.FAVOURITE.pageNumber
        }
        return false
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        bottomNavigation.menu.getItem(position).isChecked = true
        hideKeyboard()
    }

    override fun onPageScrollStateChanged(state: Int) {}

    private fun setUpViewPager() {
        containerViewpager.adapter = MainMenuViewPagerAdapter(childFragmentManager)
        containerViewpager.apply {
            addOnPageChangeListener(this@MainFragment)
            currentItem = MenuNumber.HOME.pageNumber
            offscreenPageLimit = LIMIT_PAGE
        }
    }

    companion object {
        private const val LIMIT_PAGE = 2

        fun newInstance() = MainFragment()
    }
}
