package tn.sim.locagest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import tn.sim.locagest.R
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.databinding.ActivityMainBinding
import tn.sim.locagest.models.Message
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataset = arrayOf(
            Message("id123","id554", "Hello", arrayListOf(), Date(), Date()),
            Message("id123","id554", "Hello", arrayListOf(), Date(), Date()),
            Message("id123","id554", "Hello", arrayListOf(), Date(), Date()),
            Message("id123","id554", "Hello", arrayListOf(), Date(), Date()),
        )

        Log.w("messageslist", "before adapter")
        val myAdapter = ConversationAdapter(dataset)

        binding.rvMessages.adapter = myAdapter
    }
}