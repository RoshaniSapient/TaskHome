package com.example.hometask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hometask.helper.Status
import com.example.hometask.helper.StockApiState
import com.example.hometask.model.stockList
import com.example.hometask.reporsitory.StockConfig
import com.example.hometask.reporsitory.StockListRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class stockListViewModel : ViewModel() {
    private val repository = StockListRepository(StockConfig.ApiService())
    val commentState = MutableStateFlow(
        StockApiState(
            Status.LOADING,
            stockList(), ""
        )
    )

    init {
        fetchList()
    }

    fun fetchList()
    {
        viewModelScope.launch {
            repository.getStockList(1).catch {  commentState.value =
                StockApiState.error(it.message.toString()) }
                .collect {
                    commentState.value =StockApiState.success(it.data)
                }
        }
    }
}