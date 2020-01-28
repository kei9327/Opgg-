package kr.wayde.opgg.ui.main

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.ui.BaseObservableViewModel

class BuildItemViewModel(val items: String) : BaseObservableViewModel() {

    companion object {
        @JvmStatic
        @BindingAdapter("app:buildItemImageUrl")
        fun buildItemImageUrl(view: SimpleDraweeView, builtItemImageUrl: String) {
            builtItemImageUrl
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.setImageURI(builtItemImageUrl)
                }
        }

    }

}