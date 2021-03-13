package com.example.androiddevchallenge

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        LoginPage(
            onSignup = {},
            onLogin = {}
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

// region Welcome Screen

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MyTheme {
        Surface(Modifier.size(width = 360.dp, height = 640.dp)) {
            WelcomePage({}, {})
        }
    }
}

@Composable
fun WelcomePage(onSignup: () -> Unit, onLogin: () -> Unit) {
    WelcomeBackground()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo()
        Spacer(Modifier.height(32.dp))
        BigButton(
            onClick = onSignup,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Text(stringResource(id = R.string.sign_up).toUpperCase(LocaleList.current))
        }
        Spacer(Modifier.height(8.dp))
        BigButton(
            onClick = onLogin,
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Text(stringResource(id = R.string.log_in).toUpperCase(LocaleList.current))
        }
    }
}

@Composable
fun WelcomeBackground() {
    Image(
        painter = painterResource(id = R.drawable.ic_welcome),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = stringResource(id = R.string.app_name)
    )
}

@Composable
fun BigButton(
    onClick: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth(),
        content = content
    )
}
// endregion

// region Login Screen

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    MyTheme {
        Surface(Modifier.size(width = 360.dp, height = 640.dp)) {
            LoginPage({}, {})
        }
    }
}

@Composable
fun LoginPage(onLogin: () -> Unit, onSignup: () -> Unit) {
    LoginBackground()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTitle()

        Spacer(Modifier.height(32.dp))

        var email = ""
        EditTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = @Composable {
                Text(stringResource(id = R.string.email_address))
            }
        )

        Spacer(Modifier.height(8.dp))

        var password = ""
        EditTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = @Composable {
                Text(stringResource(id = R.string.password))
            },
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(Modifier.height(8.dp))

        BigButton(
            onClick = onLogin,
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Text(stringResource(id = R.string.log_in).toUpperCase(LocaleList.current))
        }
        val annotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.missing_account))
            append(" ")
            pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
            pushStringAnnotation(tag = "URL", annotation = "signup")
            append(stringResource(id = R.string.sign_up))
            pop()
            pop()
        }

        ClickableText(
            text = annotatedString,
            style = MaterialTheme.typography.body1.copy(color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)),
            modifier = Modifier.paddingFromBaseline(top = 32.dp),
            onClick = { offset ->
                annotatedString
                    .getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let {
                        onSignup()
                    }
            }
        )
    }
}

@Composable
fun LoginBackground() {
    Image(
        painter = painterResource(id = R.drawable.ic_login),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun LoginTitle() {
    Text(
        text = stringResource(id = R.string.log_in).toUpperCase(LocaleList.current),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.paddingFromBaseline(top = 200.dp)
    )
}

@Composable
fun EditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

// endregion

// region Home Screen

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    MyTheme {
        Surface(Modifier.size(width = 360.dp, height = 640.dp)) {
            HomePage()
        }
    }
}

@Composable
fun HomePage() {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            item {
                SearchField()
            }
            item {
                RowTitle(title = stringResource(id = R.string.favorite_collections))
                RowFavorite()
            }
            item {
                RowTitle(title = stringResource(id = R.string.align_your_body))
                RowBody()
            }
            item {
                RowTitle(title = stringResource(id = R.string.align_your_mind))
                RowMind()
            }
        }
    }
}


@Composable
fun CollectionCard(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(width = 192.dp, height = 56.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = title,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Composable
fun Course(
    painter: Painter,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier
                .size(88.dp)
                .clip(RoundedCornerShape(percent = 50)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            modifier = Modifier.paddingFromBaseline(top = 24.dp),
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun SearchField() {
    Spacer(Modifier.height(56.dp))
    var search = ""
    EditTextField(
        value = search,
        onValueChange = { search = it },
        placeholder = @Composable {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.body1
            )
        },
        modifier = Modifier.padding(horizontal = 16.dp),
        leadingIcon = @Composable {
            SmallIcon(resId = R.drawable.ic_search)
        }
    )
}

@Composable
fun RowTitle(title: String){
    Text(
        text = title.toUpperCase(LocaleList.current),
        style = MaterialTheme.typography.h2,
        modifier = Modifier
            .paddingFromBaseline(top = 40.dp)
            .padding(horizontal = 16.dp)
            .padding(bottom = 4.dp)
    )
}

@Composable
fun RowFavorite(){
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        item {
            Column {
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_nature_meditations),
                    title = "Short mantras",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_nature_meditations),
                    title = "Nature meditation",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
        item {
            Column {
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_stress_and_anxiety),
                    title = "Stress and anxiety",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_self_massage),
                    title = "Self-massage",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
        item {
            Column {
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_overwhelmed),
                    title = "Overwhelmed",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
                CollectionCard(
                    painter = painterResource(id = R.mipmap.ic_nightly_wind_down),
                    title = "Nightly wind down",
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun RowBody() {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_inversions),
                title = "Inversions",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_quick_yoga),
                title = "Quick yoga",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_stretching),
                title = "Stretching",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_tabata),
                title = "Tabata",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_hiit),
                title = "HIIT",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_pre_natal_yoga),
                title = "Pre-natal yoga",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun RowMind() {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_meditate),
                title = "Meditate",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_with_kids),
                title = "With kids",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_aromatherapy),
                title = "Aromatherapy",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_on_the_go),
                title = "On the go",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_with_pets),
                title = "With pets",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
        item {
            Course(
                painter = painterResource(id = R.mipmap.ic_high_stress),
                title = "High stress",
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun SmallIcon(resId: Int) {
    Icon(
        painter = painterResource(id = resId),
        contentDescription = stringResource(id = R.string.search),
        modifier = Modifier.size(18.dp)
    )
}

// endregion