package com.codeinger.moviestack.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.codeinger.moviestack.R
import com.codeinger.moviestack.ui.dashboard.MainActivity
import com.github.paolorotolo.appintro.AppIntro
import spencerstudios.com.jetdblib.JetDB

class IntroScreen : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(IntroFragment.newInstance(" We find Different types of movies like Trending Movies,Trending Tv Show and so on,we also find here Trending Person....","Home",R.drawable.intro1))
        addSlide(IntroFragment.newInstance(" We find any detail of Actor or Actress....we can find every detail of any celebrities.....","People",R.drawable.intro5))
        addSlide(IntroFragment.newInstance(" We find Single Movie or Tv show detail....Their Cast,Crew,Genre,Trailor and production Companies......... ","Movie & Tv Show",R.drawable.intro2))
        addSlide(IntroFragment.newInstance("In the Bookmark we can save any movie or tv show to our favourite and this movie will show in the Bookmark.... ","Bookmark",R.drawable.intro6))
        addSlide(IntroFragment.newInstance("The Movie or Tv Show Which we is save in the bookmark we can Show our favourite selected list in this My List ..... ","My List",R.drawable.intro4))
        addSlide(IntroFragment.newInstance("We can Search any movie,Tv show and any person information with the help of search button.... ","Search",R.drawable.intro3))


        setSeparatorColor(Color.parseColor("#000000"))
        setNextArrowColor(Color.parseColor("#000000"))
        setColorDoneText(Color.parseColor("#000000"))
        setSeparatorColor(Color.parseColor("#000000"))
        setColorSkipButton(Color.parseColor("#000000"))

        isProgressButtonEnabled = true
        setSkipText(resources.getString(R.string.app_intro_skip_button))
        setDoneText(resources.getString(R.string.app_intro_done_button))

        setVibrate(true)
        setVibrateIntensity(30)
        setIndicatorColor(
            Color.parseColor("#000000"),
            Color.parseColor("#444444")
        )

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        JetDB.putBoolean(this@IntroScreen, true, "INTRO_DONE");
        startActivity(Intent(this@IntroScreen, MainActivity::class.java))
        finish()
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        JetDB.putBoolean(this@IntroScreen, true, "INTRO_DONE");
        startActivity(Intent(this@IntroScreen,MainActivity::class.java))
        finish()
    }


}