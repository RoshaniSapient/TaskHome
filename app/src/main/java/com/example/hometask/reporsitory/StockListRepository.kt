package com.example.hometask.reporsitory

import com.example.hometask.Service.ApiService
import com.example.hometask.helper.StockApiState
import com.example.hometask.model.stockList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StockListRepository(private val apiService: ApiService) {

    suspend fun getStockList(id: Int): Flow<StockApiState<List<stockList>>> {
        return flow {

            while (true) {
                val comment = apiService.getStckList(id)
                emit(StockApiState.success(comment))
                delay(8000)
            }
        }.flowOn(Dispatchers.IO)
    }
}