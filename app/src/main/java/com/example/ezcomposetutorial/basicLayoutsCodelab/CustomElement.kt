package com.example.ezcomposetutorial.basicLayoutsCodelab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 *
 * Create by Ervin on 2023/11/7
 **/
@Composable
fun TestViewModel() {
    val viewModel: CViewModel = viewModel()
    //val viewModel: CViewModel = rememberViewModel()
    val count by viewModel.count.observeAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(count.toString(), modifier = Modifier.padding(10.dp))
        Button(onClick = {
            // 点击事件
            viewModel.onCountChanged(count + 2)
        }) {
            Text("Add Count")
        }
    }
}

class CViewModel(defaultCount: Int = 0) : ViewModel() {

    private val _count = MutableLiveData(defaultCount)

    val count: LiveData<Int>
        get() = _count

    fun onCountChanged(count: Int) {
        _count.postValue(count)
    }

}