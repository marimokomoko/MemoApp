package com.example.memoapp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.memoapp.R
import com.example.memoapp.data.Item

/**
 * HomeBody:ホーム画面のUI
 * @param itemList 表示するアイテムのリスト
 * @param onItemClick アイテムがクリックされたときのコールバック関数
 * @param onCheckedChange チェックボックス状態変更のコールバック関数
 * @param modifier 修飾子
 */
@Composable
fun HomeBody(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var checked by remember {
        mutableStateOf(false)
    }
    LazyColumn(modifier = modifier) {
        item {
            Row(
                Modifier.toggleable(value = checked,
                    role = Role.Checkbox,
                    onValueChange = {
                        checked = it
                        onCheckedChange(it)
                    })
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = null
                )
                Text(text = "完了済も表示する")
            }
        }
        if (itemList.isEmpty()) {
            item {
                Text(
                    text = "アイテムがないよ。追加してね",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        items(items = itemList, key = { it.id }) { item ->
            TodoItem(
                item = item, modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) }
            )
        }
    }
}

@Preview
@Composable
private fun HomeBodyPre() {
    HomeBody(
        itemList = listOf(
            Item(1, "お部屋掃除", "詳細", true),
            Item(2, "自転車のタイヤ空気入れる", "詳細", false)
        )
    )
}