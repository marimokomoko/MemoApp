package com.example.memoapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memoapp.R
import com.example.memoapp.data.Item

/**
 * TodoItem:各アイテムを表示するコンポーザブル関数
 * @param item Itemオブジェクト取得する
 * @param modifier 修飾子
 */
@Composable
fun TodoItem(
    item: Item,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_large)),
                verticalArrangement = Arrangement
                    .spacedBy(dimensionResource(id = R.dimen.padding_small))
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.title, style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.weight(1f))
                    Image(
                        painter = if (item.done) {
                            painterResource(id = R.drawable.ashiato_black)
                        } else {
                            painterResource(id = R.drawable.ashiato_pink)
                        },
                        contentDescription = "",
                        modifier = Modifier.size(width = 30.dp, height = 30.dp)
                    )
                }
            }
            if (item.done) {
                Divider(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
private fun TodoItemPre() {
    TodoItem(
        item = Item(1, "たまご買う", "30個は余裕で必要", true),
        modifier = Modifier.padding(16.dp)
    )
}