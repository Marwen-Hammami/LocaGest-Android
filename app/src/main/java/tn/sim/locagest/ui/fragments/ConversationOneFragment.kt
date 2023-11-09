package tn.sim.locagest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import tn.sim.locagest.R
import tn.sim.locagest.adapters.ConversationAdapter
import tn.sim.locagest.databinding.FragmentConversationOneBinding
import tn.sim.locagest.models.Message

class ConversationOneFragment: Fragment() {

    private lateinit var binding : FragmentConversationOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("ConversationOneFragment", "onCreateView")
        binding = FragmentConversationOneBinding.inflate(layoutInflater)
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView() {
        //init static data
        val listMessages = arrayListOf<Message>(
            Message("id","id","Bonjour",null, null, null),
            Message("id","id","Comment tu vas",null, null, null),
            Message("id","id","Bien",null, null, null),
            Message("id","id","oui",null, null, null)
            )

        Log.d("ConversationAdapter", "List size: ${listMessages.size}")

        val conversationAdapter = ConversationAdapter(listMessages)
        binding.rvMessages.adapter = conversationAdapter
        binding.rvMessages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}