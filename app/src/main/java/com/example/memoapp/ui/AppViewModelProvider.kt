package com.example.memoapp.ui

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.memoapp.TodoApplication
import com.example.memoapp.ui.home.HomeViewModel
import com.example.memoapp.ui.item.ItemEntryViewModel

/**
 * AppViewModelProvider:viewModelFactory関数の結果を保持するオブジェクト
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // ホーム画面のViewModel初期化
        initializer {
            HomeViewModel(todoApplication().container.itemsRepository)
        }
        // 新規画面のViewModel初期化
        initializer {
            ItemEntryViewModel(todoApplication().container.itemsRepository)
        }
    }
}

/**
 * viewModelFactory:ViewModel作成時に追加の情報を提供するクラス
 */
fun CreationExtras.todoApplication(): TodoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
            as TodoApplication)