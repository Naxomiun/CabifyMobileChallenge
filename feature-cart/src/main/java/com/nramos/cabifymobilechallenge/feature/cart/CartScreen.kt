package com.nramos.cabifymobilechallenge.feature.cart

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nramos.cabifymobilechallenge.core.domain.model.CartItem
import com.nramos.cabifymobilechallenge.core.domain.model.Order
import com.nramos.cabifymobilechallenge.core.presentation.ui.AppTopBar
import com.nramos.cabifymobilechallenge.core.presentation.ui.FullLoadingScreen

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartScreenViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    CartView(
        modifier = modifier,
        state = state,
    )
}

@Composable
fun CartView(
    modifier: Modifier = Modifier,
    state: CartScreenState
) {
    Box {
        Scaffold(
            modifier = modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            topBar = {
                AppTopBar(
                    title = stringResource(id = R.string.cart_title)
                )
            },
        ) {
            CartList(
                modifier = Modifier.padding(it),
                order = state.order
            )
        }
        FullLoadingScreen(isLoading = state.spinnerLoading)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CartList(
    modifier: Modifier = Modifier,
    order: Order?
) {
    AnimatedContent(targetState = order != null) {
        if (it) {
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(order!!.items) { item ->
                    CartItemView(
                        item = item,
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemView(
    modifier: Modifier = Modifier,
    item: CartItem
) {
    Row(modifier) {
        AsyncImage(
            modifier = Modifier
                .height(170.dp)
                .aspectRatio(0.8F)
                .clip(RoundedCornerShape(10)),
            model = item.product.mediaUrl,
            contentDescription = item.product.name,
            contentScale = ContentScale.Crop
        )
        Column {
            Row(
                modifier = Modifier.background(Color.Red),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .weight(1F),
                    text = item.product.price.toString(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun cartTotal(
    modifier: Modifier = Modifier,
    order: Order?
) {

}
