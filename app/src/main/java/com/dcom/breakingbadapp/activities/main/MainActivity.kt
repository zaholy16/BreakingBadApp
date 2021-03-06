package com.dcom.breakingbadapp.activities.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.dcom.breakingbadapp.R
import com.dcom.breakingbadapp.activities.detailScreen.DetailActivity
import com.dcom.breakingbadapp.activities.login.LoginActivity
import com.dcom.breakingbadapp.databinding.ActivityMainBinding
import com.dcom.breakingbadapp.fragments.characters
import com.dcom.breakingbadapp.fragments.phrases
import com.dcom.breakingbadapp.fragments.settings
import com.dcom.breakingbadapp.models.Character
import com.dcom.breakingbadapp.models.Phrase
import com.dcom.breakingbadapp.services.QuotesService
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), characters.CharacterSelectListener, settings.onButtonLogoutListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characters = characters()
        val phrases = phrases()
        val settings = settings()

        makeCurrentFragment(characters)

        val bottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.charactersIcon -> makeCurrentFragment(characters)
                R.id.phraseIcon -> makeCurrentFragment(phrases)
                R.id.settingsIcon -> makeCurrentFragment(settings)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment:Fragment)= supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }

    override fun onCharacterSelected(character: Character){
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.CHARACTER_KEY, character)
        }
        startActivity(intent)
    }

    override fun onClickButtonLogout() {
        finish()
        val intent= Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

}
