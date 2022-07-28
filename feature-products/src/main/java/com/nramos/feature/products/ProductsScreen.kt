package com.nramos.feature.products

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nramos.cabifymobilechallenge.core.navigation.Destination
import com.nramos.core.domain.model.Discount
import com.nramos.core.domain.model.Product

@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    navigateToCart: (Destination) -> Unit,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ProductsView(
        modifier = modifier,
        state = state
    )
}

@Composable
fun ProductsView(
    modifier: Modifier,
    state: State
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        topBar = { ProductsTopBar() },
        floatingActionButton = { CartFloatingButton() }
    ) {
        ProductList(
            products = state.products
        )
    }
}

@Composable
fun ProductsTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = stringResource(id = R.string.products_title),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ),
            fontSize = 21.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        cells = GridCells.Fixed(2)
    ) {
        items(products.size) { index ->
            ProductItem(
                product = products[index]
            )
        }
    }
}

@Composable
fun ProductItem(
    product: Product
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
            onClick = {}
        ) {
            Text(text = stringResource(id = R.string.products_add_to_cart))
        }
    }
}

@Composable
fun CartFloatingButton(
    modifier: Modifier = Modifier
) {

    Box(modifier) {
        FloatingActionButton(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = stringResource(id = R.string.products_cart_button_desc)
            )
        }
        Badge(
            modifier = Modifier.align(Alignment.TopEnd),
            backgroundColor = MaterialTheme.colors.onBackground,
        ) {
            Text(
                modifier = Modifier.padding(1.dp),
                text = "2",
                fontSize = 10.sp,
                style = TextStyle(
                    color = MaterialTheme.colors.background,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

}

@Composable
fun DiscountView(
    modifier: Modifier = Modifier,
    discount: Discount
) {
    val title = when(discount) {
        is Discount.Bulk -> stringResource(id = R.string.discount_2x1)
        is Discount.TwoForOne -> stringResource(id = R.string.discount_bulk)
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