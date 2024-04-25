package com.example.memoapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.memoapp.data.Item
import com.example.memoapp.data.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * HomeUiState:ホーム画面のUIの状態を表すデータクラス
 */
data class HomeUiState(val itemList: Flow<List<Item>> = flowOf(listOf()))

/**
 * HomeViewModel:ホーム画面のデータを管理し、そのデータをUIに反映する
 */
class HomeViewModel(itemsRepository: ItemsRepository) : ViewModel() {
    val homeUiState = HomeUiState(itemsRepository.getAllItemsStream())
}