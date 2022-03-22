package jp.youmalink.salesforce_marketing_cloud_sample

import android.app.Application
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import com.salesforce.marketingcloud.MarketingCloudConfig
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions
import com.salesforce.marketingcloud.notifications.NotificationManager
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig
import kotlin.random.Random

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SFMCSdk.configure(applicationContext as Application, SFMCSdkModuleConfig.build {
            pushModuleConfig = MarketingCloudConfig.builder().apply {
                setApplicationId(BuildConfig.PUSH_APP_ID)
                setAccessToken(BuildConfig.PUSH_ACCESS_TOKEN)
                setSenderId(BuildConfig.SENDER_ID)
                setMarketingCloudServerUrl(BuildConfig.PUSH_CLOUD_SERVER_URL)
                setMid(BuildConfig.PUSH_MID)

                // Other configuration options
                setInboxEnabled(true)
                setAnalyticsEnabled(true)
                setPiAnalyticsEnabled(true)
                setUseLegacyPiIdentifier(false)
                setDelayRegistrationUntilContactKeyIsSet(false)

                // 設定しないとクラッシュするためダミーでセット
                setNotificationCustomizationOptions(
                    NotificationCustomizationOptions.create(
                        R.drawable.ic_launcher_background,
                        { context, _ ->
                            // 通知を表示
                            val requestCode = Random.nextInt()
                            val intent = Intent()
                            PendingIntent.getService(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                        },
                        { context, _ ->
                            NotificationManager.createDefaultNotificationChannel(context)
                        }
                    )
                )

            }.build(applicationContext)
        }) {}
    }
}