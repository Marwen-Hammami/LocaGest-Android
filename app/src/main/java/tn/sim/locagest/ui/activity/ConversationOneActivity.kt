package tn.sim.locagest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.models.Message
import java.util.Date
class ConversationOneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //récupérer l'id de la conv passé en intent

        val dataset = arrayOf(
            Message("id123","id554", "idSender", "Hello1", arrayListOf(), Date(), Date()),
            Message("id123","id554", "idSender", null, arrayListOf("test", "test2"), Date(), Date()),
            Message("id123","id554", "idSender","Hello3", arrayListOf(), Date(), Date()),
            Message("id123","id554", "idSender","Hello4", arrayListOf(), Date(), Date()),
        )

        val myAdapter = ConversationAdapter(dataset.reversed().toTypedArray())  //to get the latest message at the bottom

        binding.rvMessages.adapter = myAdapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true                                      //to get the latest message at the bottom
        binding.rvMessages.layoutManager = layoutManager
    }
}