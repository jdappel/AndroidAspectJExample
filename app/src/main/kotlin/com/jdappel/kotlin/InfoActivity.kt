package com.jdappel.kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.jdappel.example.R
import com.jdappel.profiler.annotation.ProfileDynamic
import com.jdappel.profiler.annotation.ProfileInstance

@ProfileDynamic
@ProfileInstance
class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab?.setOnClickListener {
            val uri = "mailto:archinamon@gmail.com" +
                "?subject=" + Uri.encode("Example project feedback")
            val intent = Intent(Intent.ACTION_SENDTO).setData(Uri.parse(uri))
            startActivity(Intent.createChooser(intent, "Send email feedback"))
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return true
    }
}