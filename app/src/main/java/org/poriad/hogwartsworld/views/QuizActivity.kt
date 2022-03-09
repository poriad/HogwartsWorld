package org.poriad.hogwartsworld.views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import org.poriad.hogwartsworld.Apollo
import org.poriad.hogwartsworld.QuestionsQuizQuery
import org.poriad.hogwartsworld.R
import org.poriad.hogwartsworld.databinding.ActivityQuizBinding
import org.poriad.hogwartsworld.databinding.CustomToastBinding
import kotlin.random.Random

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var bindingToast: CustomToastBinding
    private var quiz: List<QuestionsQuizQuery.GetQuiz?> = emptyList()
    private var correctAnswer: Int = 0

    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.charactersNav -> {
                    startActivity(Intent(this, CharactersActivityView::class.java))
                    false
                }
                else -> false
            }
        }

        buttonOne = binding.answerOne
        buttonTwo = binding.answerTwo
        buttonThree = binding.answerThree
        buttonFour = binding.answerFour

        buttonOne.setOnClickListener(this)
        buttonTwo.setOnClickListener(this)
        buttonThree.setOnClickListener(this)
        buttonFour.setOnClickListener(this)
        getQuiz()
    }

    private fun getQuiz() {
        lifecycleScope.launchWhenResumed {
            quiz = Apollo.getQuiz()!!
            bindQuizToView()
        }
    }

    private fun bindQuizToView() {
        val oneQuestionWithAnswers = quiz[Random.nextInt(0, quiz.size)]
        if (oneQuestionWithAnswers != null) {
            val answers = oneQuestionWithAnswers.answers
            binding.question.text = oneQuestionWithAnswers.question
            buttonOne.text = answers?.get(0)?.answer
            buttonTwo.text = answers?.get(1)?.answer
            buttonThree.text = answers?.get(2)?.answer
            buttonFour.text = answers?.get(3)?.answer

            answers?.forEachIndexed { index, answer ->
                if(answer?.isTrue == true) {
                    correctAnswer = index + 1
                }
            }
        }
    }

    private fun customToast(message: String) {
        bindingToast = CustomToastBinding.inflate(layoutInflater)
        var view = layoutInflater.inflate(R.layout.custom_toast, findViewById<ViewGroup>(R.id.custom_toast_quiz))
        bindingToast.toastText.text = message
        var toast = Toast.makeText(applicationContext, "message", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL or Gravity.BOTTOM, 0, 200)
        toast.view = view
        toast.show()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.answerOne -> {
                if (correctAnswer == 1) {
                    buttonOne.setBackgroundColor(Color.GREEN)
                } else {
                    buttonOne.setBackgroundColor(Color.RED)
                    customToast("Error! No es correcta su respuesta")
                    buttonOne.animate().setDuration(2000).withEndAction {
                        buttonOne.setBackgroundColor(resources.getColor(R.color.purple_500))
                    }.start()
                }
            }
            R.id.answerTwo -> {
                if (correctAnswer == 2) {
                    buttonTwo.setBackgroundColor(Color.GREEN)
                } else {
                    buttonTwo.setBackgroundColor(Color.RED)
                    buttonTwo.animate().setDuration(2000).withEndAction {
                        buttonTwo.setBackgroundColor(resources.getColor(R.color.purple_500))
                    }.start()
                }
            }
            R.id.answerThree -> {
                if (correctAnswer == 3) {
                    buttonThree.setBackgroundColor(Color.GREEN)
                } else {
                    buttonThree.setBackgroundColor(Color.RED)
                    buttonThree.animate().setDuration(2000).withEndAction {
                        buttonThree.setBackgroundColor(resources.getColor(R.color.purple_500))
                    }.start()
                }
            }
            else -> {
                if (correctAnswer == 4) {
                    buttonFour.setBackgroundColor(Color.GREEN)
                } else {
                    buttonFour.setBackgroundColor(Color.RED)
                    buttonFour.animate().setDuration(2000).withEndAction {
                        buttonFour.setBackgroundColor(resources.getColor(R.color.purple_500))
                    }.start()
                }
            }
        }
    }
}