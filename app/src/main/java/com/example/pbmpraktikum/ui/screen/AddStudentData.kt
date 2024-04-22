package com.example.pbmpraktikum.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pbmpraktikum.ui.database.User
import com.example.pbmpraktikum.ui.theme.PBMPraktikumTheme
import com.example.pbmpraktikum.ui.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddStudentData(navController: NavController, userViewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showSubmitDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        name = dataTextField(name, "Name", singleLine = true)

        Spacer(modifier = Modifier.height(16.dp))

        nim = dataTextField(nim, "Nim", singleLine = true)

        Spacer(modifier = Modifier.height(16.dp))

        description = dataTextField(description, "Description", singleLine = false)

        Spacer(modifier = Modifier.height(16.dp))

        showSubmitDialog =
            submitButton(name, nim, description, coroutineScope, userViewModel, showSubmitDialog)

        Spacer(modifier = Modifier.height(16.dp))

        BackButton(navController)
    }
}

@Composable
private fun dataTextField(description: String, fieldName: String, singleLine: Boolean): String {
    var description1 by remember{ mutableStateOf(description) }
    OutlinedTextField(
        value = description1,
        onValueChange = { description1 = it },
        label = {
            Text(
                text = fieldName,
                style = MaterialTheme.typography.bodySmall
            )
                },
        textStyle = MaterialTheme.typography.bodySmall,
        singleLine = singleLine,
        modifier = Modifier.fillMaxWidth(),
    )
    return description1
}

@Composable
private fun submitButton(
    name: String,
    nim: String,
    description: String,
    coroutineScope: CoroutineScope,
    userViewModel: UserViewModel,
    showSubmitDialog: Boolean
): Boolean {
    var showSubmitDialog1 by remember { mutableStateOf(showSubmitDialog) }
    Button(
        onClick = {
            val user = User(0, name, nim, description)
            coroutineScope.launch {
                userViewModel.addUser(user)
            }
            showSubmitDialog1 = true
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = "Submit",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelLarge,
        )
    }

    if (showSubmitDialog1) {
        AlertDialog(
            onDismissRequest = { showSubmitDialog1 = false },
            confirmButton = {},
            title = {
                Text(
                    text = "Success!",
                    style = MaterialTheme.typography.titleSmall
                )
                    },
            text = {
                Text(
                    text = "Data has been added successfully",
                    style = MaterialTheme.typography.bodySmall
                )
                   },
        )
    }

    return showSubmitDialog1
}

@Composable
private fun BackButton(navController: NavController) {
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        Button(
            onClick = { navController.navigate("show_student_data") },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Back",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddStudentScreenPreview() {
    PBMPraktikumTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AddStudentData(navController = rememberNavController(), userViewModel = viewModel(factory = UserViewModel.factory))
        }
    }
}
