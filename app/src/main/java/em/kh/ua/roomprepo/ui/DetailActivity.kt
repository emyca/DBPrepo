package em.kh.ua.roomprepo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import em.kh.ua.roomprepo.R
import em.kh.ua.roomprepo.database.Fruit

class DetailActivity : AppCompatActivity() {

    private var detailName: TextView? = null
    private var detailDesc: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar()
        setupViews()
        handleParcel()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun setupViews(){
        detailName = findViewById(R.id.detail_name)
        detailDesc = findViewById(R.id.detail_desc)
    }

    private fun handleParcel(){
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            val note: Fruit? = bundle.getParcelable("fruit_detail")
            if (note != null) {
                detailName?.text = note.fruitName
                detailDesc?.text = note.fruitDesc
            }
        }
    }
}
