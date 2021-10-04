package com.example.debugging_challenge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainLayout: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var activeUsers: TextView
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView
    private lateinit var tv7: TextView
    private var users = arrayListOf(
        "Freddy",
        "Jason",
        "Ripley",
        "Poncho",
        "Saitama",
        "Archer",
        "Derek",
        "Pamela",
        "Simba",
        "Simon",
        "Retsy",
        "Peter",
        "Daria",
        "Smitty"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.mainLayout)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        submitBtn.setOnClickListener {
            if (usernameAccepted(userName.text.toString()) && passwordAccepted(password.text.toString())) {
                if (password2.text.toString() == password.text.toString()) {
                    Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                    users.add(
                        userName.text.toString()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
                    displayUsers()
                } else {
                    Toast.makeText(this, "The passwords do not match", Toast.LENGTH_LONG).show()
                }
            }
        }
        activeUsers = findViewById(R.id.tvActiveUsers)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)
        tv7 = findViewById(R.id.tv7)
    }

    private fun usernameAccepted(text: String): Boolean {
        var text =
            text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        if (text !in users) {
            if (text.length in 5..15) {
                if (!hasNumber(text)) {
                    if (!hasSpecial(text) && !text.contains(" ")) {
                        return true
                    }
                    Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_LONG).show()
                }
                Toast.makeText (this, "The username cannot contain numbers", Toast.LENGTH_LONG).show()
            }
            Toast.makeText (this, "The username must be between 5 and 15 characters long", Toast.LENGTH_LONG).show()
       }else{
        Toast.makeText(this, "The username is already taken", Toast.LENGTH_LONG).show()}
        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        if(text.length >= 8){
            if(hasUpper(text)){
                if(hasNumber(text)){
                    if(hasSpecial(text)){
                        return true
                    }
                    Toast.makeText(this, "The password must contain a special character", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(this, "The password must contain a number", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this, "The password must contain an uppercase letter", Toast.LENGTH_LONG).show()
        }else{
        Toast.makeText(this, "The password must be at least 8 characters long", Toast.LENGTH_LONG).show()}
        return false
    }

    private fun hasUpper(text: String): Boolean {
        for (i in text) {
            if (i.isUpperCase())
             if (text.contains(i))
                return true
        }
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "!@#$%"
        for(special in specialCharacters){
            if(text.contains(special)){
                return true
            }
        }
        return false
    }

    private fun displayUsers(){
        var allUsers = "Active Users:\n\n"
        for(user in users){
            allUsers += user + "\n"
        }
        activeUsers.text = allUsers
        activeUsers.isVisible = true
        mainLayout.isVisible = false
    }

}
