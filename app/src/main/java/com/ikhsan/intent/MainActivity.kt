package com.ikhsan.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->
        if (result.resultCode == MoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveWithData: Button = findViewById(R.id.btn_moveData)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithObject: Button = findViewById(R.id.btn_moveObject)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialNumber: Button = findViewById(R.id.btn_dial_number)
        btnDialNumber.setOnClickListener(this)

        val btnMoveResult: Button = findViewById(R.id.btn_move_with_result)
        btnMoveResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_moveData -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Rahmad Noor Ikhsan")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_moveObject -> {
                val person = Person(
                    "Rahmad Noor Ikhsan",
                    20,
                    "rajaikhsan2021@gmail.com",
                    "Wonosobo"
                )

                val moveWithObject = Intent(this@MainActivity, MoveWithObject::class.java)
                moveWithObject.putExtra(MoveWithObject.EXTRA_PERSON, person)
                startActivity(moveWithObject)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "081425378298"
                val dialPhone = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
                startActivity(dialPhone)
            }
            R.id.btn_move_with_result -> {
                val moveForResult = Intent(this@MainActivity, MoveForResult::class.java)
                resultLauncher.launch(moveForResult)
            }
        }
    }
}