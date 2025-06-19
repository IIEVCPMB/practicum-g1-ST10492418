//Alwande S. Mkhize ST10492418
package vcmsa.ci.musicplaylistmanagerapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
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
import androidx.core.view.OneShotPreDrawListener.add
import vcmsa.ci.musicplaylistmanagerapp.ui.theme.MusicPlaylistManagerAppTheme

class MainActivity : ComponentActivity() {


    private val titleOfSong = ArrayList<String>() //Initialises an array for songTitles
    private nameOfArtist= ArrayList<String>() //Initialises an array for artist's names
    private val ratings = ArrayList<Int>() //Initialises array for ratings - uses integer data type
    private val userComments = ArrayList<String>() //Initialises array for comments from the user
    private var currentIndex = 0 //Assigning the currentIndex(counter) to 0 as starting value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainscreen) //Initialises program starting point

        //Layout Reference
        val addLayout =
            findViewById<LinearLayout>(R.id.layoutAdd) //Assigns LinearLayout to a variable
        val viewLayout =
            findViewById<LinearLayout>(R.id.layoutView) //Assigns LinearLayout to a variable

        //Main Screen Setup
        val addButton = findViewById<Button>(R.id.btnAdd) //assigns button to a variable
        val nextButton = findViewById<Button>(R.id.btnNext) //assigns button to a variable
        val exitButton = findViewById<Button>(R.id.btnExit) //assigns button to a variable

        //Navigation logic
        addButton.setOnClickListener {
            addLayout.visibility = View.VISIBLE //This line prompts the addLayout to appear
            viewLayout.visibility = View.GONE //This line prompts the viewLayout to disappear

            val Title = findViewById<TextView>(R.id.txtTitle)
            val Artist = findViewById<TextView>(R.id.txtArtist)
            val Rating = findViewById<TextView>(R.id.txtRating)
            val Comments = findViewById<TextView>(R.id.txtComments)

            while (currentIndex != 4) //This line restricts the loop to run for 4 times only(counter starts at 0)

                AlertDialog.Builder(this)
                    .setTitle("Add Song details")
                    .setView(addLayout)
                    .setPositiveButton("Add") { _, _ ->
                        val songTitle = Title.text.toString()
                        val artistName = Artist.text.toString()
                        val rating = Rating.text.toString().toIntOrNull()
                        val comment = Comments.text.toString()
                        currentIndex++

                        if (title.isBlank() || artistName.isBlank() || rating == null || comment.isBlank()) {
                            showError("All fields must be correctly filled")
                            Log.i(
                                "Fill in all spaces",
                                "$title - $artistName - $rating - $comment."
                            )
                            return@setPositiveButton
                        }
                        titleOfSong.add(songTitle)
                        nameOfArtist.add(artistName)
                        ratings.add(rating)
                        userComments.add(comment)
                            Log.i("Playlist updated", "$title - $artistName - $rating - $comment.")
                        }


                    }



            nextButton.setOnClickListener {
                setContentView(R.layout.viewscreen)
                setUpDetailedView()
            }

            exitButton.setOnClickListener {
                finish()
            }

        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()

    }

    private fun setUpDetailedView() {
        var totalAverage = 0


        val display = findViewById<Button>(R.id.btnDisplay)
        val average = findViewById<Button>(R.id.btnAverage)
        val back = findViewById<Button>(R.id.btnReturn)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        display.setOnClickListener {
            var result = StringBuilder()
            for (i in 0 until songTitle.size) {
                result.append("Song Title: ${songTitle[i]} - Artist's name: ${artistName[i]} - Rating: ${ratings[i]} - User comments: ${userComments[i]} \n\n ")
                currentIndex++
            }
            txtResult.text = result.toString()
            Log.i("Display", "All items displayed.")

            average.setOnClickListener {
                for (i in 0 until ratings.size)
                    totalAverage = totalAverage + ratings[currentIndex]
                val averageTotal = totalAverage / 4
                print("The average rating is: $averageTotal")
            }
            back.setOnClickListener {
                setContentView(R.layout.mainscreen)
            }

    }    }