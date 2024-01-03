package tn.sim.locagest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.adapters.ConversationUserListAdapter
import tn.sim.locagest.databinding.ActivityConversationListUsersBinding
import tn.sim.locagest.databinding.ActivityConversationOneBinding
import tn.sim.locagest.models.User

class ConversationUserListactivity : AppCompatActivity() {

    private lateinit var binding : ActivityConversationListUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listUsers = arrayListOf<User>(
            User("123456789","username","youremail@gmail.com", "12345678", "Ahmed", "Tounsi", "123456789", "GOOD", "Client", 3, "client", null),
            User("123456789","username","youremail@gmail.com", "12345678", "Samir", "Hammami", "123456789", "GOOD", "Client", 3, "client", null),
            User("123456789","username","youremail@gmail.com", "12345678", "Amira", "Jrad", "123456789", "GOOD", "Client", 3, "client", null),
            User("123456789","username","youremail@gmail.com", "12345678", "Khaled", "Arbi", "123456789", "GOOD", "Client", 3, "client", null),
            User("123456789","username","youremail@gmail.com", "12345678", "Youssef", "Hammami", "123456789", "GOOD", "Client", 3, "client", null),
            User("123456789","username","youremail@gmail.com", "12345678", "Maher", "Shiri", "123456789", "GOOD", "Client", 3, "client", null),
        )

        val myAdapter = ConversationUserListAdapter(listUsers)

        binding.usersRV.adapter = myAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.usersRV.layoutManager = layoutManager
    }
}