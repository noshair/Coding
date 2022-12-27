package com.winter.check24codingchalleng.section.game_questions

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.winter.check24codingchalleng.R
import com.winter.check24codingchalleng.databinding.FragmentQuizQuestionBinding
import com.winter.check24codingchalleng.section.game_questions.ui.QuizFactory
import com.winter.check24codingchalleng.service.extension.Resource
import com.winter.check24codingchalleng.service.model.game_model.QuizResponseItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.game_item.view.*

@AndroidEntryPoint
class QuizQuestionFragment : Fragment(), QuizFactory.QuizClickListener {

    private lateinit var quizQuestionViewModel: QuizQuestionViewModel
    private var factory: QuizFactory? = null
    private lateinit var quizFactoryBinding: FragmentQuizQuestionBinding
    private lateinit var list: List<QuizResponseItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizQuestionViewModel = ViewModelProvider(this)[QuizQuestionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizFactoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_question, container, false
        )

        return quizFactoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        quizQuestionViewModel.getQuizQuestions()
        initRecyclerview()
        lifecycleScope.launchWhenCreated {
            quizQuestionViewModel.quizList.collect {
                when (it) {
                    is Resource.OnFailure -> {
                        //  if (it.error != null) {
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                        //  }
                    }
                    is Resource.OnSuccess -> {
                        if (it.data != null) {
                            list = it.data
                            factory?.update(it.data,0)
                        }
                    }
                    is Resource.OnLoading -> {
                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        quizFactoryBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            factory = QuizFactory(this@QuizQuestionFragment, requireContext())
            this.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = factory
        }
    }

    override fun itemClicked(position: Int) {
        Handler().postDelayed({
            if (position > list.size){
                val navController = NavHostFragment.findNavController(this)
                navController.navigate(
                    R.id.action_quizQuestionFragment_to_gameEndFragment,
                )
            }else{
                quizFactoryBinding.recyclerView.adapter?.notifyItemChanged(position)
                quizFactoryBinding.recyclerView.smoothScrollToPosition(position-1)
            }
        }, 2000)
    }
}