package tn.sim.locagest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.adapters.ConversationsListAdapter
import tn.sim.locagest.databinding.FragmentConversationListBinding
import tn.sim.locagest.models.User

class ConversationListFragment : Fragment() {
    private lateinit var binding : FragmentConversationListBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentConversationListBinding.inflate(layoutInflater)

        initRecyclerView()

        return binding.root
    }

    fun initRecyclerView() {
        //init static data
        val listUsers = arrayListOf<User>(
            User("123456789","username","youremail@gmail.com", "12345678", "Ahmed", "Tounsi", 123456789, "GOOD", "Client", 3, "client", true, "path/to/img.png"),
            User("123456789","username","youremail@gmail.com", "12345678", "Samir", "Hammami", 123456789, "GOOD", "Client", 3, "client", false, "path/to/img.png"),
            User("123456789","username","youremail@gmail.com", "12345678", "Amira", "Jrad", 123456789, "GOOD", "Client", 3, "client", true, "path/to/img.png"),
            User("123456789","username","youremail@gmail.com", "12345678", "Khaled", "Arbi", 123456789, "GOOD", "Client", 3, "client", false, "path/to/img.png"),
            User("123456789","username","youremail@gmail.com", "12345678", "Youssef", "Hammami", 123456789, "GOOD", "Client", 3, "client", false, "path/to/img.png"),
            User("123456789","username","youremail@gmail.com", "12345678", "Maher", "Shiri", 123456789, "GOOD", "Client", 3, "client", true, "path/to/img.png"),
        )

        val newsAdapter = ConversationsListAdapter(listUsers)
        binding.conversationListRV.adapter = newsAdapter
        binding.conversationListRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}