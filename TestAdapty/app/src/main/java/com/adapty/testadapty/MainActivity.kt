package com.adapty.testadapty

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adapty.adapty310.adapty310
import com.adapty.testadapty.ui.theme.TestAdaptyTheme

class MainActivity : ComponentActivity() {
    var adapty_ = adapty310()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main)

        val currentActivity: Activity = this

        val myButton = Button(this)
        myButton.text = "Show Paywall" // Set the text displayed on the button
        myButton.id = View.generateViewId() // Assign a unique ID (optional but recommended for referencing)
        // Customize other properties like background, text color, padding, etc.
        myButton.setBackgroundColor(Color.BLUE)
        myButton.setTextColor(Color.WHITE)

        val myButton1 = Button(this)
        myButton1.text = "Activate" // Set the text displayed on the button
        myButton1.id = View.generateViewId() // Assign a unique ID (optional but recommended for referencing)
        // Customize other properties like background, text color, padding, etc.
        myButton1.setBackgroundColor(Color.BLUE)
        myButton1.setTextColor(Color.WHITE)


        // Example for LinearLayout:
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, // width
            LinearLayout.LayoutParams.WRAP_CONTENT  // height
        )
        // You can also set margins:
        layoutParams.setMargins(16, 16, 16, 16) // left, top, right, bottom in pixels
        myButton.layoutParams = layoutParams
        myButton1.layoutParams = layoutParams

        val parentLayout = findViewById<LinearLayout>(R.id.layout_123 ) // Replace with your parent layout's ID
        parentLayout.addView(myButton1)
        parentLayout.addView(myButton)



        myButton.setOnClickListener {
            // Code to execute when the button is clicked
            //Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
            //adapty_.InitAdapty(applicationContext, "public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU")
            adapty_.DisplayPaywall("paywall_android", currentActivity)
        }


        myButton1.setOnClickListener {
            // Code to execute when the button is clicked
            //Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()

            adapty_.InitAdapty(applicationContext, "public_live_uXkrMIHl.6YWa7VOzEhya9jOE8VkU")
            //temp.DisplayPaywall("paywall_android", currentActivity)
        }
/*
        setContent {
            TestAdaptyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
 */
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAdaptyTheme {
        Greeting("Android")
    }
}