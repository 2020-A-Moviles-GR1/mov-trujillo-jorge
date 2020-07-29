package com.llamasoftworks.examen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val numOfTabs = 2
    val fragmentList = arrayListOf<Fragment>(CartasFragment(),ExpansionesFragment())
    val titleList = arrayListOf<String>("Cartas","Expansiones")
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
    override fun getCount(): Int {
        return numOfTabs
    }
}