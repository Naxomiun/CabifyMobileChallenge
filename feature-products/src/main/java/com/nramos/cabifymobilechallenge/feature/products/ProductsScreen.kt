package com.nramos.cabifymobilechallenge.feature.products

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nramos.cabifymobilechallenge.core.domain.model.Discount
import com.nramos.cabifymobilechallenge.core.domain.model.Product
import com.nramos.cabifymobilechallenge.core.navigation.Destination
import com.nramos.cabifymobilechallenge.core.presentation.ui.AppTopBar
import com.nramos.cabifymobilechallenge.core.presentation.ui.FullLoadingScreen

@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    navigateToCart: (Destination) -> Unit,
    viewModel: ProductsScreenViewModel = hiltViewModel()
) {
    val state by viewModel.viewState.collectAsState()
    ProductsView(
        modifier = modifier,
        state = state,
        navigateToCart = { navigateToCart(Destination.Cart) },
        onAddToCartClicked = viewModel::addItemToCart
    )
}

@Composable
fun ProductsView(
    modifier: Modifier = Modifier,
    state: ProductsScreenState,
    navigateToCart: () -> Unit,
    onAddToCartClicked: (Product) -> Unit
) {
    Box {
        Scaffold(
            modifier = modifier
                .statusBarsPadding()
                .navigationBarsPadding(),
            topBar = {
                AppTopBar(
                    title = stringResource(id = R.string.products_title)
                )
            },
            floatingActionButton = {
                CartFloatingButton(
                    itemsInOrder = state.order?.getTotalItemsInOrder() ?: 0,
                    navigateToCart = navigateToCart
                )
            }
        ) {
            ProductList(
                modifier = Modifier.padding(it),
                products = state.products,
                onAddToCartClicked = onAddToCartClicked
            )
        }
        FullLoadingScreen(isLoading = state.spinnerLoading)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onAddToCartClicked: (Product) -> Unit
) {
    AnimatedContent(targetState = products.isNotEmpty()) {
        if (it) {
            LazyVerticalGrid(
                modifier = modifier,
                contentPadding = PaddingValues(15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                columns = GridCells.Fixed(2)
            ) {
                items(products, key = { it.code }) {
                    ProductItem(
                        product = it,
                        onAddToCartClicked = onAddToCartClicked
                    )
                }
            }
        } else {
            ProductsLoaderView()
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    onAddToCartClicked: (Product) -> Unit
) {
    Column {
        Box {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .aspectRatio(1F),
                model = product.mediaUrl,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = product.name
            )
            if (product.hasDiscount()) {
                DiscountView(
                    modifier = Modifier.align(Alignment.BottomStart),
                    discount = product.discount!!
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = product.name,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp
            )
        )
        Text(
            text = String.format("%s %s", product.price.toString(), product.currencyCode),
            style = TextStyle(
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp
            )
        )
        Button(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = { onAddToCartClicked(product) }
        ) {
            Text(text = stringResource(id = R.string.products_add_to_cart))
        }
    }
}

@Composable
fun CartFloatingButton(
    modifier: Modifier = Modifier,
    navigateToCart: () -> Unit,
    itemsInOrder: Int,
) {

    Box(modifier) {
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
            onClick = navigateToCart
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = stringResource(id = R.string.products_cart_button_desc)
            )
        }

        if (itemsInOrder != 0) {
            Badge(
                modifier = Modifier.align(Alignment.TopEnd),
                backgroundColor = MaterialTheme.colors.onBackground,
            ) {
                Text(
                    modifier = Modifier.padding(1.dp),
                    text = itemsInOrder.toString(),
                    fontSize = 10.sp,
                    style = TextStyle(
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }

}

@Composable
fun DiscountView(
    modifier: Modifier = Modifier,
    discount: Discount
) {
    val title = when (discount) {
        is Discount.TwoForOne -> stringResource(id = R.string.discount_2x1)
        is Discount.Bulk -> stringResource(id = R.string.discount_bulk)
    }

    Text(
        modifier = modifier
            .padding(all = 8.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colors.secondary)
            .padding(horizontal = 10.dp, vertical = 3.dp),
        text = title,
        style = TextStyle(
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ProductsLoaderView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondary
        )
    }
}