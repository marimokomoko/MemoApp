package com.example.memoapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memoapp.R
import com.example.memoapp.ui.AppViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memoapp.data.Item
import com.example.memoapp.data.ItemsRepository
import com.example.memoapp.ui.TodoTopAppBar
import com.example.memoapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * HomeDestination:NavigationDestinationを実装したオブジェクト
 * @param route ナビ先のルートを識別する文字列
 * @param titleRes ナビ先のタイトルリソースID
 */
object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

/**
 * HomeScreen:一覧画面
 * @param navigateToItemEntry FAButtonがタップされた時の処理関数(新規作成画面へ)
 * @param navigateToItemUpdate アイテムがタップされたときの処理関数(更新画面へ)
 * @param modifier 修飾子
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit = {},
    navigateToItemUpdate: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel =
        viewModel(factory = AppViewModelProvider.Factory)
) {
    val itemList by viewModel.homeUiState.itemList
        .collectAsState(initial = emptyList())
    var showDone by remember { mutableStateOf(false) }
    var filteredItemList by remember(itemList, showDone) {
        mutableStateOf(itemList.filter {
            if (showDone) true
            else it.done == false
        })
    }

    Scaffold(
        topBar = {
            TodoTopAppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = navigateToItemEntry,
                modifier = Modifier
                    .padding(
                        dimensionResource(id = R.dimen.padding_large)
                    )
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.m_enpitsu),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )
            }
        }

    ) { innerPadding ->
        HomeBody(
            itemList = filteredItemList,
            onItemClick = { navigateToItemUpdate(it.id) },
            onCheckedChange = {
                showDone = it
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val mockObject = object : ItemsRepository {
        override fun getAllItemsStream(): Flow<List<Item>> = MutableStateFlow(
            listOf(
                Item(1, "めも 1", "めも詳細1", false),
                Item(2, "めも 2", "めも詳細2", true)
            )
        )

        override fun getItemsStream(id: Int): Flow<Item?> = MutableStateFlow(
            Item(1, "めも1", "めも詳細1", false)
        )

        override suspend fun insertItem(item: Item) {}
        override suspend fun deleteItem(item: Item) {}
        override suspend fun updateItem(item: Item) {}
    }
    HomeScreen(
        navigateToItemEntry = {},
        navigateToItemUpdate = {},
        viewModel = HomeViewModel(mockObject)
    )
}