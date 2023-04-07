package com.example.istminesweeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity  : AppCompatActivity() {
    public var size_min: Int = 2


    public var size_max: Int = 11
    private val button_open by lazy { findViewById<Button>(R.id.button) }
    public val size_count by lazy { findViewById<EditText>(R.id.editSize) }
      public val mines_count by lazy { findViewById<EditText>(R.id.editNumber) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        size_count.setText("7")
      mines_count.setText("7")

        button_open.setOnClickListener {
            var textmessege: String = ""
            var res: Int = checker()
            if (res == 1) {
                textmessege =
                    "Указан нверный разер. \nРазмер: минимальое значение - $size_min, максимальное - $size_max."
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()
            } else if (res == 2) {
                textmessege = "Число мин не должно превышать половину всех клеток."
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()  }
            if (res == 0) {
                textmessege = "Поехали!"
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("size", size_count.text.toString())
                               intent.putExtra("mines", mines_count.text.toString())
                startActivity(intent)
            }

        }
    }


    private fun checker()
            : Int {
        var size_current: Int = Integer.parseInt(size_count.text.toString())


        if (size_current > size_max || size_current < size_min) {
            return 1
        }
        if ((size_current * size_current).toFloat() * 0.5 < mines_count.text.toString()
                .toFloat()
        ) {
            return 2
        }

        return 0
    }

}
