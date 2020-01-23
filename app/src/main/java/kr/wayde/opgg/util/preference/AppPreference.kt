package kr.wayde.opgg.util.preference

import android.content.Context

/**
 * Created by Wayde.k(Jnaghyok Park) on 2020.01.24
 */
class AppPreference(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: AppPreference? = null

        fun getInstance(context: Context) =
            INSTANCE
                ?: AppPreference(context)
                    .also {
                        INSTANCE = it
                    }
    }

    private val sharedPreferences = context.getSharedPreferences("opgg_wayde", 0)
    private val editor = sharedPreferences.edit()
}