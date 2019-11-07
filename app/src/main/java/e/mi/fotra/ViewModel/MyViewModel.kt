package e.mi.fotra.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    val message = MutableLiveData<String>()

    fun myMessage(msg: String) {
        message.value = msg
    }
}