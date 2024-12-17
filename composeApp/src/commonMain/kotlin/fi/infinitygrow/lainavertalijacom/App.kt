package fi.infinitygrow.lainavertalijacom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lainavertalijacom.composeapp.generated.resources.LainavertailijacomMusta
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import lainavertalijacom.composeapp.generated.resources.Res
import lainavertalijacom.composeapp.generated.resources.compose_multiplatform
import kotlin.math.round
import kotlin.math.roundToInt


// Specify the type explicitly as Colors
val lightColors: Colors = lightColors(
    secondary = Color(0xFF003366),
    onSecondary = Color(0xFF003366),
    onBackground = Color.Black,
    onSurface = Color(0xFF003366) // Ensure the surface text color is white
)

val darkColors: Colors = darkColors(
    secondary = Color(0xFF003366),
    onSecondary = Color(0xFF003366),
    onBackground = Color.Black,
    onSurface = Color(0xFF003366)
)


// Function to round the value to the nearest whole number (since loan term is in whole years)
fun roundToNearestInt(value: Double): Double {
    return round(value)
}


val typography =
    Typography(
        h1 = TextStyle(fontWeight = FontWeight.W100, fontSize = 96.sp, color = Color(0xFF003366)),
        button = TextStyle(fontWeight = FontWeight.W600, fontSize = 14.sp, color = Color(0xFF003366)),
        body1 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFF003366) // Set default color for body text to white
        ),
        body2 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color(0xFF003366) // Set default color for smaller body text to white
        )
    )

// Utility function to safely convert a string to a float, falling back to 0f if invalid
fun floatWithNullConversion(principal: String): Float {
    return try {
        principal.toFloat()
    } catch (e: NumberFormatException) {
        0f
    }
}


//@Composable
//fun MyScreen2(urlManager: UrlManager) {
//    val annotatedText = buildAnnotatedString {
//        pushStringAnnotation(tag = "URL", annotation = "https://lainavertailija.com/tietosuojaseloste")
//        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.None)) {
//            append("tietosuojaseloste")
//        }
//        pop()
//    }
//    ClickableText(
//        text = annotatedText,
//        onClick = { offset ->
//            annotatedText.getStringAnnotations(tag = "URL", start = offset, end = offset)
//                .firstOrNull()?.let { annotation ->
//                    urlManager.openLink(annotation.item) // Pass the Android context
//                }
//        }
//    )
//}

@Composable
fun MyScreen(urlManager: UrlManager) {
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(tag = "URL", annotation = "https://lainavertailija.com/tietosuojaseloste")
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.None)) {
            append("tietosuojaseloste")
        }
        pop()
    }

    Text(
        text = annotatedText,
        modifier = Modifier.clickable {
            // Handle the click
            annotatedText.getStringAnnotations(tag = "URL", start = 0, end = annotatedText.length)
                .firstOrNull()?.let { annotation ->
                    urlManager.openLink(annotation.item)
                }
        },
        style = MaterialTheme.typography.body1
    )
}



@Composable
fun ClickableImage(
    urlManager: UrlManager
) {
    // Assuming the common `openLink` function (expect/actual) is already defined
    Image(
        painter = painterResource(Res.drawable.LainavertailijacomMusta), // Your image resource
        contentDescription = "Lainavertailija Logo", // Optional description
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                urlManager.openLink("https://lainavertailija.com")
            }

    )
}


