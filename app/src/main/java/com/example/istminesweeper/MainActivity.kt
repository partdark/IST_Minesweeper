package com.example.istminesweeper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity  : AppCompatActivity() {
    public var size_min: Int = 2


    public var size_max: Int = 11
    public var time : Int = 999
    private val button_open by lazy { findViewById<Button>(R.id.button) }
    public val size_count by lazy { findViewById<EditText>(R.id.editSize) }
      public val mines_count by lazy { findViewById<EditText>(R.id.editNumber) }
    public val time_count by lazy { findViewById<EditText>(R.id.editTextNumber2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  val inflater = menuInflater
       // inflater.inflate(R.menu.menu_main, menu)
      //  val popupMenu = androidx.appcompat.widget.PopupMenu(this, imageView)
      //  popupMenu.inflate(R.menu.popupmenu)


        size_count.setText("7")
      mines_count.setText("7")
time_count.setText(time.toString())
        button_open.setOnClickListener {
            var textmessege: String = ""
            var res: Int = checker()
            if (res == 1) {
                textmessege =
                    "Указан нверный разер. \nРазмер: минимальое значение - $size_min, максимальное - $size_max."
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show() }
            else if (res == -1)
            {
                textmessege =
                "Указано ноеверное время. \nМинимальое значение - 60, максимальное - 999."
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()
            } else if (res == 2) {
                textmessege = "Число мин не должно превышать половину всех клеток, и не должно быть меньше 1."
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()  }
            if (res == 0) {
                textmessege = "Поехали!"
                val toast = Toast.makeText(applicationContext, textmessege, Toast.LENGTH_LONG)
                toast.show()
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("size", size_count.text.toString())
                intent.putExtra("mines", mines_count.text.toString())
                intent.putExtra("time", time_count.text.toString())
                startActivity(intent)
            }

        }
    }


    override  fun onCreateOptionsMenu(menu : Menu?) : Boolean    {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu) }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.big -> {
                Toast.makeText(applicationContext, "вывод информации о приложении", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Activity_Info::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
        }

    private fun checker()
            : Int {
        if (size_count.text.toString() == "" )
        {
            return 1
        }
        if (mines_count.text.toString() == "")
        {
            return 2
        }
        if (time_count.text.toString() == "")
        {
            return -1;
        }
        var size_current: Int = Integer.parseInt(size_count.text.toString())


        if (size_current > size_max || size_current < size_min) {
            return 1
        }

        if ((size_current * size_current).toFloat() * 0.5 < mines_count.text.toString().toFloat() || mines_count.text.toString().toFloat() < 1
        ) {
            return 2
        }
        if (time_count.text.toString().toFloat() < 60 || time_count.text.toString().toFloat() > 999) {
            return -1
        }

        return 0
    }

}
