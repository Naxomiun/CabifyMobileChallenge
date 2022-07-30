package com.nramos.cabifymobilechallenge.feature.cart

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
        removeItemClicked = viewModel::removeItemFromCart
    )
}

@Composable
fun CartView(
    modifier: Modifier = Modifier,
    state: CartScreenState,
    removeItemClicked: (CartItem) -> Unit
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
                order = state.order,
                removeItemClicked = removeItemClicked
            )
        }
        FullLoadingScreen(isLoading = state.spinnerLoading)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CartList(
    modifier: Modifier = Modifier,
    order: Order?,
    removeItemClicked: (CartItem) -> Unit
) {
    AnimatedContent(targetState = order != null) {
        if (it) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(
                        start = 15.dp,
                        end = 15.dp,
                        top = 15.dp,
                        bottom = 100.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(order!!.items) { item ->
                        CartItemView(
                            item = item,
                            removeItemClicked = removeItemClicked
                        )
                    }
                }
                CartTotalView(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    order = order!!
                )
            }
        }
    }
}

@Composable
fun CartItemView(
    modifier: Modifier = Modifier,
    item: CartItem,
    removeItemClicked: (CartItem) -> Unit
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
        Column(
            modifier = Modifier.padding(start = 15.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.totalPriceWithDiscounts} ${item.product.currencyCode}",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                if (item.totalPriceWithDiscounts != item.totalPrice) {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "${item.totalPrice} ${item.product.currencyCode}",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                }

                Spacer(Modifier.weight(1F))

                IconButton(
                    modifier = Modifier
                        .size(18.dp),
                    onClick = { removeItemClicked(item) },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = stringResource(id = R.string.cart_remove_item),
                        tint = MaterialTheme.colors.onBackground,
                    )
                }
            }

            Text(
                text = item.product.name,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            )

            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (item.quantity > 1) {
                    Text(
                        modifier = Modifier
                            .padding(end = 10.dp),
                        text = "x${item.quantity}",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        text = "${item.pricePerUnitWithDiscount} ${item.product.currencyCode}",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    if (item.pricePerUnitWithDiscount != item.pricePerUnit) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            text = "${item.pricePerUnit} ${item.product.currencyCode}",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartTotalView(
    modifier: Modifier = Modifier,
    order: Order
) {
    if (order.items.isNotEmpty()) {
        Column(modifier.fillMaxWidth()) {
            Divider(
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.4F),
                modifier = Modifier
                    .height(0.3.dp)
            )
            Row(
                modifier = modifier
                    .padding(vertical = 15.dp)
                    .background(MaterialTheme.colors.background),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .weight(1F),
                    text = stringResource(id = R.string.cart_total),
                    style = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Column(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "${order.totalPriceWithDiscounts} ${order.getOrderCurrencyCode()}",
                        style = TextStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    if (order.hasDiscounts()) {
                        Text(
                            text = "${order.totalPrice} ${order.getOrderCurrencyCode()}",
                            style = TextStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Normal,
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    }
                }
            }
        }
    }
}
