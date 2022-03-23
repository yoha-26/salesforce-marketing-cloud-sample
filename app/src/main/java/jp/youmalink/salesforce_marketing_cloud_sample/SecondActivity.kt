package jp.youmalink.salesforce_marketing_cloud_sample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk
import jp.youmalink.salesforce_marketing_cloud_sample.ui.main.MainFragment

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        SFMCSdk.requestSdk { sdk ->
            sdk.mp {
                val textView = findViewById<TextView>(R.id.second_message)
                textView.text = it.registrationManager.attributes.toString()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SecondActivity::class.java))
        }
    }
}