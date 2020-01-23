package kr.wayde.opgg.util

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by Wayde.k(Jnaghyok Park) on 2020.01.24
 */
class AndroidContext private constructor(val application: Context){

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var sInstance: AndroidContext? = null

        /**
         * The android context object cannot be created until the android
         * has created the application object. The AndroidContext object
         * must be initialized before other singletons can use it.
         */
        fun initialize(context: Context) {
            if (sInstance == null) {
                sInstance = AndroidContext(context)
            }
        }

        /**
         * Return a previously initialized instance, throw if it has not been
         * initialized yet.
         */
        fun instance(): AndroidContext {
            if (sInstance == null) {
                throw IllegalStateException("Android context was not initialized.")
            }
            return sInstance as AndroidContext
        }
    }
}