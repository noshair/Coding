package com.winter.check24codingchalleng.section.game_start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.winter.check24codingchalleng.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_start.*
@AndroidEntryPoint
class StartGameFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        helloText.setOnClickListener {
            val navController = NavHostFragment.findNavController(this)
            navController.navigate(
                R.id.action_startFragment_to_quizQuestionFragment,
            )
        }

    }

}