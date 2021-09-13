 package com.example.quoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.quoteapp.databinding.ActivityMainBinding


 class MainActivity : AppCompatActivity() {
     //creating instance of ViewModel class
    lateinit var mainViewModel:MainViewModel
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel=ViewModelProvider(this,ViewModelFactory(application)).get(MainViewModel::class.java)
        //this function will take data from MainViewModel as we add all the data over there in viewModel
        setQuote(mainViewModel.getQuote())
    }
     fun setQuote(quote:Quote){
         binding.quoteText.text=quote.text
         binding.quoteAuthor.text=quote.author
     }

     fun previousQuote(view: android.view.View) {
         setQuote(mainViewModel.previousQuote())
     }
     fun nextQuote(view: android.view.View) {
         setQuote(mainViewModel.nextQuote())
     }
     fun onShare(view: android.view.View) {
         val intent =Intent(Intent.ACTION_SEND)
         intent.type = "text/plain"
         intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
         startActivity(intent)
     }
 }