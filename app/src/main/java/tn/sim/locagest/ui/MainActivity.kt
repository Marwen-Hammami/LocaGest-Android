package tn.sim.locagest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tn.sim.locagest.R
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}