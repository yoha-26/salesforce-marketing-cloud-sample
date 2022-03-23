package jp.youmalink.salesforce_marketing_cloud_sample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk
import jp.youmalink.salesforce_marketing_cloud_sample.BuildConfig
import jp.youmalink.salesforce_marketing_cloud_sample.R
import jp.youmalink.salesforce_marketing_cloud_sample.SecondActivity

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        textView = rootView.findViewById(R.id.message)
        rootView.findViewById<View>(R.id.btn_next_screen).setOnClickListener {
            SecondActivity.start(requireContext())
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        sendAttribute()
    }

    override fun onResume() {
        super.onResume()
        getAndShowAttributes()
    }

    private fun sendAttribute() {
        SFMCSdk.requestSdk { sdk ->
            sdk.identity.run {
                // Set Attribute value
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_A, "false")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_B, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_C, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_D, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_E, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_F, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_G, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_H, "true")
                // setProfileAttribute(BuildConfig.CUSTOM_KEY_I, "true")
                setProfileAttributes(
                    mapOf(
                        BuildConfig.CUSTOM_KEY_A to "false",
                        BuildConfig.CUSTOM_KEY_A to "false",
                        BuildConfig.CUSTOM_KEY_B to "true",
                        BuildConfig.CUSTOM_KEY_C to "true",
                        BuildConfig.CUSTOM_KEY_D to "true",
                        BuildConfig.CUSTOM_KEY_E to "true",
                        BuildConfig.CUSTOM_KEY_F to "true",
                        BuildConfig.CUSTOM_KEY_G to "true",
                        BuildConfig.CUSTOM_KEY_H to "true",
                        BuildConfig.CUSTOM_KEY_I to "true"
                    )
                )
            }
        }
    }

    private fun getAndShowAttributes() {
        SFMCSdk.requestSdk { sdk ->
            sdk.mp {
                textView.text = it.registrationManager.attributes.toString()
            }
        }
    }
}