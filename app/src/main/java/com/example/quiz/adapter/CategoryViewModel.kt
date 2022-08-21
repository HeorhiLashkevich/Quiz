package com.example.quiz.adapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.di.NetworkController
import com.example.quiz.network.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel
    : ViewModel(

) {

    val categories = MutableLiveData<ArrayList<String>>()


    fun getCategories() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = NetworkController.getQuizApi().getCategories()
//            if (response.isSuccessful) {
        val list: ArrayList<String> = arrayListOf("ArtsAndLiterature","FoodAndDrink","GeneralKnowledge"
            , "Geography", "History", "Music", "Science", "SocietyAndCulture", "SportAndLeisure"  )
//        list.add("ArtsAndLiterature")
//        list.add("FoodAndDrink")
//        list.add("GeneralKnowledge")
//        list.add("Geography")
//        list.add("History")
//        list.add("Music")
//        list.add("Science")
//        list.add("SocietyAndCulture")
//        list.add("SportAndLeisure")

//                list.add(Resources.getSystem().getString(R.string.ArtsAndLiterature))
//                list.add(Resources.getSystem().getString(R.string.FoodAndDrink))
//                list.add(Resources.getSystem().getString(R.string.GeneralKnowledge))
//                list.add(Resources.getSystem().getString(R.string.Geography))
//                list.add(Resources.getSystem().getString(R.string.History))
//                list.add(Resources.getSystem().getString(R.string.Music))
//                list.add(Resources.getSystem().getString(R.string.Science))
//                list.add(Resources.getSystem().getString(R.string.SocietyAndCulture))
//                list.add(Resources.getSystem().getString(R.string.SportAndLeisure))

//                list.add(response.body()?.Science.toString())
//                list.add(response.body()?.ArtsAndLiterature.toString())
//                list.add(response.body()?.FoodAndDrink.toString())
//                list.add(response.body()?.GeneralKnowledge.toString())
//                list.add(response.body()?.Geography.toString())
//                list.add(response.body()?.History.toString())
//                list.add(response.body()?.Music.toString())
//                list.add(response.body()?.Science.toString())
//                list.add(response.body()?.SocietyAndCulture.toString())
//                list.add(response.body()?.SportAndLeisure.toString())
        categories.postValue(list)

    }
}
