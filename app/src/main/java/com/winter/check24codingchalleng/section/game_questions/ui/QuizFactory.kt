package com.winter.check24codingchalleng.section.game_questions.ui

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.winter.check24codingchalleng.R
import com.winter.check24codingchalleng.databinding.GameItemBinding
import com.winter.check24codingchalleng.service.model.game_model.QuizResponseItem
import com.winter.check24codingchalleng.service.utils.UtilsSharePreferences

class QuizFactory(private val quizClickListener: QuizClickListener, private val context: Context) :
    RecyclerView.Adapter<QuizFactory.FirstViewHolder>() {

    // data
    private lateinit var recyclerViewList: List<QuizResponseItem>
    private var currentValue=0
    class FirstViewHolder(var itemFirstBinding: GameItemBinding) :
        RecyclerView.ViewHolder(itemFirstBinding.root)

    interface QuizClickListener {
        fun itemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstViewHolder {

        val view: GameItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.game_item,
                parent,
                false
            )
        return FirstViewHolder(view)
    }

    override fun onBindViewHolder(holder: FirstViewHolder, position: Int) {
       // val item = getItem(position)
        holder.itemFirstBinding.facotry = recyclerViewList[position]
        setBackColor(holder)
       currentValue= UtilsSharePreferences.getCurrentScore("current",context)

        holder.itemFirstBinding.a.setOnClickListener {
            if (recyclerViewList[position].correct_answer == "answer_a") {
                holder.itemFirstBinding.a.background =
                    ContextCompat.getDrawable(context,R.drawable.right)
                holder.itemFirstBinding.score.text=currentValue++.toString()
                UtilsSharePreferences.setCurrentScore("current",currentValue++,context)
            }else{
                holder.itemFirstBinding.a.background =
                    ContextCompat.getDrawable(context,R.drawable.wrong)
            }
            quizClickListener.itemClicked(position+2)

        }
        holder.itemFirstBinding.b.setOnClickListener {
            if (recyclerViewList[position].correct_answer == "answer_b") {
                holder.itemFirstBinding.b.background =
                    ContextCompat.getDrawable(context,R.drawable.right)
                holder.itemFirstBinding.score.text=currentValue++.toString()
            }else{
                holder.itemFirstBinding.b.background =
                    ContextCompat.getDrawable(context,R.drawable.wrong)
            }

            quizClickListener.itemClicked(position+2)
        }

       /* holder.itemFirstBinding.root.setOnClickListener {
            quizClickListener.itemClicked(item)
        }*/
    }
    private fun setBackColor(holder: FirstViewHolder){
        holder.itemFirstBinding.a.background= ContextCompat.getDrawable(context,R.drawable.rounded_corner)
        holder.itemFirstBinding.b.background= ContextCompat.getDrawable(context,R.drawable.rounded_corner)
        holder.itemFirstBinding.c.background= ContextCompat.getDrawable(context,R.drawable.rounded_corner)
        holder.itemFirstBinding.d.background= ContextCompat.getDrawable(context,R.drawable.rounded_corner)

    }
    private fun getItem(position: Int): QuizResponseItem {
        return recyclerViewList[position]
    }

    override fun getItemCount(): Int {
        if (::recyclerViewList.isInitialized) {
            return recyclerViewList.size
        }
        return 0
    }

    // reloadData()
    fun update(users: List<QuizResponseItem>,position:Int) {
        notifyItemChanged(position)
        recyclerViewList = kotlin.collections.ArrayList(users)

    }
}