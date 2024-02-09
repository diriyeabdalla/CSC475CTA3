package com.example.simplecontactbook

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = getSharedPreferences("SimpleContactBookPrefs", Context.MODE_PRIVATE)

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonEdit = findViewById(R.id.buttonEdit)


        buttonAdd.setOnClickListener {
            addContact(editTextName.text.toString(), editTextPhone.text.toString())
        }


        buttonDelete.setOnClickListener {
            deleteContact(editTextName.text.toString())
        }


        buttonEdit.setOnClickListener {
            editContact(editTextName.text.toString(), editTextPhone.text.toString())
        }
    }

    private fun addContact(name: String, phone: String) {
        if (name.isNotEmpty() && phone.isNotEmpty()) {
            val editor = sharedPreferences.edit()
            editor.putString(name, phone)
            editor.apply()

            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()
            editTextName.text.clear()
            editTextPhone.text.clear()
        } else {
            Toast.makeText(this, "Both name and phone number are required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteContact(name: String) {
        if (name.isNotEmpty()) {
            if (sharedPreferences.contains(name)) {
                val editor = sharedPreferences.edit()
                editor.remove(name)
                editor.apply()
                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter the name of the contact to delete", Toast.LENGTH_SHORT).show()
        }
    }

    private fun editContact(name: String, newPhone: String) {
        if (name.isNotEmpty() && newPhone.isNotEmpty()) {
            if (sharedPreferences.contains(name)) {
                val editor = sharedPreferences.edit()
                editor.putString(name, newPhone)
                editor.apply()
                Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Both name and new phone number are required", Toast.LENGTH_SHORT).show()
        }
    }
}
