package tn.sim.locagest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tn.sim.locagest.adapters.ConversationsListAdapter
import tn.sim.locagest.databinding.FragmentConversationListBinding
import tn.sim.locagest.models.Conversation
import tn.sim.locagest.viewmodel.ConversationViewModel

class ConversationListFragment : Fragment() {
    private lateinit var binding : FragmentConversationListBinding

    //For Subjects ViwModel
    lateinit var conversationViewModel: ConversationViewModel
    lateinit var listConversations: MutableList<Conversation>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentConversationListBinding.inflate(layoutInflater)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        var idUser = "654bee233415c8cea0e3557c"     //Static à changer

        listConversations = mutableListOf()
        conversationViewModel = ViewModelProvider(this).get(ConversationViewModel::class.java)

        conversationViewModel.getLessonConversation(idUser)

        conversationViewModel.getConversationListOfAUserObservable().observe(viewLifecycleOwner, Observer<List<Conversation>?> {
            if(it == null) {
                Log.w("MyApp", "There is no Conversations")
            }else {
                listConversations = it.toMutableList()
                initRecyclerView()
            }
        })
    }

    fun initRecyclerView() {
        val newsAdapter = ConversationsListAdapter(listConversations)
        binding.conversationListRV.adapter = newsAdapter
        binding.conversationListRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}