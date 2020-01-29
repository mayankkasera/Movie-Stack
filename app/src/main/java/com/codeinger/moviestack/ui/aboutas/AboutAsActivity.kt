package com.codeinger.moviestack.ui.aboutas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.codeinger.moviestack.R
import com.codeinger.moviestack.utils.setAllOnClickListener
import kotlinx.android.synthetic.main.activity_about_as.*

class AboutAsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_as)

        github.setAllOnClickListener(
            View.OnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/mayankkasera")
                    )
                )
            }
        )





        linke.setAllOnClickListener(
            View.OnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/mayank-kasera-5b784a152")
                    )
                )
            }
        )

        insta.setAllOnClickListener(
            View.OnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/_mayank_98")
                    )
                )
            }
        )


        fb.setAllOnClickListener(
            View.OnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/mayank.kasera")
                    )
                )
            }
        )




    }
}
