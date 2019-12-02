package com.example.albumkt.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.albumkt.barcode.zxing.ScanActivity
import com.example.albumkt.R
import com.example.albumkt.base.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.samples.apps.mlkit.EntryChoiceActivity


class MainActivity : BaseActivity() {


    @BindView(R.id.container)
    lateinit var container: FrameLayout
    @BindView(R.id.tl_bottom)
    lateinit var tlBottom: TabLayout

    private lateinit var imageFragment: ImageFragment
    private fun isImageFragmentInitialized() = ::imageFragment.isInitialized

    private lateinit var videoFragment: VideoFragment
    private fun isVideoFragmentInitialized() = ::videoFragment.isInitialized

    private lateinit var allFragment: AllFragment
    private fun isAllFragmentInitialized() = ::allFragment.isInitialized

    private lateinit var navigationView: NavigationView

    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolBar()
        initNavigation()
        initMainContent()
    }

    private fun initToolBar() {
        // Note that the Toolbar defined in the layout has the id "my_toolbar"
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START, true)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        showMenuIcon(menu)
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * Show icons of the action bar menu items
     * @param menu
     */
    private fun showMenuIcon(menu: Menu?) {
        if (menu != null) {
            if (menu::class.java.simpleName == "MenuBuilder") {
                try {
                    val m = menu::class.java.getDeclaredMethod(
                        "setOptionalIconsVisible",
                        Boolean::class.java
                    )
                    m.isAccessible = true
                    m.invoke(menu, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_more, menu)
        //menu?.findItem(R.id.action_settings)?.isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {

            }
            R.id.action_scan -> {
                startActivity(Intent(this,ScanActivity::class.java)) // zxing barcode
            }
            R.id.action_scan2 -> {
                startActivity(Intent(this, EntryChoiceActivity::class.java))  // Firebase barcode
            }
            R.id.action_settings -> {

            }
            R.id.action_feedback -> {

            }
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initNavigation() {
        navigationView = findViewById(R.id.navigation)
        drawerLayout = findViewById(R.id.drawerLayout)
    }

    private fun initMainContent() {
        container = findViewById(R.id.container)
        tlBottom = findViewById(R.id.tl_bottom)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        imageFragment = ImageFragment()
        fragmentTransaction.add(R.id.container, imageFragment)
        fragmentTransaction.show(imageFragment)
        fragmentTransaction.commit()

        val tabSelectedListener = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                switchFragment(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                hideFragment(tab)

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                switchFragment(tab)

            }
        }

        tlBottom.addOnTabSelectedListener(tabSelectedListener)
    }


    fun switchFragment(tab: TabLayout.Tab) {
        val pos = tab.position
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (pos) {
            0 -> {
                if (!isImageFragmentInitialized()) {
                    imageFragment = ImageFragment()
                    fragmentTransaction.add(R.id.container, imageFragment)
                }
                fragmentTransaction.show(imageFragment)
            }
            1 -> {
                if (!isVideoFragmentInitialized()) {
                    videoFragment = VideoFragment()
                    fragmentTransaction.add(R.id.container, videoFragment)
                }
                fragmentTransaction.show(videoFragment)
            }
            2 -> {

                if (!isAllFragmentInitialized()) {
                    allFragment = AllFragment()
                    fragmentTransaction.add(R.id.container, allFragment)
                }
                fragmentTransaction.show(allFragment)
            }
        }
        fragmentTransaction.commit()

    }

    fun hideFragment(tab: TabLayout.Tab) {
        val pos = tab.position
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (pos) {
            0 -> {
                if (isImageFragmentInitialized()) {
                    fragmentTransaction.hide(imageFragment)
                }
            }
            1 -> {
                if (isVideoFragmentInitialized()) {
                    fragmentTransaction.hide(videoFragment)
                }

            }
            2 -> {
                if (isImageFragmentInitialized()) {
                    fragmentTransaction.hide(allFragment)
                }
            }
        }
        fragmentTransaction.commit()
    }

}