@Composable
@Preview
fun App(
    urlManager: UrlManager
) {
    val colors = if (isSystemInDarkTheme()) darkColors else lightColors

    MaterialTheme(colors = colors, typography = typography) {
        val loanCalculator = LoanCalculator(10000, 5.00, 5.0) // Example values
        var showContent by remember { mutableStateOf(false) }
        var principal by remember { mutableIntStateOf(loanCalculator.principal) }
        var principalString by remember { mutableStateOf("50000") }
        var interestString by remember { mutableStateOf("5.00") }
        var loanString by remember { mutableStateOf("5") }
        var interestRate by remember { mutableDoubleStateOf(loanCalculator.interestRate) }
        var loanTerm by remember { mutableDoubleStateOf(loanCalculator.loanTerm) }
        var monthlyPayment by remember { mutableDoubleStateOf(loanCalculator.calculateMonthlyPayment()) }
        var monthlyInterest by remember { mutableDoubleStateOf(loanCalculator.calculateMonthlyInterest()) }
        var monthlyCapital by remember { mutableDoubleStateOf(loanCalculator.calculateMonthlyCapital()) }

        // Helper function to update the monthly payment whenever values change
        fun updateMonthlyPayment() {
            val updatedCalculator = LoanCalculator(principal, interestRate, loanTerm)
            monthlyPayment = updatedCalculator.calculateMonthlyPayment()
            monthlyInterest = updatedCalculator.calculateMonthlyInterest()
            monthlyCapital = updatedCalculator.calculateMonthlyCapital()
        }
        // Column with verticalScroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()), // Enable scrolling
            verticalArrangement = Arrangement.spacedBy(8.dp) // Space between elements
        ) {

            ClickableImage(urlManager)
            OutlinedTextField(
                value = principalString,
                onValueChange = { principalString = it
                    val principalValue = principalString.toIntOrNull()
                    if (principalValue != null) {
                        principal = principalValue
                        updateMonthlyPayment()
                    }
                },
                label = { Text("Lainamäärä (€)") }, // Label for the text field
                modifier = Modifier.fillMaxWidth().padding(4.dp), // Adding padding and width
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), // Number keyboard
            )
            Slider(
                value = floatWithNullConversion(principal.toString()), // Using a utility function to safely convert to Float
                onValueChange = { newValue ->
                    val roundedValue = ((newValue / 500).roundToInt() * 500).toFloat()
                    principal = roundedValue.toInt()
                    principalString = principal.toString()
                    updateMonthlyPayment() // Call function to update the monthly payment
                },
                valueRange = 0f..1_000_000f, // Slider range from 0 to 1,000,000
                steps = 1000, // Slider steps
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(), // Take full width
                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
            ) {
//                Button(onClick = { showContent = !showContent }) {
//                    Text("Hae Euribor")
//                }
            }
            OutlinedTextField(
                value = interestString, // the current value of the text
                onValueChange = { interestString = it
                    val interestValue = it.toDoubleOrNull() // ?: 0.0 // Safely parse the text to double
                    if (interestValue != null) {
                        interestRate = loanCalculator.roundToTwoDecimals(interestValue)
                        updateMonthlyPayment()
                    }
                },
                label = { Text("Vuosikorko (%)") }, // Label displayed in the field
                modifier = Modifier.fillMaxWidth().padding(4.dp),// Modifier with padding and width
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), // Show number keyboard
            )
            Slider(
                value = floatWithNullConversion(interestRate.toString()), // The slider uses the same value as the input field
                onValueChange = { newValue ->
                    val valueTwoDecimals = loanCalculator.roundToTwoDecimals(newValue.toDouble())
                    interestRate = valueTwoDecimals
                    interestString = interestRate.toString()
                    updateMonthlyPayment()
                },
                valueRange = 0f..30f, // Range of the slider from 0 to 30
                steps = 1000, // Number of steps between the min and max values
            )
            AnimatedVisibility(showContent) {
                //val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    //Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Row(
                        modifier = Modifier.fillMaxWidth(), // Make the Row take the full width
                        horizontalArrangement = Arrangement.Center // Center the buttons horizontally
                    ) {
                        Button(onClick = {
                            interestRate = 3.0
                            updateMonthlyPayment()
                        }) {
                            Text("3kk")
                        }
                        Spacer(modifier = Modifier.width(8.dp)) // Add space between the buttons
                        Button(onClick = {
                            interestRate = 6.0
                            updateMonthlyPayment()
                        }) {
                            Text("6kk")
                        }
                        Spacer(modifier = Modifier.width(8.dp)) // Add space between the buttons
                        Button(onClick = {
                            interestRate = 12.0
                            updateMonthlyPayment()
                        }) {
                            Text("12kk")
                        }
                    }
                    //Text("Compose: $greeting")
                }
            }
            OutlinedTextField(
                value = loanString,
                onValueChange = { loanString = it
                    val loanValue = loanString.toDoubleOrNull()
                    if (loanValue != null) {
                        loanTerm = loanValue
                        updateMonthlyPayment()
                    }
                },
                label = { Text("Laina-aika (vuotta)") }, // Label for loan term
                modifier = Modifier.fillMaxWidth().padding(4.dp), // Modifier for layout
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number), // Show number keyboard
            )
            Slider(
                value = floatWithNullConversion(loanTerm.toString()), // Display the loan term as a float for the slider
                onValueChange = {
                    loanTerm = roundToNearestInt(it.toDouble())
                    loanString = loanTerm.toInt().toString()
                    updateMonthlyPayment()
                },
                valueRange = 1f..50f, // Slider range from 1 to 50 years
                steps = 48, // There are 49 steps between 1 and 50 (50 - 1)
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Text("Kuukausierä: € ${formatCurrency(monthlyPayment)}")
            Text("Korko-osuus kuukaudessa: € ${formatCurrency(monthlyInterest)}")
            Text("Pääoma-osuus kuukaudessa: € ${formatCurrency(monthlyCapital)}")

            Spacer(modifier = Modifier.height(32.dp)) // Add space between the buttons
            Box(
                modifier = Modifier
                    .fillMaxSize() // Make the Box fill the available space
                    //.padding(16.dp) // Optional padding
                    .align(Alignment.CenterHorizontally)
            ) {
                // Center the content inside the Box
                MyScreen(urlManager = urlManager) // Your composable
                // (Alignment.Center) // Align in the center of the Box
            }
            //MyScreen(urlManager)
        }
    }
}