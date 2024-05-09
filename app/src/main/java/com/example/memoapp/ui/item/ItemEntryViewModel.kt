package com.example.memoapp.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.memoapp.data.Item
import com.example.memoapp.data.ItemsRepository

/**
 * ItemEntryViewModel:入力項目の検証機能とRoom処理を実装するクラス
 */

class ItemEntryViewModel(private val itemsRepository: ItemsRepository) :
    ViewModel() {
    // アイテムの状態を保持
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    // 引数で指定された値でitemUiStateを更新する
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(
            itemDetails = itemDetails, isEntryValid = validateInput(itemDetails)
        )
    }

    // 入力が有効の場合のみ、DBにアイテムを1件追加する
    suspend fun saveItem() {
        if (validateInput()) {
            itemsRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    // 入力値が有効か否かを検証する。タイトル、説明が空白ではない場合のみ有効に設定
    private fun validateInput(
        uiState: ItemDetails = itemUiState.itemDetails
    ): Boolean {
        return with(uiState) {
            title.isNotBlank() && description.isNotBlank()
        }
    }
}

/**
 * ItemUiState:アイテムのUI状態を表すデータクラス
 * アイテム詳細ItemDetailsとその入力が有効か否かを表すBoolean値を保持する
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

/**
 * ItemDetails:アイテムの詳細を表すデータクラス
 * 各項目を状態を保持する
 */
data class ItemDetails(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val done: Boolean = false
)

// ItemDetailsクラスをItemへ変換する拡張関数
fun ItemDetails.toItem(): Item = Item(
    id = id, title = title, description = description, done = done
)


// ItemをItemUiStateへ変換する拡張関数
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState =
    ItemUiState(
        itemDetails = this.toItemDetails(), isEntryValid = isEntryValid
    )

// ItemをItemDetailsへ変換する拡張関数
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id, title = title, description = description, done = done
)