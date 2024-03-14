package com.example.pbmpraktikum

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pbmpraktikum.ui.theme.PBMPraktikumTheme

@Composable
fun MainScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        item {
            Logo(logo = painterResource(id = R.drawable.usk))
        }

        items(10) {
            Spacer(modifier = Modifier.height(16.dp))
            InfoCard(
                name = "Your Name",
                nim = "xx08107010xxx",
                description = "BZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"
            )
        }
    }
}

@Composable
fun Logo(logo: Painter) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = logo,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
            )

            Text(
                text = "Universitas Syiah Kuala",
                modifier = Modifier.weight(1F),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun InfoCard(name: String, nim: String, description: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {

            Row {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = nim,
                    modifier = Modifier.weight(1F),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LogoPreview() {
    PBMPraktikumTheme {
        Logo(logo = painterResource(id = R.drawable.usk))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InfoCardPreview() {
    PBMPraktikumTheme {
        InfoCard(
            name = "NAME_TEST",
            nim = "xx08107010xxx",
            description = "BZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ",
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
    PBMPraktikumTheme {
        MainScreen()
    }
}