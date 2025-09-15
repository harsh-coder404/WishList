package com.example.mywishliistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishliistapp.data.Wish
import com.example.mywishliistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository         // Going to make such we can pass the wishRepository , but it's not necessary to pass it
): ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChange(newString: String){
        wishTitleState = newString
    }

    fun onWishDescriptionChange(newString: String){
        wishDescriptionState = newString
    }

    lateinit var getAllWishes: Flow<List<Wish>>      // lateinit - a promise to initializes the variable before starting an operation
   // Flow - Or any kind of coroutines may take a little while to work so taking time to initialize a variable , thus always use 'lateinit' in such cases
    // only to use with mutable values (eg - var)

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {  // Dispatchers - are used to decide in which thread a coroutine should run (used in optimizing)
                                                           // IO - this dispatcher is used when an IO operation is held (requires Reading and Writing in Db)
            wishRepository.addWish(wish = wish)
        }
    }

    fun getAWishById(id:Long):Flow<Wish>{
        return wishRepository.getAWishById(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish = wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wish = wish)
        }
    }
}